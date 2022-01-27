package com.payal.faculty.uifac.ui.facultyviewlessonplan;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.payal.faculty.DetailsOfLessonPlanActivity;
import com.payal.faculty.LessonplanDetails;
import com.payal.faculty.R;

import java.util.ArrayList;

public class AdapterLessonList extends BaseAdapter {
    Context context;
    ArrayList<ListLessonPlan> listLessonPlan;

    public AdapterLessonList(Context context, ArrayList<ListLessonPlan> listLessonPlan)
    {
        this.context=context;
        this.listLessonPlan=listLessonPlan;
    }

    @Override
    public int getCount() {
        return listLessonPlan.size();
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

        view= LayoutInflater.from(context).inflate(R.layout.custom_list_lesson_plan,parent,false);

        final TextView txtId=(TextView) view.findViewById(R.id.textId);
        final TextView txtDate=(TextView) view.findViewById(R.id.txtDate);
        final TextView txtTime=(TextView) view.findViewById(R.id.txtTime);

        txtId.setText(""+listLessonPlan.get(position).getIdi());
        txtDate.setText(listLessonPlan.get(position).getDate());
        txtTime.setText(listLessonPlan.get(position).getTime());

        view.setOnClickListener(new View.OnClickListener() {
            int idi=Integer.parseInt(txtId.getText().toString());
            @Override

            public void onClick(View v) {
                Intent in = new Intent(parent.getContext(), DetailsOfLessonPlanActivity.class);
                in.putExtra("IDI",idi);
                parent.getContext().startActivity(in);
                // Toast.makeText(context,"Position:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
