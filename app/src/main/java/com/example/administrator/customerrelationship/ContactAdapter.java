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

public class ContactAdapter extends ArrayAdapter {
    private Context mContext;
    private List<Contact> list = null;
    private int resourceId;
    public ContactAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ContactAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ContactAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    public ContactAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
        this.mContext = context;
        this.list = objects;
    }

    public ContactAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup arg2){
        final Contact contact = (Contact) getItem(position);
        ViewHolder holder ;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder = new ViewHolder();
            holder.per_icon = (ImageView) view
                    .findViewById(R.id.per_icon);
            holder.per_name = (TextView) view
                    .findViewById(R.id.per_name);
            holder.per_num = (TextView) view
                    .findViewById(R.id.per_number);
            holder.per_job =(TextView) view.findViewById(R.id.per_job);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.per_icon.setImageResource(R.drawable.preson_icon);
        holder.per_name.setText(contact.getName());
        holder.per_num.setText(contact.getPhone());
        holder.per_job.setText(contact.getJob());
        return view;
    }

    private class ViewHolder {
        ImageView per_icon;
        TextView per_name;
        TextView per_num;
        TextView per_job;
    }
}
