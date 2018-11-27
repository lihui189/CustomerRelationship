package com.example.administrator.customerrelationship;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustAdapter extends ArrayAdapter {

    private Context mContext;
    private List<CustSearch> list = null;
    private int resourceId;
    public CustAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public CustAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public CustAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    public CustAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public CustAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
        this.mContext = context;
        this.list = objects;
    }

    public CustAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2){
        final CustSearch custSearch = (CustSearch) getItem(position);
        ViewHolder holder ;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder = new ViewHolder();

            holder.cust_name = (TextView) view
                    .findViewById(R.id.cust_name);
            holder.cust_company =(TextView) view.findViewById(R.id.cust_company);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.cust_name.setText(custSearch.getCustomer_name());
        holder.cust_company.setText(custSearch.getCusunit_name()+"-"+custSearch.getCus_job());
        return view;
    }

    private class ViewHolder {

        TextView cust_name;
        TextView cust_company;
    }
}
