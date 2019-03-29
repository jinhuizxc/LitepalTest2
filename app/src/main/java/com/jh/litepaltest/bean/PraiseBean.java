package com.jh.litepaltest.bean;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.UUID;

/**
 * Created by ${Vico} on 2017/7/26.
 */

public class PraiseBean extends LitePalSupport {

    private int id;
    private String flag;
    private UserBean userBean;
    private NewsBean newsBean;
    private boolean isPraise;

    public PraiseBean(){
        flag = UUID.randomUUID().toString();
        isPraise = false;
    }
    public boolean isPraise() {
        return isPraise;
    }
    
    public void setPraise(boolean praise) {
        isPraise = praise;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public NewsBean getNewsBean() {
        return newsBean;
    }

    public void setNewsBean(NewsBean newsBean) {
        this.newsBean = newsBean;
    }


}
