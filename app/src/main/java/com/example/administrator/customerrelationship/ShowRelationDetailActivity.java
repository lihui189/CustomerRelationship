package com.example.administrator.customerrelationship;

import android.Manifest;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowRelationDetailActivity extends Activity {
    private Context mContext;
    private View ivExit;
    private TextView tvdepart;
    private TextView tvemployee;
    private Bundle bundle_getEmployee;
    private TextView tvPer_name2;
    private TextView tvPer_number2;
    private TextView tvPer_job2;
    private TextView tvRelation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_relation_detail);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initView();
        clicklistener();
    }


    private void initView() {
        tvdepart = (TextView) findViewById(R.id.tvdepart);
        tvemployee = (TextView) findViewById(R.id.tvemployee);
        ivExit = (ImageView) findViewById(R.id.imgExit);
        tvPer_name2 = (TextView) findViewById(R.id.tvPer_name2);
        tvPer_number2 = (TextView) findViewById(R.id.tvPer_number2);
        tvPer_job2  = (TextView) findViewById(R.id.tvPer_job2);
        tvRelation = (TextView) findViewById(R.id.tvRelation);
        bundle_getEmployee = getIntent().getExtras();
        tvdepart.setText(bundle_getEmployee.getString("depart"));
        tvemployee.setText(bundle_getEmployee.getString("employee"));
        tvPer_name2.setText(bundle_getEmployee.getString("customer_name"));
        tvPer_number2.setText(bundle_getEmployee.getString("cus_phone"));
        tvPer_job2.setText(bundle_getEmployee.getString("cus_job"));
        tvRelation.setText(bundle_getEmployee.getString("relation_type"));

    }

    private void clicklistener() {
        ivExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        tvPer_number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diaintent = new Intent();
                diaintent.setAction(Intent.ACTION_DIAL);
                diaintent.setData(Uri.parse("tel:"+tvPer_number2.getText().toString()));
                startActivity(diaintent);
            }
        });
    }

}
