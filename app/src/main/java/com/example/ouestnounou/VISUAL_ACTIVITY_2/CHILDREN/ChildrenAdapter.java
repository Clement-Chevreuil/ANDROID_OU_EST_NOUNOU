package com.example.ouestnounou.VISUAL_ACTIVITY_2.CHILDREN;

import static android.content.Context.MODE_PRIVATE;

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

public class ChildrenAdapter extends BaseAdapter {
    private List<Children> children_list;
    private LayoutInflater mInflater;
    private ChildrenDAO childrenDAO;

    public ChildrenAdapter(Context context, ArrayList<Children> childrenList) {
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
            convertView = mInflater.inflate(R.layout.e_children_list_item, parent, false);
            holder = new ViewHolder();
            holder.timeTextView = (TextView) convertView.findViewById(R.id.name_child);
            holder.delChild = (Button) convertView.findViewById(R.id.del_child);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        childrenDAO = new ChildrenDAO(parent.getContext());

        com.example.ouestnounou.MODEL.Children children = children_list.get(position);
        holder.timeTextView.setText( children.getFist_name() + " - " + children.getLast_name());

        SharedPreferences prefs = convertView.getContext().getSharedPreferences("session", MODE_PRIVATE);
        int id_category = prefs.getInt("id", 0);
        String category = prefs.getString("category", "");

        View finalConvertView = convertView;
        holder.delChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category.equals(finalConvertView.getResources().getString(R.string.nurse))){
                    Nurse nurse = new Nurse();
                    nurse.setId(-1);
                    children.setNurse(nurse);
                    childrenDAO.update(children);
                    children_list.remove(position);
                    notifyDataSetChanged();
                }
                else{
                    childrenDAO.delete(children.getId());
                    children_list.remove(position);
                    notifyDataSetChanged();
                }

            }
        });



        return convertView;
    }

    static class ViewHolder {
        public Button delChild;
        TextView timeTextView;
    }
}
