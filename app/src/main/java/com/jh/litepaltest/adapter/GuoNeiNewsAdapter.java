package com.jh.litepaltest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.jh.litepaltest.App;
import com.jh.litepaltest.R;
import com.jh.litepaltest.bean.NewsBean;
import com.jh.litepaltest.bean.PraiseBean;
import com.jh.litepaltest.utils.Utils;

import java.util.List;


/**
 * Created by ${Vico} on 2017/7/18.
 */

public class GuoNeiNewsAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private List<NewsBean> list;
    private NewsBean newsBean;
    private static final String TAG = "GuoNeiNewsAdapter";
    public GuoNeiNewsAdapter(Context context, List<NewsBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_listview_guonei, null);
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_content = convertView.findViewById(R.id.tv_content);
            holder.tv_praise_sum = convertView.findViewById(R.id.tv_praise_sum);
            holder.btn_praise = convertView.findViewById(R.id.btn_praise);
            convertView.setTag(holder);
        } else {
           holder = (ViewHolder) convertView.getTag();
        }
        newsBean = list.get(position);
        holder.tv_title.setText(newsBean.getTitile());
        holder.tv_content.setText(newsBean.getContent());
        holder.tv_praise_sum.setText(newsBean.getPraiseSum()+"赞");
        //坑点一：因为复用 View 视图的原因，不能直接的设置背景颜色 ，而必须再写一个 else 的情况 感觉不会再爱了
        if (Utils.getInstances().isExists(newsBean.getId(), App.accountInfo.getId())) {
            holder.btn_praise.setBackgroundColor(Color.parseColor("#FF0000"));
        } else {
            holder.btn_praise.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        //点赞按钮的点击事件
        holder.btn_praise.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //坑点二：不知道为什么这里的 item 的 newsBean对象并不能用之前已经获取并赋值的成员变量 而是需要在通过position获取一次
                NewsBean newsBean = list.get(position);
                PraiseBean praiseBean = new PraiseBean();
                praiseBean.setNewsBean(newsBean);
                praiseBean.setUserBean(App.accountInfo);
                praiseBean.setPraise(true);
                //每个赞的唯一标识是通过用户ID和该新闻的ID建立起来的 所以是否赞过要这两者共同判断
                if (Utils.getInstances().isExists(newsBean.getId(), App.accountInfo.getId())) {
                    Log.i(TAG, "onClick: 已经赞过:"+position+"list.get(position).getId()"+newsBean.getId());
                    Toast.makeText(context, "已经赞过",  Toast.LENGTH_SHORT).show();
                } else {
                    //否则 保存该记录到点赞表当中去
                    if (praiseBean.save()) {
                        Log.i(TAG, "onClick: 成功点赞position:"+position+"list.get(position).getId())"+newsBean.getId());
                        Toast.makeText(context, "成功点赞", Toast.LENGTH_SHORT).show();
                        holder.btn_praise.setBackgroundColor(Color.parseColor("#FF0000"));
                        newsBean.setPraiseSum(newsBean.getPraiseSum() + 1);
                        newsBean.save();
                        notifyDataSetChanged();
                    }else {
                        holder.btn_praise.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_praise_sum;
        private Button btn_praise;
    }
}
