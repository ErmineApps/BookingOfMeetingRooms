package com.example.kondratkov.bookingofmeetingrooms.view.reservation;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kondratkov.bookingofmeetingrooms.MyApplication;
import com.example.kondratkov.bookingofmeetingrooms.R;
import com.example.kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import com.example.kondratkov.bookingofmeetingrooms.model.api.Controller;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import com.example.kondratkov.bookingofmeetingrooms.representation.DataTimePepresentation;
import com.example.kondratkov.bookingofmeetingrooms.view.datatimepicker.DateTimePickerFragment;
import com.example.kondratkov.bookingofmeetingrooms.view.datatimepicker.FragmentFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.LocalDateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationAddActivity extends AppCompatActivity {

    @BindView(R.id.editText_reservadd_end_time)EditText editText_reservadd_end_time;
    @BindView(R.id.editText_reservadd_number_of_people)EditText editText_reservadd_number_of_people;
    @BindView(R.id.editText_reservadd_start_time)EditText editText_reservadd_start_time;

    private DataTimePepresentation dataTimePepresentation;
    private static ApiInterface mApiInterface;
    private Date mLocalStartDateTime;
    private Date mLocalEndDateTime;

    private Date mLocalStartDateTimeOld;
    private Date mLocalEndDateTimeOld;
    private int numberChair;
    private Context mContext;

    private long[]arrayStartTime;
    private long[]arrayEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_add);

        mApiInterface = Controller.postApi();
        mContext = ReservationAddActivity.this;
        ButterKnife.bind(this);
        dataTimePepresentation = new DataTimePepresentation();

        mLocalStartDateTime = new Date();
        mLocalEndDateTime = new Date();

        mLocalStartDateTimeOld = new Date();
        mLocalEndDateTimeOld = new Date();

        numberChair = getIntent().getIntExtra("numberChair", 0);
        arrayStartTime = getIntent().getLongArrayExtra("arrayStartTime");
        arrayEndTime = getIntent().getLongArrayExtra("arrayEndTime");
    }

    @OnClick(R.id.button_reservadd_yes)
    public void getbutton_reservadd_yes(View view){
        try {
            if(editText_reservadd_start_time.getText().length()==0){
                Toast.makeText(mContext, "Вы не указали время начала!", Toast.LENGTH_SHORT).show();
            }else if(editText_reservadd_end_time.getText().length()==0){
                Toast.makeText(mContext, "Вы не указали время окончания!", Toast.LENGTH_SHORT).show();
            }else if(Integer.parseInt(String.valueOf(editText_reservadd_number_of_people.getText()))>numberChair){
                alertDialogStart();
            }else{
                request_add();
            }
        }catch (NumberFormatException nfe){
            Toast.makeText(mContext, "не указанно количество человек!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){}
    }

    @OnClick(R.id.button_reservadd_no)
    public void getbutton_reservadd_no(View view){
        ReservationAddActivity.this.finish();
    }

    @OnClick(R.id.editText_reservadd_start_time)
    public void get_reservadd_start_time(View view){
        FragmentManager fragmentManager = getFragmentManager();

        Date dateToUse = new LocalDateTime().toDate();
        DateTimePickerFragment datePickerFragment =
                FragmentFactory.createDatePickerFragment(dateToUse, "The", DateTimePickerFragment.BOTH,
                        new DateTimePickerFragment.ResultHandler() {
                            @Override
                            public void setDate(Date result) {
                                mLocalStartDateTime = result;
                                checkStart();
                            }
                        });
        datePickerFragment.show(fragmentManager, DateTimePickerFragment.DIALOG_TAG);
    }

    @OnClick(R.id.editText_reservadd_end_time)
    public void get_reservadd_end_time(View view){
        FragmentManager fragmentManager = getFragmentManager();

        Date dateToUse = new LocalDateTime().toDate();
        DateTimePickerFragment datePickerFragment =
                FragmentFactory.createDatePickerFragment(dateToUse, "The", DateTimePickerFragment.BOTH,
                        new DateTimePickerFragment.ResultHandler() {
                            @Override
                            public void setDate(Date result) {
                                mLocalEndDateTime = result;
                                endCheck();
                            }
                        });
        datePickerFragment.show(fragmentManager, DateTimePickerFragment.DIALOG_TAG);
    }

    private void updateStartDateTimeTextView() {
        try{
            editText_reservadd_start_time.setText(dataTimePepresentation.getDate_date_ddMMyyyy(mLocalStartDateTime, editText_reservadd_start_time.getContext())+" в "+
                    dataTimePepresentation.getDate_date_HHmm(mLocalStartDateTime, editText_reservadd_start_time.getContext()));
        }
        catch (Exception e){}
    }

    private void updateEndDateTimeTextView() {
        try{
            editText_reservadd_end_time.setText(dataTimePepresentation.getDate_date_ddMMyyyy(mLocalEndDateTime, editText_reservadd_end_time.getContext())+" в "+
                    dataTimePepresentation.getDate_date_HHmm(mLocalEndDateTime, editText_reservadd_end_time.getContext()));
        }
        catch (Exception e){}
    }

    private void checkStart(){
        if(mLocalStartDateTime.getTime()<mLocalStartDateTimeOld.getTime()){
            editText_reservadd_start_time.setText("");
            Toast.makeText(mContext, "Вы указали прошедшее время!", Toast.LENGTH_SHORT).show();
        }else if(arrayStartTime.length==0) {
            updateStartDateTimeTextView();
        }else {
            for(int i =0; i<arrayStartTime.length; i++){
                long d1 = arrayStartTime[i];
                long d2 = mLocalStartDateTime.getTime();
                long d3 = arrayEndTime[i];
                if(arrayStartTime[i]<mLocalStartDateTime.getTime() && mLocalStartDateTime.getTime()<arrayEndTime[i]||arrayStartTime[i] == mLocalStartDateTime.getTime()){
                    Toast.makeText(mContext, "Выбранное время уже занято!", Toast.LENGTH_SHORT).show();
                    i=arrayStartTime.length;
                }else if (i == arrayStartTime.length-1){
                    updateStartDateTimeTextView();
                }
            }
        }
    }

    private void endCheck(){
        if(mLocalEndDateTime.getTime()<mLocalEndDateTimeOld.getTime()){
            editText_reservadd_end_time.setText("");
            Toast.makeText(mContext, "Вы указали прошедшее время!", Toast.LENGTH_SHORT).show();
        }else if(mLocalStartDateTime.getTime()>mLocalEndDateTime.getTime()) {
            editText_reservadd_end_time.setText("");
            Toast.makeText(mContext, "Указанное время меньше начала!", Toast.LENGTH_SHORT).show();
        }else if(arrayEndTime.length==0) {
            updateEndDateTimeTextView();
        }else {
            for(int i =0; i<arrayEndTime.length; i++){
                if(arrayStartTime[i]<mLocalEndDateTime.getTime() && mLocalEndDateTime.getTime()<arrayEndTime[i]){
                    Toast.makeText(mContext, "Выбранное время уже занято!", Toast.LENGTH_SHORT).show();
                    i=arrayEndTime.length;
                }else if(mLocalStartDateTime.getTime()<arrayStartTime[i] && arrayStartTime[i]<mLocalEndDateTime.getTime()){
                    Toast.makeText(mContext, "Выбранное время уже занято!", Toast.LENGTH_SHORT).show();
                    i=arrayEndTime.length;
                }else if (i == arrayEndTime.length-1){
                    updateEndDateTimeTextView();
                }
            }
        }
    }

    private void alertDialogStart(){
        AlertDialog.Builder ad = new AlertDialog.Builder(mContext);
        ad.setTitle("");  // заголовок
        ad.setMessage("Количество мест в комнате меньше чем заявлено человек!"); // сообщение
        ad.setPositiveButton("все равно забронировать", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                try {
                    request_add();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ad.setNegativeButton("отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {

            }
        });
        ad.show();
    }

    private boolean request_add()throws Exception {
        final boolean[] tryResponse = new boolean[1];

        Reservation reservation = new Reservation(dataTimePepresentation.getDateToString(mLocalStartDateTime),
                dataTimePepresentation.getDateToString(mLocalEndDateTime),
                MyApplication.getInstance().getMeetingRoom().getId(),
                MyApplication.getInstance().getUser().getId());

        Gson gson = new Gson();
        String d = gson.toJson(reservation);
        int dd =d.length();

        mApiInterface.addReservationRoom(reservation).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if(response.code()>199 && response.code()<300){
                    tryResponse[0] = true;
                    Toast.makeText(mContext, "Запрос отправлен!", Toast.LENGTH_SHORT).show();
                    ReservationAddActivity.this.finish();
                }else {
                    Toast.makeText(mContext, "Нет сети!", Toast.LENGTH_SHORT).show();
                    tryResponse[0] = false;
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                tryResponse[0] = false;
                Toast.makeText(mContext, "Нет сети!", Toast.LENGTH_SHORT).show();
            }
        });
        return tryResponse[0];
    }
}
