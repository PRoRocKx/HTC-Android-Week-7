package com.example.evgeniy.week7.t1;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.evgeniy.week7.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner ndSpinner;
    private Switch ndSwitch;
    private EditText ndEditText;

    @BindView(R.id.drawer_layout)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.nav_view_right)
    NavigationView navigationView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView2.getMenu();
        Button ndButton = menu.findItem(R.id.nav_manage).getActionView().findViewById(R.id.ndButton);
        ndEditText = menu.findItem(R.id.nav_camera).getActionView().findViewById(R.id.editText);
        ndSpinner = menu.findItem(R.id.nav_gallery).getActionView().findViewById(R.id.ndSpinner);
        ndSwitch = menu.findItem(R.id.nav_slideshow).getActionView().findViewById(R.id.ndSwitch);
        ndButton.setOnClickListener(view -> sendDataToFragment());

    }

    private void sendDataToFragment() {

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragmentList) {
            if (fragment instanceof ItemFragment) {
                ((ItemFragment) fragment).setData(ndEditText.getText().toString(), ndSpinner.getSelectedItem().toString(), String.valueOf(ndSwitch.isChecked()));
                drawer.closeDrawer(R.id.nav_view_right);
                return;
            }
        }
        Toast.makeText(this, R.string.fragment_not_found, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void addFragment(String name) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ItemFragment.newInstance(name), name)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            addFragment("camera");
        } else if (id == R.id.nav_gallery) {
            addFragment("gallery");
        } else if (id == R.id.nav_slideshow) {
            addFragment("slideshow");
        } else if (id == R.id.nav_manage) {
            addFragment("manage");
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
