package com.example.kondratkov.bookingofmeetingrooms.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.ReservationEntity;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.User;

import java.util.List;

public class Repository {
    AppDatabase mAppDatabase;
    private static ApiInterface mApiInterface;

    public Repository (Context context){
        synchronized (context){
            mAppDatabase = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DB_NAME).build();
        }
    }

    public void addReservation(ReservationEntity reservationEntity){
        long rec = mAppDatabase.getReservationDao().insertReservation(reservationEntity);
    }

    public void updatePerson(ReservationEntity reservationEntity) {
        mAppDatabase.getReservationDao().updateReservation(reservationEntity);
    }

    public void deletePerson(ReservationEntity reservationEntity) {
        mAppDatabase.getReservationDao().deleteReservation(reservationEntity);
    }

    public List<ReservationEntity> getAllReservations() {
        return mAppDatabase.getReservationDao().getAllReservations();
    }

    public ReservationEntity getReservation(int id) {
        return mAppDatabase.getReservationDao().getReservation(id);
    }
    public List<ReservationEntity> getAllReservationReminder(int date_start, int date_finish){
        return mAppDatabase.getReservationDao().getAllReservationReminder(date_start);//, date_finish
    }

    public void addUser(User user){
        long rec = mAppDatabase.getUserDao().insertUser(user);
    }

    public void updateUser(User user) {
        mAppDatabase.getUserDao().updateUser(user);
    }

    public void deleteUser(User user) {
        mAppDatabase.getUserDao().deleteUser(user);
    }

    public User getUser(){return mAppDatabase.getUserDao().getUser();}

    public int getTestUser() {
        return mAppDatabase.getUserDao().getTestUser();
    }
}
