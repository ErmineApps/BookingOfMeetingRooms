package com.example.kondratkov.bookingofmeetingrooms.view.listroom;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kondratkov.bookingofmeetingrooms.MyApplication;
import com.example.kondratkov.bookingofmeetingrooms.R;
import com.example.kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import com.example.kondratkov.bookingofmeetingrooms.model.api.Controller;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.MeetingRoom;
import com.example.kondratkov.bookingofmeetingrooms.view.bookinghistory.BookingHistory;
import com.example.kondratkov.bookingofmeetingrooms.view.navigation.NavigationViewMyApp;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRoomsActivity extends AppCompatActivity{

    private static ApiInterface mApiInterface;
    private List<MeetingRoom> mMeetingRooms;
    @BindView(R.id.recylerView_listroom) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rooms);

        MyApplication.getInstance().getNavigationViewMyApp().setAppCompatActivity(ListRoomsActivity.this);

        mMeetingRooms = new ArrayList<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.interactive);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mApiInterface = Controller.getApi();
        initializeAdapter();
        //testRequest();

    }

    private void initializeAdapter(){
        ListRoomsAdapter adapter = new ListRoomsAdapter(getApplicationContext(), mMeetingRooms);
        mRecyclerView.setAdapter(adapter);

        mApiInterface.getMeeringRooms().enqueue(new Callback<List<MeetingRoom>>() {
            @Override
            public void onResponse(Call<List<MeetingRoom>> call, Response<List<MeetingRoom>> response) {
                String s = String.valueOf(response.body());
                mMeetingRooms.addAll(response.body());
                mRecyclerView.getAdapter().notifyDataSetChanged();
                Toast.makeText(ListRoomsActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<MeetingRoom>> call, Throwable t) {

            }
        });
    }

    private void testRequest(){
        List<MeetingRoom>meetingRooms =new ArrayList<MeetingRoom>();
        for(int i =0; i<16; i++){
            if(i==3||i==4||i==5){
                meetingRooms.add(i, new MeetingRoom(i, i,"переговорная "+i, false, true, true, "2017-11-5 11:12:25", "fffffffff"));
            }else if(i==2){
                meetingRooms.add(i, new MeetingRoom(i, i,"переговорная "+i, false, true, false, "2017-11-5 16:15:25", "fffffffff"));
            }else if(i%2>0){
                meetingRooms.add(i, new MeetingRoom(i, i,"переговорная "+i, false, false, true, "2017-11-5 12:10:25", "fffffffff"));
            }
            else {
                meetingRooms.add(i, new MeetingRoom(i, i,"переговорная "+i, true, true, true, "nu", "fffffffff"));
            }
        }

        ListRoomsAdapter adapter = new ListRoomsAdapter(ListRoomsActivity.this, meetingRooms);//getApplicationContext()
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();
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

//    @Override
//    public void show(NavigationViewMyApp mNavigationViewMyApp) {
//        this.mNavigationViewMyApp = mNavigationViewMyApp;
//    }



//    class UrlConnectionTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            String result = "";
//
//            String d = "http://app.mmka.info/TopQuestions/GetTopQuestions";
//            OkHttpClient client = new OkHttpClient();
//
//            Request request = new Request.Builder()
//                    .url("http://"+in.get_url()+"/TopQuestions/GetTopQuestions")
//                    .build();
//
//            try {
//                Response response = client.newCall(request).execute();
//                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//                result = response.body().string();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Gson gg = new Gson();
//            mcArray = gg.fromJson(result, TopQuestion[].class);
//            start_s_server();
//            super.onPostExecute(result);
//        }
//    }
}
