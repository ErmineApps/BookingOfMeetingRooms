package com.example.kondratkov.bookingofmeetingrooms.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "reservation")
public class ReservationEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER, collate = 1)
    private int mId;
    @ColumnInfo(name = "meetingroomname", typeAffinity = ColumnInfo.TEXT)
    private String mMeetingRoomName;
    @ColumnInfo(name = "date_start" , typeAffinity = ColumnInfo.INTEGER)
    private int mDateStart;
    @ColumnInfo(name = "date_finish", typeAffinity = ColumnInfo.INTEGER)
    private int mDateFinish;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getMeetingRoomName() {
        return mMeetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        mMeetingRoomName = meetingRoomName;
    }

    public int getDateStart() {
        return mDateStart;
    }

    public void setDateStart(int dateStart) {
        mDateStart = dateStart;
    }

    public int getDateFinish() {
        return mDateFinish;
    }

    public void setDateFinish(int dateFinish) {
        mDateFinish = dateFinish;
    }
}