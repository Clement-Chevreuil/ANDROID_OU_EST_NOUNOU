package com.example.ouestnounou.VISUAL_ACTIVITY_2_PARENTS.CHILDREN;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.MODEL.CalendarEvent;
import com.example.ouestnounou.MODEL.Children;
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
        return children_list.size();
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

        holder.delChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childrenDAO.delete(children.getId());
                // Mettre ici le code à exécuter lorsque le bouton est cliqué
                // Par exemple, vous pouvez supprimer l'enfant associé à ce bouton
                children_list.remove(position);
                notifyDataSetChanged();
            }
        });



        return convertView;
    }

    static class ViewHolder {
        public Button delChild;
        TextView timeTextView;
    }
}
