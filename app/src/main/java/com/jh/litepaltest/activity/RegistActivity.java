package com.jh.litepaltest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jh.litepaltest.R;
import com.jh.litepaltest.bean.UserBean;


/**
 * Created by ${Vico} on 2017/7/11.
 */

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegistActivity";
    private EditText et_account;
    private EditText et_password;
    private EditText et_gender;
    private EditText et_age;
    private Button btn_submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
        initEvent();
    }

    private void initEvent() {
        btn_submit.setOnClickListener(this);
    }

    private void initView() {
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_pwd);
        et_gender = (EditText) findViewById(R.id.et_gender);
        et_age = (EditText) findViewById(R.id.et_age);
        btn_submit = (Button) findViewById(R.id.btn_submit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:

                String account = et_account.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String gender = et_gender.getText().toString().trim();
                String age = et_age.getText().toString().trim();

                if (TextUtils.isEmpty(account)
                        || TextUtils.isEmpty(password)
                        || TextUtils.isEmpty(gender)
                        || TextUtils.isEmpty(age)) {
                    Toast.makeText(this, "以上信息不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    UserBean userBean = new UserBean();
                    userBean.setAccount(account);
                    userBean.setPassword(password);
                    userBean.setAge(Integer.parseInt(age));
                    boolean isSave = userBean.save();
                    Log.i(TAG, "onClick: " + isSave);
                    if (isSave) {
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

        }

    }
}
