package com.example.administrator.customerrelationship;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrarytitle.widget.CommonTitleBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static com.example.administrator.customerrelationship.LoginActivity.Message_RESPONSE;
import static com.example.administrator.customerrelationship.LoginActivity.SHOW_RESPONSE;

public class ShowCompanyActivity extends AppCompatActivity  {
    private Spinner  sp_region;
    private EditText et_input;
    private TextView tv_search;
    private ListView lv_listView;
    private ListView lv_contact;
    private CommonTitleBar titleBar;
    private ArrayAdapter<String> listViewCompanyAdapter;
    private ArrayAdapter<String> listViewContactAdapter;
    private ArrayList<Company> companyArrayList = new ArrayList<Company>();
    private String[] data;//listView加载的所有数据
    private ArrayList<String> data_item = new ArrayList<String>();//listView加载的数据
    private ArrayList<String> data_serchList;//listView加载搜索的数据
    private ArrayList<String> data_departList;//listView加载搜索的数据

    private String response_relationship;
    private Handler handler = new Handler();
    private PopupWindow popupWindow;
    private List<RelationShip> relationshipList;
    private List<Contact> contactList;
    private String[] rs_region ;//地区数据
    private String[] data_temp;//临时数组数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_company);
        initView();
        initListView();
        setSpListeners();
        setClickListeners();
        setTextListeners();
    }

    private void initView() {
        titleBar = (CommonTitleBar) findViewById(R.id.titlebar);
        titleBar.setBackgroundResource(R.drawable.shape_gradient);
        sp_region = (Spinner)findViewById(R.id.spinner);
        Resources res = getResources();
        rs_region = res.getStringArray(R.array.region);
        et_input = (EditText) findViewById(R.id.et_text);
        tv_search = (TextView) findViewById(R.id.tv_search);
        lv_listView = (ListView) findViewById(R.id.listview);
    }

    private void initListView() {

        Gson gson = new Gson();
        companyArrayList = gson.fromJson(Message_RESPONSE, new
                TypeToken<List<Company>>() {
                }.getType());

        //ListView加载的数据
        data = new String[companyArrayList.size()];
        for (int i = 0; i < companyArrayList.size(); i++) {
            data[i] = companyArrayList.get(i).
                    getCusunit_area().toString() + "-" + companyArrayList.get(i).getCusunit_name().toString();
        }
        //按拼音字母排序
        Comparator comparator = Collator.getInstance(Locale.CHINA);
        Arrays.sort(data, comparator);

/*
        listViewCompanyAdapter = new ArrayAdapter<>(ShowCompanyActivity.this, android.R.layout.simple_list_item_1, data);
        lv_listView.setAdapter(listViewCompanyAdapter);
*/
/*
        //自动加载
        for (int i = 0; i <1600; i++) {
            data_item.add(String.valueOf(data[i]));
        }

        listViewCompanyAdapter = new ArrayAdapter<>(ShowCompanyActivity.this, android.R.layout.simple_list_item_1, data_item);
        lv_listView.setAdapter(listViewCompanyAdapter);
*/
      /*
        //添加listview滚动监听
        lv_listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            //AbsListView view 这个view对象就是listview
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {

                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        Toast.makeText(ShowCompanyActivity.this, "加载数据", Toast.LENGTH_SHORT).show();
                        loadData2();
                    }
                }
            }
            @Override

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            int    lastItem = firstVisibleItem + visibleItemCount - 1 ;
            }
        });
        */
    }
//加载所有
    private void loadData() {
        int count = listViewCompanyAdapter.getCount() + 1;
        for (int i = count; i < count + 1600; i++) {
            if(i<data.length)
            {
                listViewCompanyAdapter.addAll(String.valueOf(data[i]));
            }else {
                Toast.makeText(ShowCompanyActivity.this, "已加载完数据", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
//按区域加载
  private void loadData2() {
    int count = listViewCompanyAdapter.getCount() + 1;
    for (int i = count; i < count + 1600; i++) {
        if(i<data_departList.size())
        {
            listViewCompanyAdapter.addAll(data_departList.get(i));
        }else {
            Toast.makeText(ShowCompanyActivity.this, "已加载完数据", Toast.LENGTH_SHORT).show();
            break;
        }
    }
}


    private void setSpListeners() {

        sp_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //加载的据
                data_departList = new ArrayList<String>();

                for (int i = 0; i < data.length; i++) {
                    data_temp = data[i].split("-");
                    if(rs_region[position].equals("全市")){
                            data_departList.add(String.valueOf(data[i]));
                    }
                    else if(rs_region[position].equals(data_temp[0])){
                        data_departList.add(data[i]);
                    }
                }
                listViewCompanyAdapter = new ArrayAdapter<String>(ShowCompanyActivity.this,
                        android.R.layout.simple_list_item_1, data_departList);
                lv_listView.setAdapter(listViewCompanyAdapter);
                lv_listView.invalidateViews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    //搜索文本监听
    private void setTextListeners() {
        /**
         * 对编辑框添加文本改变监听，搜索的具体功能在这里实现
         * 很简单，文本该变的时候进行搜索。关键方法是重写的onTextChanged（）方法。
         */
        et_input.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 如果adapter不为空的话就根据编辑框中的内容来过滤数据
                //  listViewAdapter.getFilter().filter(s);
                refreshListView(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void refreshListView(String searchString) {

        /*
        if (searchString == null || searchString.trim().length() == 0) {//搜索字符串为空时，显示全部
            listViewCompanyAdapter = new ArrayAdapter<>(ShowCompanyActivity.this,
                    android.R.layout.simple_list_item_1, data);
            lv_listView.setAdapter(listViewCompanyAdapter);
        }
*/
        data_serchList = new ArrayList<String>();
        for (String s : data_departList) {
            if (s.toLowerCase().contains(searchString.toLowerCase().replace(" ",""))) {//ListView 数据项中包含搜索字符串（不区分字符大小）
                data_serchList.add(s);
            }
        }
        listViewCompanyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data_serchList);
        lv_listView.setAdapter(listViewCompanyAdapter);
        lv_listView.invalidateViews();
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

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_input.setText("");
            }
        });

        lv_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView tv_company = (TextView) view.findViewById(android.R.id.text1);
                String s_company = tv_company.getText().toString();
                String[] ss_company = s_company.split("-");
                String address = "http://116.208.3.145:998/entertest_relation.php?cusunit_area=" + ss_company[0] + "&cusunit_name=" + ss_company[1];
                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = response.toString();
                        handler.sendMessage(message);
                        response_relationship = response;
                        // 在这里根据返回内容执行具体的逻辑
                        if (response_relationship.toString().trim().equals("nothing")) {
                            Toast.makeText(ShowCompanyActivity.this, "目前该单位下未有关系用户", Toast.LENGTH_SHORT).show();
                        } else {
                           loginRelation();
                        }
                    }
                    @Override
                    public void onError(Exception e) {
                        // 在这里对异常情况进行处理
                        Toast.makeText(ShowCompanyActivity.this, "链接异常", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loginRelation() {
        initViewRelation();
        initListViewRelation();
    }

    private void initViewRelation() {
        final View popupwindow_view = getLayoutInflater().inflate(
                R.layout.layout_popwindow_item, null, false);
        lv_contact = (ListView) popupwindow_view.findViewById(R.id.contactsListView);
        ImageView ivExit = (ImageView) popupwindow_view.findViewById(R.id.iExit);
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }
        });
        popupWindow = new PopupWindow(popupwindow_view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setAnimationStyle(R.style.Animi);
        popupWindow.showAtLocation(findViewById(R.id.listview), Gravity.CENTER,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void initListViewRelation() {
        Gson gson = new Gson();
        relationshipList = gson.fromJson(response_relationship.toString(), new
                TypeToken<List<RelationShip>>() {}.getType());
        Bitmap contactPhoto = BitmapFactory.decodeResource(getResources(),
                R.drawable.preson_icon);
        contactList = new ArrayList();
        for (RelationShip relationShip : relationshipList) {
            Contact person = new Contact(contactPhoto, relationShip.getCustomer_name(),
                    relationShip.getCus_phone(), relationShip.getCus_job());
            contactList.add(person);
        }
            listViewContactAdapter = new ContactAdapter(ShowCompanyActivity.this, R.layout.adapter_contact, contactList);
            lv_contact.setAdapter(listViewContactAdapter);

            lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                final Bundle bundle = new Bundle();
                                @Override
                                public void onItemClick(AdapterView<?> arg0, View v, final int pos,
                                                        long arg3) {
                                    TextView tv_cutphone = v.findViewById(R.id.per_number);

                                    for (RelationShip relationShip : relationshipList) {

                                        if(relationShip.cus_phone ==tv_cutphone.getText().toString().trim()) {
                                            bundle.putString("customer_name",relationShip.customer_name);
                                            bundle.putString("cus_job",relationShip.cus_job);
                                            bundle.putString("cus_phone",relationShip.cus_phone);
                                            bundle.putString("relation_type",relationShip.relation_type);
                                            bundle.putString("depart", relationShip.employee_depart);
                                            bundle.putString("employee", relationShip.employee_name);
                                        }
                                    }
                                    Intent intent = new Intent(ShowCompanyActivity.this, ShowRelationDetailActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    //popupWindow.dismiss();
                                }});
    }
}


