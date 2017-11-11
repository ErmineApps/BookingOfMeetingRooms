package com.example.kondratkov.bookingofmeetingrooms.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.kondratkov.bookingofmeetingrooms.MyApplication;
import com.example.kondratkov.bookingofmeetingrooms.R;

import com.example.kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import com.example.kondratkov.bookingofmeetingrooms.model.api.Controller;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.ReservationEntity;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.User;
import com.example.kondratkov.bookingofmeetingrooms.service.MyIntentService;
import com.example.kondratkov.bookingofmeetingrooms.view.listroom.ListRoomsActivity;
import com.example.kondratkov.bookingofmeetingrooms.view.navigation.NavigationViewMyApp;
import com.example.kondratkov.bookingofmeetingrooms.view.profile.LoginActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    NavigationViewMyApp mNavigationViewMyApp;
    private static ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiInterface = Controller.getApi();

        Intent intentService = new Intent(this, MyIntentService.class);
        startService(intentService);

        mNavigationViewMyApp =new NavigationViewMyApp(MainActivity.this);
        MyApplication.getInstance().setNavigationViewMyApp(mNavigationViewMyApp);
        MyApplication.getInstance().createdata(this);



        autoLogin();
        //testLogin();
    }

    private void autoLogin(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (MyApplication.getInstance().getRepository().getTestUser() == 1) {
                    MyApplication.getInstance().setUser(MyApplication.getInstance().getRepository().getUser());
                    Intent intent = new Intent(MainActivity.this, ListRoomsActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

                    MainActivity.this.finish();
                }
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
