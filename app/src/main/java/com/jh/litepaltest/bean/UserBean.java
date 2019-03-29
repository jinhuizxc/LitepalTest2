package com.jh.litepaltest.bean;



import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ${Vico} on 2017/7/26.
 */

public class UserBean extends LitePalSupport {

    private int id;
    private String flag;
    private String password;
    private String account;
    private String nick_url;//头像url
    private int age;
    private boolean sex;  //true 为女性 false为男性

    private List<PraiseBean> praiseBeen = new ArrayList<>();

    public UserBean() {
        flag = UUID.randomUUID().toString();
    }

    public List<PraiseBean> getPraiseBeen() {
        return praiseBeen;
    }

    public void setPraiseBeen(List<PraiseBean> praiseBeen) {
        this.praiseBeen = praiseBeen;
    }

    public UserBean(String account, String password, String nick_url, int age, boolean sex) {
        flag = UUID.randomUUID().toString();
        this.account = account;
        this.password = password;
        this.nick_url = nick_url;
        this.age = age;
        this.sex = sex;
    }

    public int getId() {
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick_url() {
        return nick_url;
    }

    public void setNick_url(String nick_url) {
        this.nick_url = nick_url;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", flag='" + flag + '\'' +
                ", password='" + password + '\'' +
                ", account='" + account + '\'' +
                ", nick_url='" + nick_url + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
