package kondratkov.bookingofmeetingrooms.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kondratkov.bookingofmeetingrooms.database.dao.ReservationDAO;
import kondratkov.bookingofmeetingrooms.database.dao.UserDAO;
import kondratkov.bookingofmeetingrooms.model.pojo.ReservationEntity;
import kondratkov.bookingofmeetingrooms.model.pojo.User;

/**
 * Created by kondratkov on 07.11.2017.
 */

@Database(entities = {ReservationEntity.class, User.class}, version = 10)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "Booking_db";

    public abstract ReservationDAO getReservationDao();

    public abstract UserDAO getUserDao();
}