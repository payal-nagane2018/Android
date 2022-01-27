package com.payal.faculty.ui.adminviewlessonplan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.payal.faculty.DatabaseHelper;
import com.payal.faculty.R;

import java.util.ArrayList;

public class AdminViewLessonPlanFragment extends Fragment {

    ListView lsFaculty;
  // SearchView shFaculty;
    ArrayList<ListFaculty> faculty;
    AdapterList adapterFaculty;

   @Nullable
   @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.admin_fragment_viewlessonplan,container,false);

        lsFaculty=view.findViewById(R.id.list_faculty);
       // shFaculty=view.findViewById(R.id.search_faculty);

        listShow();

//        shFaculty.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//              //  faculty.getFilter.filter(query);
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

//        lsFaculty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int i, long id)
//           {
//               if(i==1) {
//                   Intent intent = new Intent(getActivity(), LessonplanDetails.class);
//                   getActivity().startActivity(intent);
//               }
//               else  if(i==2) {
//                   Intent intent = new Intent(getActivity(), LessonplanDetails.class);
//                   getActivity().startActivity(intent);
//               }
//               else  if(i==3) {
//                   Intent intent = new Intent(getActivity(), LessonplanDetails.class);
//                   getActivity().startActivity(intent);
//               }
//               else  if(i==4) {
//                   Intent intent = new Intent(getActivity(), LessonplanDetails.class);
//                   getActivity().startActivity(intent);
//               }
//
//              // intent.putExtra("txt1", strDes[i]);
//                   //intent.putExtra("txt2",strDes[i]);
//                   //intent.putExtra("img",images[i]);
//
//
//           }
//       });
        return view;
    }

    private void listShow() {
        faculty=new ArrayList<ListFaculty>();
        DatabaseHelper db=new DatabaseHelper(getContext());
        Cursor c=db.getAllData();
        while (c.moveToNext())
        {

            byte[] blob = c.getBlob(c.getColumnIndex("PHOTO"));
            faculty.add(new ListFaculty(c.getInt(0),c.getString(3),c.getString(5),c.getString(6),blob));
        }
        adapterFaculty=new AdapterList(getContext(),faculty);
        lsFaculty.setAdapter(adapterFaculty);
    }

}