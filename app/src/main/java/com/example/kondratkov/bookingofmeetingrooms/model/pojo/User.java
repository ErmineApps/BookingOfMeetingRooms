package com.example.kondratkov.bookingofmeetingrooms.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kondratkov on 06.11.2017.
 */
@Entity(tableName = "User")
public class User {

    @SerializedName("Id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER, collate = 1)
    private int mId;

    @SerializedName("UserName")
    @Expose
    @ColumnInfo(name = "UserName", typeAffinity = ColumnInfo.TEXT)
    private String mUserName;

    @SerializedName("Password")
    @Expose
    @ColumnInfo(name = "Password", typeAffinity = ColumnInfo.TEXT)
    private String mPassword;

    public User(int id, String userName, String password) {
        mId = id;
        mUserName = userName;
        mPassword = password;
    }

    public User() {

    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
