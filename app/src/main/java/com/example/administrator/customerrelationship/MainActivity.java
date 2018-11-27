package com.example.administrator.customerrelationship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import static com.example.administrator.customerrelationship.LoginActivity.Message_RESPONSE;

public class MainActivity extends AppCompatActivity {

    private TextView tvCount;
    private Button  bt_s1,bt_s2,bt_admin;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClicklisler();
        try {
            JSONArray jsonArray = new JSONArray(Message_RESPONSE);
            //Log.d("d",Message_RESPONSE);
            count = jsonArray.length();
            tvCount.setText("累计："+count+"单位");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onClicklisler() {
        bt_s1.setOnClickListener(new ButtonClick());
        bt_s2.setOnClickListener(new ButtonClick());
        bt_admin.setOnClickListener(new ButtonClick());
    }

    private void init() {
        tvCount = (TextView)findViewById(R.id.tv_count);
        bt_s1 = (Button)findViewById(R.id.bt_s1);
        bt_s2 =(Button)findViewById(R.id.bt_s2);
        bt_admin =(Button)findViewById(R.id.bt_admin);
    }
    class ButtonClick implements View.OnClickListener{
        Intent  intent=null;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_s1:
                    intent=new Intent(MainActivity.this, ShowCompanyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.bt_s2:
                    intent=new Intent(MainActivity.this, CustomerInquiry.class);
                    startActivity(intent);
                    break;
                case R.id.bt_admin:
//                    intent=new Intent(MainActivity.this, CustomerManagementActivity.class);
//                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"管理更新中",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
