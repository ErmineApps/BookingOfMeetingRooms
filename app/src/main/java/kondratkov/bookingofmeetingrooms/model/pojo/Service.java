package kondratkov.bookingofmeetingrooms.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kondratkov on 11.11.2017.
 */

public class Service {
    @SerializedName("Reservations")
    @Expose
    private Reservation mPushReservation;

    public Reservation getPushReservation() {
        return mPushReservation;
    }

    public void setPushReservation(Reservation pushReservation) {
        mPushReservation = pushReservation;
    }
}
