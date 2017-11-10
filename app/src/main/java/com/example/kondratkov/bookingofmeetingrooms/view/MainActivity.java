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

    private void testLogin(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                User user = new User(5, "rrrr", "dsfsdf");
                MyApplication.getInstance().getRepository().addUser(user);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

                MainActivity.this.finish();
            }
        });

    }






    @OnClick(R.id.buttonMain)
    public void onOn(View view){

//        mApiInterface.getadf().enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                String s = String.valueOf(response.body());
//                mUsers.addAll(response.body());
//                //mRecyclerView.getAdapter().notifyDataSetChanged();
//                Log.d("qwert",")))____+"+mUsers.size());
//                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//
//            }
//        });


//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                List<ReservationEntity> reservationEntities = MyApplication.getInstance().getReservationRepository().getAllReservations();
//                Log.d("qwerty", "e33____________");
//                Log.d("qwerty", "e_"+reservationEntities.toString());
//                Log.d("qwerty", "_"+reservationEntities.get(reservationEntities.size()-1).getId());


//                for(int i =0 ; i<reservationEntities.size(); i++){
//                    Log.d("qwerty", "_"+reservationEntities.get(i).getId());
//                }
//            }
//        });


    }
    @OnClick(R.id.button2)
    public void ononf(View view){

        Log.d("qwerty", "1234!!!!!");
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

         ReservationEntity reservationEntity = new ReservationEntity();
                        reservationEntity.setMeetingRoomName("MeetingRoomName");
                        reservationEntity.setDateStart(2);
                        reservationEntity.setDateFinish(4);
                MyApplication.getInstance().getRepository().addReservation(reservationEntity);

                List<ReservationEntity> reservationEntities = MyApplication.getInstance().getRepository().getAllReservations();
                Log.d("qwerty", "e33____________");
                Log.d("qwerty", "e_"+reservationEntities.toString());
                Log.d("qwerty", "_1_ "+reservationEntities.get(reservationEntities.size()-1).getMeetingRoomName());
                Log.d("qwerty", "_2_ "+reservationEntities.get(reservationEntities.size()-1).getDateStart());
                Log.d("qwerty", "_3_ "+reservationEntities.get(reservationEntities.size()-1).getDateFinish());


                for(int i =0 ; i<reservationEntities.size(); i++){
                    Log.d("qwerty", "List_"+reservationEntities.get(i).getId());
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
