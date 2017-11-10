package com.example.kondratkov.bookingofmeetingrooms.view.bookinghistory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kondratkov.bookingofmeetingrooms.R;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.ReservationEntity;
import com.example.kondratkov.bookingofmeetingrooms.representation.DataTimePepresentation;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kondratkov on 05.11.2017.
 */

public class BookingHistoryHolder extends RecyclerView.ViewHolder{
    //@BindView(R.id.tv_item_history_fromtime)
    TextView tv_item_history_fromtime, tv_item_history_totime, tv_item_history_date, tv_item_history_name;


    public BookingHistoryHolder(View itemView) {
        super(itemView);
        //ButterKnife.bind(this, itemView);
        tv_item_history_fromtime = (TextView)itemView.findViewById(R.id.tv_item_history_fromtime);
        tv_item_history_totime = (TextView)itemView.findViewById(R.id.tv_item_history_totime);
        tv_item_history_date = (TextView)itemView.findViewById(R.id.tv_item_history_date);
        tv_item_history_name = (TextView)itemView.findViewById(R.id.tv_item_history_name);
    }

    public void  bind(@NonNull ReservationEntity reservation){
        DataTimePepresentation mDataTimePepresentation = new DataTimePepresentation();
        tv_item_history_name.setText(reservation.getMeetingRoomName());
        try {
            tv_item_history_date.setText(mDataTimePepresentation.getData_ddMMyyyy("2017-11-5 11:12:25", itemView.getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Date dateStart = new Date((long)reservation.getDateStart());
            Date dateFinish = new Date((long)reservation.getDateFinish());
            tv_item_history_fromtime.setText(mDataTimePepresentation.getDate_date_HHmm(dateStart, tv_item_history_fromtime.getContext()));
            tv_item_history_totime.setText(mDataTimePepresentation.getDate_date_HHmm(dateFinish, tv_item_history_totime.getContext()));
        }catch (Exception e){}
    }
}
