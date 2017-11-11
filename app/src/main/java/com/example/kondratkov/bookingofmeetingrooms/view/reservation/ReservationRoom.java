package com.example.kondratkov.bookingofmeetingrooms.view.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kondratkov.bookingofmeetingrooms.MyApplication;
import com.example.kondratkov.bookingofmeetingrooms.R;
import com.example.kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import com.example.kondratkov.bookingofmeetingrooms.model.api.Controller;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.MeetingRoom;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.User;
import com.example.kondratkov.bookingofmeetingrooms.representation.DataTimePepresentation;
import com.example.kondratkov.bookingofmeetingrooms.view.listroom.ListRoomsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationRoom extends AppCompatActivity {

    private static ApiInterface mApiInterface;
    private List<Reservation>mReservations;
    private ReservationItemFragment mReservationItemFragment;
    private DataTimePepresentation dataTimePepresentation;

    @BindView(R.id.tv_reservation_chair)TextView tv_reservation_chair;
    @BindView(R.id.tv_reservation_projector)TextView tv_reservation_projector;
    @BindView(R.id.tv_reservation_blackboard)TextView tv_reservation_blackboard;
    @BindView(R.id.tv_reservation_info)TextView tv_reservation_info;
    @BindView(R.id.linearlayout_fragment_reservation)LinearLayout linearlayout_fragment_reservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_room);
        ButterKnife.bind(this);

        mApiInterface = Controller.getApi();
        dataTimePepresentation = new DataTimePepresentation();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        String j = String.valueOf(getIntent().getIntExtra("MeetingRoomsID", 0));

        mApiInterface.getReservation(getIntent().getIntExtra("MeetingRoomsID", 0)).enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {

                String s = "dd";
                mReservations = response.body();
                try {
                    addTextView();
                    addFragment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
            }
        });
        MyApplication.getInstance().setReservations(mReservations);
    }

    public void addTextView ()throws Exception{
        tv_reservation_chair.setText(String.valueOf(getIntent().getIntExtra("MeetingRoomsNumberChair",0)));
        tv_reservation_projector.setText(getIntent().getBooleanExtra("MeetingRoomsProjector", false) ? R.string.yes : R.string.no);
        tv_reservation_blackboard.setText(getIntent().getBooleanExtra("MeetingRoomsBlackboard", false)? R.string.yes : R.string.no);
        tv_reservation_info.setText(String.valueOf(getIntent().getStringExtra("MeetingRoomsInfo")));
    }

    public void addFragment(){
        mReservationItemFragment = new ReservationItemFragment(mReservations);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.linearlayout_fragment_reservation, mReservationItemFragment).commit();

    }

    @OnClick(R.id.button_reservation_add)
    public void onClickReservationAdd(View view){
        Intent  intent = new Intent(ReservationRoom.this, ReservationAddActivity.class);
        intent.putExtra("arrayStartTime", getArrayStartTime());
        intent.putExtra("arrayEndTime", getArrayEndTime());
        intent.putExtra("numberChair", getIntent().getIntExtra("MeetingRoomsNumberChair",0));
        startActivity(intent);
    }

    private long[] getArrayStartTime(){
        if(mReservations !=null){
            long[] arrayStartTime = new long[mReservations.size()];
            for(int i = 0; i<mReservations.size(); i++){
                arrayStartTime[i] = dataTimePepresentation.getStringToDate(mReservations.get(i).getDateStart()).getTime();
            }
            return arrayStartTime;
        }
        return new long[0];
    }

    private long[] getArrayEndTime(){
        if(mReservations !=null){
            long[] arrayEndTime = new long[mReservations.size()];
            for(int i = 0; i<mReservations.size(); i++){
                arrayEndTime[i] = dataTimePepresentation.getStringToDate(mReservations.get(i).getDateFinish()).getTime();
            }
            return arrayEndTime;
        }
        return new long[0];
    }

    public void testReserv(){
        mReservations = new ArrayList<Reservation>();
        for(int i= 0; i<11; i++){
            mReservations.add(i, new Reservation(i, new MeetingRoom(1, i+12, "name "+String.valueOf(i),true, false, true,"2", "Описание"),new User(1, "213123", "324324"), "2017-11-5 13:00:25", "2017-11-5 13:30:25") );
        }
        mReservations.add(1, new Reservation(10, new MeetingRoom(1, 42, "name "+String.valueOf(2),true, false, true,"2", "Описание"),new User(1, "213123", "324324"), "2017-11-5 8:30:25", "2017-11-5 9:30:25") );
        mReservations.add(2, new Reservation(11, new MeetingRoom(1, 22, "name "+String.valueOf(3),true, false, true,"2", "Описание"),new User(1, "213123", "324324"), "2017-11-5 10:30:25", "2017-11-5 11:30:25") );

        MyApplication.getInstance().setReservations(mReservations);
        try {
            addTextView();
            addFragment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
