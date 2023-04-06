package com.example.ouestnounou.VISUAL_ACTIVITY_2.CALENDAR;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.MODEL.CalendarEvent;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.R;

import java.util.List;

public class CalendarEventAdapter extends BaseAdapter {
    private List<CalendarEvent> mEvents;
    private LayoutInflater mInflater;

    public CalendarEventAdapter(Context context, List<CalendarEvent> events) {
        mEvents = events;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    @Override
    public Object getItem(int position) {
        return mEvents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ChildrenDAO childrenDAO = new ChildrenDAO(convertView.getContext());

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.e_calendar_list_item, parent, false);
            holder = new ViewHolder();
            holder.timeTextView = (TextView) convertView.findViewById(R.id.timeTextView);
            holder.acceptedCheckBox = (CheckBox) convertView.findViewById(R.id.acceptedCheckBox);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        CalendarEvent event = mEvents.get(position);

        Children child = childrenDAO.getChildrenByIDEasy(event.getChildren().getId());
        holder.timeTextView.setText(event.getDate() + " - " + event.getStartTime() + " - " + event.getEndTime() + " : " + child.getFirstName() + " " + child.getLastName());
        holder.acceptedCheckBox.setChecked(event.isAccepted());
        holder.acceptedCheckBox.setEnabled(false);

        return convertView;
    }

    static class ViewHolder {
        TextView dateTextView;
        TextView timeTextView;
        CheckBox acceptedCheckBox;
    }
}
