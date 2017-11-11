package kondratkov.bookingofmeetingrooms.view.listroom;

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

import kondratkov.bookingofmeetingrooms.MyApplication;
import kondratkov.bookingofmeetingrooms.R;
import kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import kondratkov.bookingofmeetingrooms.model.api.Controller;
import kondratkov.bookingofmeetingrooms.model.pojo.MeetingRoom;
import kondratkov.bookingofmeetingrooms.view.bookinghistory.BookingHistory;
import kondratkov.bookingofmeetingrooms.view.navigation.NavigationViewMyApp;
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
    public ListRoomsAdapter adapter;

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
        try {
            initializeAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //testRequest();

    }

    private void initializeAdapter() throws Exception{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (this) {
                        try {
                            mApiInterface.getMeeringRooms().enqueue(new Callback<List<MeetingRoom>>() {
                                @Override
                                public void onResponse(Call<List<MeetingRoom>> call, Response<List<MeetingRoom>> response) {
                                    String s = String.valueOf(response.body());
                                    mMeetingRooms.clear();
                                    mMeetingRooms.addAll(response.body());
                                    //mRecyclerView.getAdapter().notifyDataSetChanged();
                                    if(mMeetingRooms!=null) {
                                        adapter = new ListRoomsAdapter(getApplicationContext(), mMeetingRooms);
                                        mRecyclerView.setAdapter(adapter);
                                    }
                                }
                                @Override
                                public void onFailure(Call<List<MeetingRoom>> call, Throwable t) {

                                }
                            });


                            wait(10000);
                        } catch (Exception e) {
                        }
                    }
                }
            }
        });

        thread.start();
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
