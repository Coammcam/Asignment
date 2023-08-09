package com.example.asignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asignment.Fragment_QuanLiSanPham.FragmentAbout;
import com.example.asignment.Fragment_QuanLiSanPham.FragmentProductManagement;
import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Mappings();
        ActionToolBar();

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(HomeScreen.this, drawerLayout, toolbar, R.string.open, R.string.close
        );

        // set fragment mac dinh khi vao homescreen
        FragmentProductManagement fragmentProductManagement = new FragmentProductManagement();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linearLayout, fragmentProductManagement)
                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.quanLiSanPham) {
                    fragment = new FragmentProductManagement();
                    getSupportActionBar().setTitle(item.getTitle());
                } else if (item.getItemId() == R.id.gioiThieu) {
                    fragment = new FragmentAbout();
                    getSupportActionBar().setTitle(item.getTitle());
                } else if (item.getItemId() == R.id.dangXuat){
                    finish();
                    Toast.makeText(context, "Dang xuat thanh cong", Toast.LENGTH_SHORT).show();
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.linearLayout, fragment)
                        .commit();

                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

    }

    private void Mappings() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        listView = (ListView) findViewById(R.id.lv);
    }
}