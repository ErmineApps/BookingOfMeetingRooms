package com.example.kondratkov.bookingofmeetingrooms.service;

import android.content.Context;

import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.ServicePush;
import com.example.kondratkov.bookingofmeetingrooms.representation.DataTimePepresentation;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kondratkov on 07.11.2017.
 */

public class PushNotification {

    private Context mContext;
    DataTimePepresentation mDataTimePepresentation = new DataTimePepresentation();

    public PushNotification(Context context){
        mContext = context;
    }

    public void startCheckingMess(ServicePush servicePush){
        if(servicePush!=null){
            if(servicePush.getPushReservation().size()!=0){
                pushReservation(servicePush.getPushReservation());
            }
        }
        pushReminder();
    }

    private void pushReservation(List<Reservation>reservations){
        for(int i=0; i<reservations.size(); i++){
            try {
                startPush("Подтверждение бронировании комнаты "+reservations.get(i).getMeetingRoom().getName()+" в "+ mDataTimePepresentation.getData_HHmm(reservations.get(i).getDateStart(),mContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void pushReservationNo(List<Reservation>reservations){
        for(int i=0; i<reservations.size(); i++){
            startPush("Вам отказали в бронировании комнаты "+reservations.get(i).getMeetingRoom().getName());
        }
    }

    private void pushReminder(){

    }

    private void startPush(String pushMessage){

    }

}
