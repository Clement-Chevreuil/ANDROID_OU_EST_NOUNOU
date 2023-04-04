package com.example.ouestnounou.VISUAL_ACTIVITY_2.CHILDREN;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
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
            holder.mail = (Button) convertView.findViewById(R.id.mail);
            holder.phone = (Button) convertView.findViewById(R.id.phone);
            holder.editChild = (Button) convertView.findViewById(R.id.edit_child);
            holder.infos = (TextView) convertView.findViewById(R.id.infos_child);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        childrenDAO = new ChildrenDAO(parent.getContext());
        ParentsDAO parentsDAO = new ParentsDAO(parent.getContext());
        NurseDAO nurseDAO = new NurseDAO(parent.getContext());


        com.example.ouestnounou.MODEL.Children children = children_list.get(position);

        SharedPreferences prefs = convertView.getContext().getSharedPreferences("session", MODE_PRIVATE);
        int id_category = prefs.getInt("id", 0);
        String category = prefs.getString("category", "");



        if(category.equals(parent.getRootView().getResources().getString(R.string.nurse))){
            holder.delChild.setVisibility(View.GONE);
            holder.editChild.setVisibility(View.GONE);
            parents = parentsDAO.getParent(children_list.get(position).getParents().getId());
            holder.timeTextView.setText( children.getFist_name() + " - " + children.getLast_name());
            holder.infos.setText( children.getSex() + " - " + children.getBirth() + "\nParents : " + parents.getLast_name() + " " + parents.getFist_name() + "\nAdresse :" + parents.getCity() + " " + parents.getAdress() + "\n" );
        }
        else{
            nurse = nurseDAO.getNurseById(children_list.get(position).getNurse().getId());
            holder.timeTextView.setText( children.getFist_name() + " - " + children.getLast_name());
            if(nurse == null){
                holder.infos.setText( children.getSex() + " - " + children.getBirth() + "\n" + "Nounou : Pas de nounou");
                holder.phone.setVisibility(View.GONE);
                holder.mail.setVisibility(View.GONE);
            }
            else{
                holder.infos.setText( children.getSex() + " - " + children.getBirth() + "\n" + "Nounou : " + nurse.getLast_name() + " " + nurse.getFist_name() + "\nAdresse :" + nurse.getCity() + " " + nurse.getAdress() );
            }

        }

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

        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);

                if(category.equals(parent.getRootView().getResources().getString(R.string.nurse))){
                    callIntent.setData(Uri.parse("tel:" + parents.getPhone()));
                }
                else{
                    if(nurse != null){
                        callIntent.setData(Uri.parse("tel:" + nurse.getPhone()));
                    }
                }

                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                v.getContext().startActivity(callIntent);
            }
        });

        holder.mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent;
                if(category.equals(parent.getRootView().getResources().getString(R.string.nurse))){
                    emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", parents.getMail(), null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sujet de l'e-mail");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Contenu de l'e-mail");
                    v.getContext().startActivity(Intent.createChooser(emailIntent, "Envoyer un e-mail..."));
                }
                else{
                    if(nurse != null)
                    {
                        emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", nurse.getMail(), null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sujet de l'e-mail");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Contenu de l'e-mail");
                        v.getContext().startActivity(Intent.createChooser(emailIntent, "Envoyer un e-mail..."));
                    }
                }
            }
        });


        return convertView;
    }

    static class ViewHolder {
        public Button delChild;
        public Button editChild;
        public Button phone;
        public Button mail;
        TextView timeTextView, infos;
    }
}
