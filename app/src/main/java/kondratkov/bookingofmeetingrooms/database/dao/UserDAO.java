package kondratkov.bookingofmeetingrooms.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import kondratkov.bookingofmeetingrooms.model.pojo.User;

import java.util.List;

/**
 * Created by kondratkov on 06.11.2017.
 */
@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertUser(User user);

    @Update
    public void updateUser(User user);

    @Delete
    public void deleteUser(User user);

    @Query("SELECT* FROM User")
    public User getUser();

    @Query("SELECT EXISTS(SELECT* FROM User)")
    public int getTestUser();
}
