package com.example.administrator.customerrelationship;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.example.mylibrarytitle.widget.CommonTitleBar;

public class CustomerManagementActivity extends AppCompatActivity {
    private Context context;
    private EditText custname,custphone,custjob,
                      custcompany, relation,emplyee;
    private Button bt_add;
    private CommonTitleBar titleBar;
    private Spinner sp_region;
    private Spinner sp_khregion;
    private Spinner sp_department;
    private String[] rs_region ;//地区数据
    private ArrayAdapter<String> adapterDepartment;
    private String[][] department = new String[][]{
            {"办公室","总经理助理","党群工作部","樊城营销中心","樊东营维中心","工会","行政保卫部","监察室","客户服务部","企业信息化部",
                    "人力资源部","市场拓展部","网络建设部","无线网优中心","系统集成部","线路维护中心","襄城营维中心","销售渠道服务部","新兴业务部",
                    "业务稽核中心","客户支撑中心","樊西营维中心","政企客户部","智慧城市","政企商客","固网部","高新区"},
            {"副总经理","政企客户部","办公室","销售服务部","新兴业务部","网络部","客服维系中心","业务支撑中心","襄州综合支撑中心","固网部","客服中心","移动销售部"},
            {"枣阳分公司","枣阳分公司副总经理","枣阳市电信公司","综合服务支撑中心","新兴业务中心","销售服务部","存量中心","固网业务中心","移动销售部","网络维护中心","物资供应","客户支撑中心","政企客户部","枣阳市政企客户部 ","枣阳办公室"},
            {"总经理","副总","资深领导","政企客户部","板桥分局长","新兴业务中心","移动部","市场部","客服","企业文化部","固网部","网络部","办公室","监控中心"},
            {"南漳分公司总经理","南漳分公司副总经理","政企客户部","网络部","移动销售部","销售服务部","办公室","设备维护","销售部","城区分局"},
            {"谷城分公司"},
            {"保康政企","办公室","政企客户部","移动销售部","办公室财务","移动业务部","综合服务支撑","网络部"},
            {"销售服务部","财务","移动部","政企客户部","客服中心员工","新兴业务部","客户经理","政个客户经理","商客","网络中心","工会","存量维系中心","老河口分公司"}

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_customer_management);
        initView();
        setClickListeners();
        setRegionSpListeners();
    }


    private void initView() {
        context =this;
        titleBar = (CommonTitleBar) findViewById(R.id.titlebar);
        titleBar.setBackgroundResource(R.drawable.shape_gradient);

        custname = (EditText) findViewById(R.id.custname);
        custphone = (EditText) findViewById(R.id.custphone);
        custjob = (EditText) findViewById(R.id.custjob);
        custcompany = (EditText) findViewById(R.id.custcompany);
        relation = (EditText) findViewById(R.id.relation);
        emplyee = (EditText) findViewById(R.id.emplyee);
        bt_add = (Button)findViewById(R.id.bt_add);

        sp_khregion =(Spinner) findViewById(R.id.sp_khregion);
        sp_region = (Spinner)findViewById(R.id.sp_region);
        Resources res = getResources();
        rs_region = res.getStringArray(R.array.region2);

        sp_department = (Spinner)findViewById(R.id.sp_department);
    }

    private void setClickListeners() {
        titleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON
                        || action == CommonTitleBar.ACTION_LEFT_TEXT) {
                    onBackPressed();
                }
            }
        });

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**/
                if(custname.getText()!=null||custphone.getText()!=null||
                        custjob.getText()!=null||custcompany.getText()!=null||
                        relation.getText()!=null||emplyee.getText()!=null)
                {
                    Toast.makeText(CustomerManagementActivity.this, "数据不能为空", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(CustomerManagementActivity.this, "后台数据新增待更新中，待升级", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRegionSpListeners() {

        sp_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos =sp_region.getSelectedItemPosition();
                //Log.d("p",pos+""+department[pos][0]);
                adapterDepartment = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,department[pos]);
                sp_department.setAdapter(adapterDepartment);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
