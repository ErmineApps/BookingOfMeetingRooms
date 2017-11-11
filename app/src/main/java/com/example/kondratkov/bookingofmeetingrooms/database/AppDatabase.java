package com.example.kondratkov.bookingofmeetingrooms.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.kondratkov.bookingofmeetingrooms.database.dao.ReservationDAO;
import com.example.kondratkov.bookingofmeetingrooms.database.dao.UserDAO;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.ReservationEntity;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.User;

/**
 * Created by kondratkov on 07.11.2017.
 */

@Database(entities = {ReservationEntity.class, User.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "Booking_db";

    public abstract ReservationDAO getReservationDao();

    public abstract UserDAO getUserDao();

}