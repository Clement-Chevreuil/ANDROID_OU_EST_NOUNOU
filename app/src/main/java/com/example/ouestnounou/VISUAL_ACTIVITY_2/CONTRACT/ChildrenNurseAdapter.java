package com.example.ouestnounou.VISUAL_ACTIVITY_2.CONTRACT;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.R;

import java.util.ArrayList;
import java.util.List;

public class ChildrenNurseAdapter extends BaseAdapter {
    private List<Children> children_list;
    private LayoutInflater mInflater;
    private ChildrenDAO childrenDAO;

    public ChildrenNurseAdapter(Context context, ArrayList<Children> childrenList) {
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
            convertView = mInflater.inflate(R.layout.e_nurse_contract_list_item, parent, false);
            holder = new ViewHolder();
            holder.timeTextView = (TextView) convertView.findViewById(R.id.name_child);
            holder.accept = (Button) convertView.findViewById(R.id.accept);
            holder.refuse = (Button) convertView.findViewById(R.id.refuse);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        childrenDAO = new ChildrenDAO(parent.getContext());

        Children children = children_list.get(position);
        holder.timeTextView.setText( children.getFist_name() + " - " + children.getLast_name());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = parent.getRootView().getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
                String category = prefs.getString("category", "");
                int id_category = prefs.getInt("id", 0);
                Nurse nurse =new Nurse();
                nurse.setId(id_category);
                children.setNurse(nurse);
                children.setNurse_accepted(1);
                childrenDAO.update(children);
                children_list.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nurse nurse = new Nurse();
                nurse.setId(-1);
                children.setNurse(nurse);
                childrenDAO.update(children);
                children_list.remove(position);
                notifyDataSetChanged();
            }
        });



        return convertView;
    }

    static class ViewHolder {
        public Button accept, refuse;
        TextView timeTextView;
    }
}
