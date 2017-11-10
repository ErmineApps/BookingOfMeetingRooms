package com.example.kondratkov.bookingofmeetingrooms.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kondratkov on 06.11.2017.
 */

public class MeetingRoom {

    @SerializedName("Id")
    @Expose
    private int mId;

    @SerializedName("NumberChair")
    @Expose
    private int mNumberChair;

    @SerializedName("Name")
    @Expose
    private String mName;

    @SerializedName("Projector")
    @Expose
    private boolean mProjector;

    @SerializedName("Blackboard")
    @Expose
    private boolean mBlackboard;

    @SerializedName("FreedomStatus")
    @Expose
    private boolean mFreedomStatus;

    @SerializedName("DateReserv")
    @Expose
    private String mDateReserv;

    @SerializedName("Info")
    @Expose
    private String mInfo;

    public MeetingRoom(int id, int numberChair, String name, boolean projector, boolean blackboard, boolean freedomStatus, String dateReserv, String info) {
        mId = id;
        mNumberChair = numberChair;
        mName = name;
        mProjector = projector;
        mBlackboard = blackboard;
        mFreedomStatus = freedomStatus;
        mDateReserv = dateReserv;
        mInfo = info;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getNumberChair() {
        return mNumberChair;
    }

    public void setNumberChair(int numberChair) {
        mNumberChair = numberChair;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isProjector() {
        return mProjector;
    }

    public void setProjector(boolean projector) {
        mProjector = projector;
    }

    public boolean isBlackboard() {
        return mBlackboard;
    }

    public void setBlackboard(boolean blackboard) {
        mBlackboard = blackboard;
    }

    public boolean isFreedomStatus() {
        return mFreedomStatus;
    }

    public void setFreedomStatus(boolean freedomStatus) {
        mFreedomStatus = freedomStatus;
    }

    public String getDateReserv() {
        return mDateReserv;
    }

    public void setDateReserv(String dateReserv) {
        mDateReserv = dateReserv;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }
}
