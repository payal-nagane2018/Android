package com.payal.faculty;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.payal.faculty.uifac.ui.facultyaddlessonplan.FacultyAddLessonPlanFragment;
import com.payal.faculty.uifac.ui.facultymodifylessonplan.FacultyModifyLessonPlanFragment;
import com.payal.faculty.uifac.ui.facultyviewlessonplan.FacultyViewLessonPlanFragment;


public class FacultyNavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.faculty_navigation);

        Toolbar toolbar = findViewById(R.id.toolbarfac);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layoutfac);

        NavigationView navigationView=findViewById(R.id.nav_viewfac);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_openfac,
                R.string.navigation_drawer_closefac);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Intent oI=getIntent();
        id=oI.getIntExtra("USER_ID",0);


        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerfac,
                    new FacultyViewLessonPlanFragment(id)).commit();

            navigationView.setCheckedItem(R.id.nav_viewlessonplanfac);
        }

    }

    @Override

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_viewlessonplanfac:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerfac,
                        new FacultyViewLessonPlanFragment(id)).commit();
                break;
            case R.id.nav_addlessonplanfac:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerfac,
                        new FacultyAddLessonPlanFragment(id)).commit();
                break;
            case R.id.nav_modifylessonplanfac:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerfac,
                        new FacultyModifyLessonPlanFragment(id)).commit();
                break;
            case R.id.nav_logoutfac:
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}
