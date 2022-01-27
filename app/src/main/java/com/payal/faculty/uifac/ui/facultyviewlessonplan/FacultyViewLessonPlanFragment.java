package com.payal.faculty.uifac.ui.facultyviewlessonplan;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.payal.faculty.DatabaseHelper;
import com.payal.faculty.DatabaseHelperF;
import com.payal.faculty.R;
import com.payal.faculty.ui.adminviewlessonplan.AdapterList;
import com.payal.faculty.ui.adminviewlessonplan.ListFaculty;

import java.util.ArrayList;

public class FacultyViewLessonPlanFragment extends Fragment {

    ListView lsLessonPlan;
    public int uid;

    ArrayList<ListLessonPlan> listLessonPlans;
    AdapterLessonList adapterList;
   @Nullable
   @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.faculty_fragment_viewlessonplan,container,false);

        lsLessonPlan=view.findViewById(R.id.list_lesson_plan);
        listLessonPlansShow();
        adapterList=new AdapterLessonList(getActivity(),listLessonPlans);
        lsLessonPlan.setAdapter(adapterList);
        return view;
    }

    public FacultyViewLessonPlanFragment(int id)
    {
        uid=id;
     //   Toast.makeText(getActivity(),""+uid,Toast.LENGTH_SHORT).show();
    }

    public void listLessonPlansShow()
    {
        listLessonPlans=new ArrayList<ListLessonPlan>();

        DatabaseHelper db=new DatabaseHelper(getContext());
        Cursor c=db.getUidData(""+uid);
        while (c.moveToNext())
        {

            //byte[] blob = c.getBlob(c.getColumnIndex("PHOTO"));
            listLessonPlans.add(new ListLessonPlan(c.getInt(0),c.getString(2),c.getString(3)));
        }

//        listLessonPlans.add(new ListLessonPlan("10/2/2020","8:30 AM to 9:30 AM"));

    }

}