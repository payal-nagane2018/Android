package com.payal.faculty;


import android.os.Bundle;


import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.payal.faculty.ui.adminaddfaculty.AdminAddFacultyFragment;
import com.payal.faculty.ui.adminmodifyfaculty.AdminModifyFacultyFragment;
import com.payal.faculty.ui.adminviewlessonplan.AdminViewLessonPlanFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdminNavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AdminViewLessonPlanFragment()).commit();

            navigationView.setCheckedItem(R.id.nav_viewlessonplan);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_viewlessonplan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AdminViewLessonPlanFragment()).commit();
                break;
            case R.id.nav_addfaculty:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AdminAddFacultyFragment()).commit();
                
                break;
            case R.id.nav_modifyfaculty:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AdminModifyFacultyFragment()).commit();
                break;
            case R.id.nav_logout:
                finish();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
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
