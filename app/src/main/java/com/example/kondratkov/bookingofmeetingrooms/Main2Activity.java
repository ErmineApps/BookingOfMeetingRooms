package com.example.kondratkov.bookingofmeetingrooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import com.example.kondratkov.bookingofmeetingrooms.model.api.Controller;
import com.example.kondratkov.bookingofmeetingrooms.view.listroom.ListRoomsActivity;
import com.example.kondratkov.bookingofmeetingrooms.view.navigation.NavigationViewMyApp;
import com.example.kondratkov.bookingofmeetingrooms.view.reservation.ReservationItemFragment;

import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    private static ApiInterface mApiInterface;


    NavigationViewMyApp mNavigationViewMyApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);

        mApiInterface = Controller.getApi();

        int d = getIntent().getIntExtra("a", 0);
        mNavigationViewMyApp = new NavigationViewMyApp(Main2Activity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ReservationItemFragment mReservationItemFragment = new ReservationItemFragment();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Intent intent = new Intent(Main2Activity.this, ListRoomsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
