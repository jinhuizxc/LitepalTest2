package com.jh.litepaltest.bean;



import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ${Vico} on 2017/7/26.
 */

public class NewsBean extends LitePalSupport {

    private int id;
    private String flag;
    private String titile;
    private String img_url;
    private String vedio_url;
    private long praiseSum;  // 点赞数
    private String content;

    private List<UserBean> userBeen = new ArrayList<>();
    private List<PraiseBean> praiseBeen = new ArrayList<>();
    public NewsBean() {
        flag = UUID.randomUUID().toString();
    }



    public NewsBean(String titile, String img_url, String vedio_url, String content) {
        flag = UUID.randomUUID().toString();
        this.img_url = img_url;
        this.titile = titile;
        this.vedio_url = vedio_url;
        this.content =content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public List<UserBean> getUserBeen() {
        return userBeen;
    }

    public void setUserBeen(List<UserBean> userBeen) {
        this.userBeen = userBeen;
    }

    public List<PraiseBean> getPraiseBeen() {
        return praiseBeen;
    }

    public void setPraiseBeen(List<PraiseBean> praiseBeen) {
        this.praiseBeen = praiseBeen;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPraiseSum() {
        return praiseSum;
    }

    public void setPraiseSum(long praiseSum) {
        this.praiseSum = praiseSum;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getVedio_url() {
        return vedio_url;
    }

    public void setVedio_url(String vedio_url) {
        this.vedio_url = vedio_url;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "id=" + id +
                ", flag='" + flag + '\'' +
                ", titile='" + titile + '\'' +
                ", img_url='" + img_url + '\'' +
                ", vedio_url='" + vedio_url + '\'' +
                ", praiseSum=" + praiseSum +
                ", content='" + content + '\'' +
                '}';
    }
}
