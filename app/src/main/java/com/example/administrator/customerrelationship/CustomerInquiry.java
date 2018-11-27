package com.example.administrator.customerrelationship;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.mylibrarytitle.widget.CommonTitleBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class CustomerInquiry extends AppCompatActivity {
    private CommonTitleBar titleBar;
    private TextView tvCustomSearch;
    private EditText etCustomSearch;
    private Button bt_CustomSearch;
    private ListView lv_CustomSearch;
    private  String Message_Cust =null;
    private ArrayList<CustSearch> custSearchList = new ArrayList<>();
    private List<CustSearch> custList;
    private ArrayAdapter<String> custListAdapter;
    private   final int SHOW_CUST = 0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_CUST:
                    //在这里可以进行UI操作
                    Message_Cust = (String) msg.obj;

                    if (Message_Cust.toString().trim().equals("nothing")) {
                        Toast.makeText(CustomerInquiry.this, "用户不存在", Toast.LENGTH_SHORT).show();
                    } else {

                    Gson gson = new Gson();
                    custSearchList = gson.fromJson(Message_Cust, new
                            TypeToken<List<CustSearch>>() {
                            }.getType());

                    custList = new ArrayList();
                    for (CustSearch custSearch : custSearchList) {
                        CustSearch cust = new CustSearch(custSearch.getCustomer_name(), custSearch.getCusunit_name(),
                                custSearch.getCus_job());
                        custList.add(cust);
                    }
                    custListAdapter = new CustAdapter(CustomerInquiry.this, R.layout.adapter_cust, custList);
                    lv_CustomSearch.setAdapter(custListAdapter);
                    lv_CustomSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                               final Bundle bundle = new Bundle();

                        @Override
                                                               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                                   TextView tv_cust_company = view.findViewById(R.id.cust_company);

                                                                   for (CustSearch cust : custSearchList) {
                                                                       if (cust.getCus_job().equals(tv_cust_company.getText().toString().
                                                                               trim().split("-")[1])) {
                                                                           bundle.putString("customer_name", cust.getCustomer_name());
                                                                           bundle.putString("cus_job", cust.getCus_job());
                                                                           bundle.putString("cus_phone", cust.getCus_phone());
                                                                           bundle.putString("relation_type", cust.getRelation_type());
                                                                           bundle.putString("depart", cust.getEmployee_depart());
                                                                           bundle.putString("employee", cust.getEmployee_name());
                                                                       }

                                                                   }

                                                                   Intent intent = new Intent(CustomerInquiry.this, ShowRelationDetailActivity.class);
                                                                   intent.putExtras(bundle);
                                                                   startActivity(intent);
                                                               }
                                                           }
                    );

                   }
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_inquiry);
        initView();
        listner();
    }

    private void initView() {
        titleBar = (CommonTitleBar) findViewById(R.id.titlebar);
        titleBar.setBackgroundResource(R.drawable.shape_gradient);
        tvCustomSearch = (TextView)findViewById(R.id.tvCustomSearch);
        etCustomSearch = (EditText)findViewById(R.id.etCustomSearch);
        bt_CustomSearch = (Button)findViewById(R.id.bt_CustomSearch);
        lv_CustomSearch = (ListView)findViewById(R.id.lv_customer);
    }

    private void listner() {
        titleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON
                        || action == CommonTitleBar.ACTION_LEFT_TEXT) {
                    onBackPressed();
                }
            }
        });

        bt_CustomSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCustomSearch.getText().toString().trim().isEmpty()) {
                    Toast.makeText(CustomerInquiry.this, "输入客户不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    enquiry();
                    // 在这里根据返回内容执行具体的逻辑
                }
            }
        });
    }
 private void enquiry() {
        String address = "http://116.208.3.145:998/entertest_customer_name_search.php?"
                +"customer_name="+etCustomSearch.getText().toString().trim();

        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
// 在这里根据返回内容执行具体的逻辑
                Message message = new Message();
                message.what = SHOW_CUST;
                message.obj = response.toString();
                //然后将消息发送出去
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
// 在这里对异常情况进行处理
                Toast.makeText(CustomerInquiry.this, "数据返回异常", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

