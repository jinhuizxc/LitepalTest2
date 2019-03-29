package com.jh.litepaltest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.jh.litepaltest.App;
import com.jh.litepaltest.Constant;
import com.jh.litepaltest.R;
import com.jh.litepaltest.bean.UserBean;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;


/**
 * Created by ${Vico} on 2017/7/11.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private EditText mAccount;
    private EditText mPassword;
    private Button mLogin;
    private CheckBox isRemember;
    private Button mRegist;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();

        SharedPreferences sp = getSharedPreferences(Constant.NAME, Context.MODE_PRIVATE);
        String names = sp.getString(Constant.USERNAME, "");
        String passwords = sp.getString(Constant.PASSWORD, "");

        if (sp.getBoolean("isChecked", false)) {
            isRemember.setChecked(true);
        }

        mAccount.setText(names);
        mPassword.setText(passwords);

    }

    private void initEvent() {
        mLogin.setOnClickListener(this);
        mRegist.setOnClickListener(this);
    }

    private void initView() {
        mAccount = findViewById(R.id.et_account);
        mPassword = findViewById(R.id.et_pwd);
        mLogin = findViewById(R.id.btn_login);
        isRemember = findViewById(R.id.remember_pwd);
        mRegist = findViewById(R.id.btn_regist);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String account = mAccount.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, getResources().getString(R.string.notEmpty), Toast.LENGTH_SHORT).show();
                } else {
                    // 遍历UserBean读取所有信息
                    List<UserBean> all = LitePal.findAll(UserBean.class);
                    Log.e(TAG, "遍历UserBean读取所有信息: " + all);
                    for (UserBean userBean : all) {
                        if (userBean.getAccount().equals(account) && userBean.getPassword().equals(password)) {
                            // 当前登录账户信息保存进 MyApplication
                            App.getInstance().accountInfo = userBean;
                            // 保存用户信息(账号、密码)

                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                            return;
                        } else if (userBean.getAccount().equals(account) && !userBean.getPassword().equals(password)) {
                            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    Toast.makeText(this, "不存在改账号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_regist:
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
    /* VolleyUtil.getInstance().login(account,password,new VolleyUtil.LoginCallback(){

                       @Override
                       public void login_success(String info) {

                if (isRemember.isChecked()){
                    SharedPreferences sp = getSharedPreferences("REM", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username",account.toString());
                    editor.putString("password",password.toString());
                    sp.edit().putBoolean("isChecked", true).commit();
                    editor.commit();

                    if (sp!=null) {
                        Toast.makeText(LoginActivity.this, "已记住密码", Toast.LENGTH_SHORT).show();
                    }
                }
                           Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                           startActivity(intent);
                      }

                       @Override
                     public void loginFail(String err) {
                           Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                      }
                    });*/
}