package kondratkov.bookingofmeetingrooms.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import kondratkov.bookingofmeetingrooms.model.pojo.ReservationEntity;

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

    @Query("SELECT * FROM reservation where :date_use < date_start AND date_start < :date_plus_15_min")
    public List<ReservationEntity> getAllReservationReminder(int date_use, int date_plus_15_min);

}