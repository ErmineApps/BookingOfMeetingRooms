package com.example.kondratkov.bookingofmeetingrooms.view.bookinghistory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kondratkov.bookingofmeetingrooms.MyApplication;
import com.example.kondratkov.bookingofmeetingrooms.R;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.MeetingRoom;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.ReservationEntity;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.User;
import com.example.kondratkov.bookingofmeetingrooms.view.listroom.ListRoomsActivity;
import com.example.kondratkov.bookingofmeetingrooms.view.navigation.NavigationViewMyApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingHistory extends AppCompatActivity {

    private List<ReservationEntity> mReservations;

    @BindView(R.id.recylerView_booking_history) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        ButterKnife.bind(this);

        MyApplication.getInstance().getNavigationViewMyApp().setAppCompatActivity(BookingHistory.this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("История бронирования");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        startAdapterList();
    }

    private void startAdapterList() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mReservations = MyApplication.getInstance().getRepository().getAllReservations();

                for(int i =0 ; i<mReservations.size(); i++){
                    Log.d("qwerty", "List_"+mReservations.get(i).getId());
                }

                BookingHistoryAdatpter mBookingHistoryAdapter = new BookingHistoryAdatpter(getApplicationContext(), mReservations);
                mRecyclerView = (RecyclerView)findViewById(R.id.recylerView_booking_history);
                mRecyclerView.setAdapter(mBookingHistoryAdapter);
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
        });


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


}
