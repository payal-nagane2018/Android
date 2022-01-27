package com.payal.faculty.ui.adminviewlessonplan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.payal.faculty.LessonActivity;
import com.payal.faculty.LessonplanDetails;
import com.payal.faculty.R;
import com.payal.faculty.Utility;

import java.util.ArrayList;

public class AdapterList extends BaseAdapter {
    Context context;
    ArrayList<ListFaculty> listFaculty;

    public AdapterList(Context context,ArrayList<ListFaculty> listFaculty)
    {
        this.context=context;
        this.listFaculty=listFaculty;
    }
    @Override
    public int getCount() {
        return listFaculty.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, final ViewGroup parent) {

        view= LayoutInflater.from(context).inflate(R.layout.custom_list,parent,false);
        final TextView uid=view.findViewById(R.id.txtUId);
        final TextView name=view.findViewById(R.id.txtName);
        final TextView department=view.findViewById(R.id.txtDept);
        final ImageView imgFac=view.findViewById(R.id.imageFaculty);

        //int uid=listFaculty.get(position).getUId();
        uid.setText(""+listFaculty.get(position).getUId());
        name.setText(listFaculty.get(position).getName());
        department.setText(listFaculty.get(position).getDepartment()+"/"+listFaculty.get(position).getYear());

        imgFac.post(new Runnable() {
            @Override
            public void run() {

                imgFac.setImageBitmap(Utility.getImage(listFaculty.get(position).getImages()));

            }
        });
       // imgFac.setImageResource(listFaculty.get(position).getImages());

        view.setOnClickListener(new View.OnClickListener() {
            int ud=Integer.parseInt(uid.getText().toString());
            @Override

            public void onClick(View v) {
                    Intent in = new Intent(parent.getContext(), LessonActivity.class);
                    in.putExtra("U_ID",ud);
                    parent.getContext().startActivity(in);
               // Toast.makeText(context,"Position:"+position,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
