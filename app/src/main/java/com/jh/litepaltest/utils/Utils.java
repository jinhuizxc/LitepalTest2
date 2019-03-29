package com.jh.litepaltest.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.jh.litepaltest.Constant;
import com.jh.litepaltest.bean.NewsBean;
import com.jh.litepaltest.bean.PraiseBean;

import org.litepal.LitePal;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ${Vico} on 2017/7/27
 */

public class Utils {

    private static Utils utils;

    private Utils() {
    }

    public static Utils getInstances() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    /**
     * 用于判断该赞是否是唯一的
     *
     * @param newsID
     * @param userID
     * @return
     */
    public boolean isExists(int newsID, int userID) {
        //巨坑：感觉自己像智障。。。LitePal框架要获取外键的话，有两种方法 激进查询和懒加载 这里先用激进查询 后面再学懒加载 昨天查询别人建议使用懒加载的方式
        List<PraiseBean> all = LitePal.findAll(PraiseBean.class, true);
        for (PraiseBean bean : all) {
            if (newsID == bean.getNewsBean().getId() && userID == bean.getUserBean().getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 用于初始化数据库中的新闻数据
     *
     * @param context
     */
    public void initData(Context context) {

        NewsBean newsBean;
        //仅仅第一次登录的时候初始化数据  原本应该是Application当中去的吧 因为 数据库和Sp的存储的生命有可能并不会同时消亡
        // 后面再处理 记录一下 免得忘记
        SharedPreferences rem = context.getSharedPreferences(Constant.NAME, MODE_PRIVATE);
        Log.i("BOOM", "initData: getBoolean = " + rem.getBoolean("First", true));
        if (rem.getBoolean("First", true)) {
            SharedPreferences.Editor editor = rem.edit();
            editor.putBoolean("First", false);
            editor.apply();
            //数据直接用for循环生成 有点懒
            for (int i = 0; i < 15; i++) {
                newsBean = new NewsBean();
                newsBean.setTitile("标题" + i);
                newsBean.setContent("内容" + i);
                boolean save = newsBean.save();
                Log.i("BOOM", "initData: " + save);
            }
        }
    }
}
