package com.example.kondratkov.bookingofmeetingrooms.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kondratkov on 06.11.2017.
 */

public class ServicePush {

    @SerializedName("PushYesReservation")
    @Expose
    private ArrayList<Reservation> mPushReservation;

    @SerializedName("PushMeetingReminder")
    @Expose
    private MeetingRoom mMeetingRoom;

    public ServicePush(ArrayList<Reservation> pushReservation, MeetingRoom meetingRoom) {
        mPushReservation = pushReservation;
        mMeetingRoom = meetingRoom;
    }

    public ArrayList<Reservation> getPushReservation() {
        return mPushReservation;
    }

    public void setPushReservation(ArrayList<Reservation> pushReservation) {
        mPushReservation = pushReservation;
    }

    public MeetingRoom getMeetingRoom() {
        return mMeetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        mMeetingRoom = meetingRoom;
    }
}
