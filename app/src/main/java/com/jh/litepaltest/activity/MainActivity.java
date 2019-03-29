package com.jh.litepaltest.activity;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.jh.litepaltest.App;
import com.jh.litepaltest.R;
import com.jh.litepaltest.adapter.GuoNeiNewsAdapter;
import com.jh.litepaltest.bean.NewsBean;
import com.jh.litepaltest.bean.PraiseBean;
import com.jh.litepaltest.utils.Utils;

import org.litepal.LitePal;

import java.util.List;

/**
 * https://github.com/vicopan/LitePalDemo
 * 一个LitePal框架的小案例建关联表，并实现不同用户的点赞功能
 *
 * https://www.jianshu.com/p/a0fbdd4f28e7
 * 谈谈Android中的SharedPreferences
 *
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private TextView tv_title;
    private TextView tv_content;
    private ListView lv;
    private Button btn_praise;
    private NewsBean newsBean1;
    private TextView tv_praise_sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化新闻数据
        Utils.getInstances().initData(this);

        initView();

        newsBean1 = LitePal.findFirst(NewsBean.class, true);
        Log.e(TAG, "newsBean1: " + newsBean1);
        Log.e(TAG, "App.accountInfo: " + App.accountInfo);
        tv_title.setText(newsBean1.getTitile());
        tv_content.setText(newsBean1.getContent());
        tv_praise_sum.setText(newsBean1.getPraiseSum() + "赞");
        btn_praise.setOnClickListener(this);
        List<PraiseBean> all = LitePal.findAll(PraiseBean.class, true);
        for (PraiseBean bean : all) {
            if (newsBean1.getId() == bean.getNewsBean().getId() && App.accountInfo.getId() == bean.getUserBean().getId()) {
                btn_praise.setBackgroundColor(Color.parseColor("#FF0000"));
                break;
            } else {
                btn_praise.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }

        Log.e(TAG, "onCreate: " + App.accountInfo.getAccount());
    }

    public void refresh() {
        onCreate(null);
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);
        btn_praise = (Button) findViewById(R.id.btn_praise);
        tv_praise_sum = (TextView) findViewById(R.id.tv_praise_sum);
        lv = (ListView) findViewById(R.id.lv);
        List<NewsBean> all = LitePal.findAll(NewsBean.class);
        all.remove(0);//因为第一个数据在Listview控件的上方的控件已经用到了 我为了新闻唯一就删除掉
        lv.setAdapter(new GuoNeiNewsAdapter(this, all));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_praise:
                PraiseBean praiseBean = new PraiseBean();
                praiseBean.setNewsBean(newsBean1);
                praiseBean.setUserBean(App.accountInfo);
                praiseBean.setPraise(true);
                if (Utils.getInstances().isExists(newsBean1.getId(), App.accountInfo.getId())) {
                    Toast.makeText(this, "已经赞过", Toast.LENGTH_SHORT).show();
                } else {
                    if (praiseBean.save()) {
                        Toast.makeText(this, "成功点赞", Toast.LENGTH_SHORT).show();
                        btn_praise.setBackgroundColor(Color.parseColor("#FF0000"));
                        newsBean1.setPraiseSum(newsBean1.getPraiseSum() + 1);
                        newsBean1.save();
                        refresh();
                    }
                }

        }
    }
}