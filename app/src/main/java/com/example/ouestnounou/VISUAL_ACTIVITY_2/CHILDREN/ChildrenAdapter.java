package com.example.ouestnounou.VISUAL_ACTIVITY_2.CHILDREN;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.ouestnounou.DAO.ChildrenDAO;
import com.example.ouestnounou.DAO.NurseDAO;
import com.example.ouestnounou.DAO.ParentsDAO;
import com.example.ouestnounou.MODEL.Children;
import com.example.ouestnounou.MODEL.Nurse;
import com.example.ouestnounou.MODEL.Parents;
import com.example.ouestnounou.R;

import java.util.ArrayList;
import java.util.List;

public class ChildrenAdapter extends BaseAdapter {
    private List<Children> children_list;
    private LayoutInflater mInflater;
    private ChildrenDAO childrenDAO;
    Parents parents;
    Nurse nurse;
    SharedPreferences prefs;
    String category;
    View finalConvertView;
    ParentsDAO parentsDAO;
    NurseDAO nurseDAO;
    com.example.ouestnounou.MODEL.Children children;
    int positionClick, id_category;
    View viewClick;
    ViewGroup viewgroupClick;

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

        positionClick = position;
        viewClick = convertView;
        viewgroupClick = parent;
        children = children_list.get(position);

        childrenDAO = new ChildrenDAO(parent.getContext());
        parentsDAO = new ParentsDAO(parent.getContext());
        nurseDAO = new NurseDAO(parent.getContext());

        prefs = convertView.getContext().getSharedPreferences("session", MODE_PRIVATE);
        id_category = prefs.getInt("id", 0);
        category = prefs.getString("category", "");


        convertView = mInflater.inflate(R.layout.e_children_list_item, parent, false);
        holder = new ViewHolder();
        holder.timeTextView = (TextView) convertView.findViewById(R.id.name_child);
        holder.delChild = (Button) convertView.findViewById(R.id.del_child);
        holder.mail = (Button) convertView.findViewById(R.id.mail);
        holder.phone = (Button) convertView.findViewById(R.id.phone);
        holder.editChild = (Button) convertView.findViewById(R.id.edit_child);
        holder.infos = (TextView) convertView.findViewById(R.id.infos_child);
        convertView.setTag(holder);


        holder.delChild.setOnClickListener(clickListener);
        holder.phone.setOnClickListener(clickListener);
        holder.mail.setOnClickListener(clickListener);


        if(category.equals(parent.getRootView().getResources().getString(R.string.nurse))){
            holder.delChild.setVisibility(View.GONE);
            holder.editChild.setVisibility(View.GONE);
            parents = parentsDAO.getParent(children_list.get(position).getParents().getId());
            holder.timeTextView.setText( children.getFirstName() + " - " + children.getLastName());
            holder.infos.setText( children.getSex() + " - " + children.getBirth() + "\nParents : " + parents.getLastName() + " " + parents.getFirstName() + "\naddresse :" + parents.getCity() + " " + parents.getaddress() + "\n" );
        }
        else{
            nurse = nurseDAO.getNurseById(children_list.get(position).getNurse().getId());
            holder.timeTextView.setText( children.getFirstName() + " - " + children.getLastName());
            if(nurse == null){
                holder.infos.setText( children.getSex() + " - " + children.getBirth() + "\n" + "Nounou : Pas de nounou");
                holder.phone.setVisibility(View.GONE);
                holder.mail.setVisibility(View.GONE);
            }
            else{
                holder.infos.setText( children.getSex() + " - " + children.getBirth() + "\n" + "Nounou : " + nurse.getLastName() + " " + nurse.getFirstName() + "\naddresse :" + nurse.getCity() + " " + nurse.getaddress() );
            }

        }


        return convertView;
    }

    static class ViewHolder {
        public Button delChild;
        public Button editChild;
        public Button phone;
        public Button mail;
        TextView timeTextView, infos;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.del_child :
                    if(category.equals(finalConvertView.getResources().getString(R.string.nurse))){
                        Nurse nurse = new Nurse();
                        nurse.setId(-1);
                        children.setNurse(nurse);
                        childrenDAO.update(children);
                        children_list.remove(positionClick);
                        notifyDataSetChanged();
                    }
                    else{
                        childrenDAO.delete(children.getId());
                        children_list.remove(positionClick);
                        notifyDataSetChanged();
                    }
                    break;
                case R.id.phone :
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    Activity activity = (Activity) viewClick.getContext();
                    if (ActivityCompat.checkSelfPermission(viewClick.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Log.e("here", "in check");
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                    else {
                        Log.e("here", "in function");
                        if(category.equals(viewgroupClick.getRootView().getResources().getString(R.string.nurse))){
                            callIntent.setData(Uri.parse("tel:" + parents.getPhone()));
                            viewClick.getContext().startActivity(callIntent);
                        }
                        else{
                            if(nurse != null){
                                callIntent.setData(Uri.parse("tel:" + nurse.getPhone()));
                                viewClick.getContext().startActivity(callIntent);
                            }
                        }
                    }
                    break;
                case R.id.mail :
                    Intent emailIntent;
                    if(category.equals(viewgroupClick.getRootView().getResources().getString(R.string.nurse))){
                        emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", parents.getMail(), null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sujet de l'e-mail");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Contenu de l'e-mail");
                        viewClick.getContext().startActivity(Intent.createChooser(emailIntent, "Envoyer un e-mail..."));
                    }
                    else{
                        if(nurse != null)
                        {
                            emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", nurse.getMail(), null));
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sujet de l'e-mail");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, "Contenu de l'e-mail");
                            viewClick.getContext().startActivity(Intent.createChooser(emailIntent, "Envoyer un e-mail..."));
                        }
                    }
                    break;
            }
        }
    };
}
