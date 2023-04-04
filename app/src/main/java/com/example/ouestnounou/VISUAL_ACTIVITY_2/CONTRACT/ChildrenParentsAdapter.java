package com.example.ouestnounou.VISUAL_ACTIVITY_2.CONTRACT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.R;

import java.util.ArrayList;
import java.util.List;

public class ChildrenParentsAdapter extends BaseAdapter {
    private List<Children> children_list;
    private LayoutInflater mInflater;
    private ChildrenDAO childrenDAO;

    public ChildrenParentsAdapter(Context context, ArrayList<Children> childrenList) {
        children_list = childrenList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        if(children_list != null)
            return children_list.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return children_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.e_parents_contract_list_item, parent, false);
            holder = new ViewHolder();
            holder.timeTextView = (TextView) convertView.findViewById(R.id.name_child);
            holder.waiting = (TextView) convertView.findViewById(R.id.waiting);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.timeTextView.setText( children_list.get(position).getFist_name() + " - " + children_list.get(position).getLast_name());

        if(children_list.get(position).getNurse_accepted() == 1){
            holder.waiting.setText("Accepté");
        }
        else{
            holder.waiting.setText("En attente");
        }


        return convertView;
    }

    static class ViewHolder {
        TextView timeTextView;
        TextView waiting;
    }
}
