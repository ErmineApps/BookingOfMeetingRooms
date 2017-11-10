package com.example.kondratkov.bookingofmeetingrooms.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.kondratkov.bookingofmeetingrooms.model.pojo.ReservationEntity;

import java.util.List;

/**
 * Created by kondratkov on 07.11.2017.
 */
@Dao
public interface ReservationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertReservation(ReservationEntity reservationEntity);

    @Update
    public void updateReservation(ReservationEntity reservationEntity);

    @Delete
    public void deleteReservation(ReservationEntity reservationEntity);

    @Query("SELECT * FROM reservation")
    public List<ReservationEntity> getAllReservations();

    @Query("SELECT * FROM reservation where id = :id")
    public ReservationEntity getReservation(int id);

    @Query("SELECT * FROM reservation where :date_start<date_start") //<:date_finish
    public List<ReservationEntity> getAllReservationReminder(int date_start);//, int date_finish
}