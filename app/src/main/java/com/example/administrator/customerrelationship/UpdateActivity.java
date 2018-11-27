package com.example.administrator.customerrelationship;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class UpdateActivity extends AppCompatActivity {
    private static final String TAG=" UpdateActivity";
    private String newVerCode;
    private int verCode;
    private String verName;
    private String newVerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getVsinoJson();
    }

    public  int getVerCode(Context context) {
        verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    "com.example.administrator.customerrelationship", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(UpdateActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return verCode;
    }

    public  String getVerName(Context context) {
        verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    "com.example.administrator.customerrelationship", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(UpdateActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return verName;
    }

    private void getVsinoJson() {

        String address = "http://116.208.3.145:998/apk/ver.json";
        String address2 = "http://10.36.163.199/ver.json";

        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
// 在这里根据返回内容执行具体的逻辑
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        newVerName = jsonObject.getString("verName");
                        newVerCode = jsonObject.getString("verCode");
                    }
                    //版本比较
                     compare();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Exception e) {
           // 在这里对异常情况进行处理
                Toast.makeText(UpdateActivity.this, "版本检测异常", Toast.LENGTH_SHORT).show();
                Intent setIntent = new Intent();
                setIntent.setClass(UpdateActivity.this, LoginActivity.class);
                startActivity(setIntent);
                finish();
            }
        });
    }

    private void compare() {
        if (Integer.parseInt(newVerCode) > getVerCode(this))
        {
            doNewVersionUpdate();
        } else {
            //login(); // 提示当前为最新版本
            //finish();
            Toast.makeText(UpdateActivity.this, "当前为最新版本", Toast.LENGTH_SHORT).show();
            Intent setIntent = new Intent();
            setIntent.setClass(UpdateActivity.this, LoginActivity.class);
            startActivity(setIntent);
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(1800);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    finish();
                };
            }.start();
        }
    }
    private void doNewVersionUpdate() {

        StringBuffer sb = new StringBuffer();
        sb.append("当前版本:");
        sb.append(getVerName(this));
        sb.append(" Code:");
        sb.append(getVerCode(this));
        sb.append(", 发现新版本:");
        sb.append(newVerName);
        sb.append(" Code:");
        sb.append(newVerCode);
        sb.append(", 是否更新?");
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("软件更新")
                .setMessage(sb.toString())
                // 设置内容
                .setPositiveButton("更新",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //从服务器端下载最新apk
                                // downloadApk();
                                Intent intent_loginDownload = new Intent();
                                intent_loginDownload .setClass(UpdateActivity.this,DownloadActivity.class);
                                UpdateActivity.this.startActivity(intent_loginDownload);
                            }
                        })
                .setNegativeButton("暂不更新",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // 点击"取消"按钮之后退出程序
                                //跳转到登录界面 ;
                                Intent setIntent = new Intent();
                                setIntent.setClass(UpdateActivity.this, LoginActivity.class);
                                startActivity(setIntent);
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }
}
