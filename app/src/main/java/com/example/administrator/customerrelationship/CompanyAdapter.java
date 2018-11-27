package com.example.administrator.customerrelationship;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CompanyAdapter extends ArrayAdapter {
    private Context mContext;
    private List<Company> list = null;
    private int resourceId;
    public CompanyAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public CompanyAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public CompanyAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    public CompanyAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public CompanyAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
        this.mContext = context;
        this.list = objects;
    }

    public CompanyAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Company company = (Company) getItem(position);
        ViewHolder holder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder = new ViewHolder();
            holder.area = (TextView) view
                    .findViewById(R.id.tv_area);
            holder.company = (TextView) view
                    .findViewById(R.id.tv_company);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.area.setText(company.getCusunit_area());
        holder.company.setText(company.getCusunit_name());
        return view;
    }

    private class ViewHolder {
        TextView area;
        TextView company;

    }
}
