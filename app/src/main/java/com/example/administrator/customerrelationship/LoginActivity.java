package com.example.administrator.customerrelationship;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText et_inputname;
    private EditText et_inputpassword;
    private Button bt_login;
    private CheckBox cb_login_rememberpassword;
    private CheckBox cb_login_autologin;
    public static final int SHOW_RESPONSE = 0;
    public static String Message_RESPONSE =null;

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setListener();
        initData();
    }

    //初始化布局
    private void initView() {
        et_inputname = (EditText) findViewById(R.id.et_name);
        et_inputpassword = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        cb_login_rememberpassword = (CheckBox) findViewById(R.id.cb_login_rememberpassword);
        cb_login_autologin = (CheckBox) findViewById(R.id.cb_login_autologin);
    }

    //点击事件响应
    private void setListener() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getAccount().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "你输入的账号为空！", Toast.LENGTH_SHORT).show();
                    return;
                }

               else if (getPassword().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "你输入的密码为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                   // Toast.makeText(LoginActivity.this, "登录中......", Toast.LENGTH_SHORT).show();
                    Intent intent_loginWelcome = new Intent();
                    intent_loginWelcome .setClass(LoginActivity.this,WelcomeActivity.class);
                    LoginActivity.this.startActivity(intent_loginWelcome);
                    //finish();
                    login();
                }
            }
        });
    }

    //判断初始化数据
    private void initData() {
        //判断是否第一次登录
        if (firstLogin()) {
            cb_login_rememberpassword.setChecked(false);
        }
        //判断是否记住密码
        if (rememberPassword()) {
            et_inputname.setText(getLocalName());
            et_inputpassword.setText(getLocalPassword());
            cb_login_rememberpassword.setChecked(true);
        } else {
            et_inputname.setText(getLocalName());
            et_inputpassword.setText("");
        }
        //判断是否自动登录
        if (autoLogin()) {
            cb_login_autologin.setChecked(true);
            //login();//去登录就可以
        }
    }

    //获得保存在本地的用户名
    public String getLocalName() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        String name = helper.getString("name");
        return name;
    }

    //获得保存在本地的密码
    public String getLocalPassword() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        String password = helper.getString("password");
        //  return Base64Utils.decryptBASE64(password);   //解码一下
        return password;   //解码一下
    }

    //判断是否第一次登陆
    private boolean firstLogin() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        boolean first = helper.getBoolean("first", true);
        if (first) {
            //创建一个ContentVa对象（自定义的）设置不是第一次登录，,并创建记住密码和自动登录是默认不选，创建账号和密码为空
            helper.putValues(new SharedPreferencesUtils.ContentValue("first", false),
                    new SharedPreferencesUtils.ContentValue("remenberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("name", ""),
                    new SharedPreferencesUtils.ContentValue("password", ""));
            return true;
        }
        return false;
    }

    //判断是否记住密码
    private boolean rememberPassword() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        boolean remenberPassword = helper.getBoolean("remenberPassword", false);
        return remenberPassword;
    }

    //判断是否自动登录
    private boolean autoLogin() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        boolean autoLogin = helper.getBoolean("autoLogin", false);
        return autoLogin;
    }

    //记住密码和自动登录的状态
    private void loadCheckBoxState() {
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        //如果设置自动登录
        if (cb_login_autologin.isChecked()) {
            //创建记住密码和自动登录是都选择,保存密码数据
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", true),
                    new SharedPreferencesUtils.ContentValue("autoLogin", true),
                    new SharedPreferencesUtils.ContentValue("name", et_inputname.getText().toString().trim()),
                    new SharedPreferencesUtils.ContentValue("password", et_inputpassword.getText().toString().trim()));

        } else if (!cb_login_rememberpassword.isChecked()) { //如果没有保存密码，那么自动登录也是不选的
            //创建记住密码和自动登录是默认不选,密码为空
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("password", ""),
                    new SharedPreferencesUtils.ContentValue("name", et_inputname.getText().toString().trim()));

        } else if (cb_login_rememberpassword.isChecked()) {   //如果保存密码，没有自动登录
            //创建记住密码为选中和自动登录是默认不选,保存密码数据
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", true),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("name", et_inputname.getText().toString().trim()),
                    new SharedPreferencesUtils.ContentValue("password", et_inputpassword.getText().toString().trim()));
        }
    }

    //获取账号
    private String getAccount() {
        return et_inputname.getText().toString().trim();//去掉空格
    }

    //获取密码
    private String getPassword() {
        return et_inputpassword.getText().toString().trim();//去掉空格
    }

    //登录
    private void login() {

        String address = "http://116.208.3.145:998/entertest_login.php?username="+getAccount()+"&password="+getPassword();

        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            loadCheckBoxState();
// 在这里根据返回内容执行具体的逻辑
            Message message = new Message();
            message.what = SHOW_RESPONSE;
            message.obj = response.toString();
            handler.sendMessage(message);

            if (response.toString().trim().equals("wrong")) {
                Toast.makeText(LoginActivity.this, "账号或者密码错误，请重新输入", Toast.LENGTH_SHORT).show();
            } else {

                Message_RESPONSE = response.toString();
                Intent setIntent = new Intent();
                //setIntent.setClass(LoginActivity.this, ShowCompanyActivity.class);
                setIntent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(setIntent);
                finish();
            }
        }
            @Override
            public void onError(Exception e) {
// 在这里对异常情况进行处理
                Toast.makeText(LoginActivity.this, "登录链接异常", Toast.LENGTH_SHORT).show();
            }
        });
    }
}