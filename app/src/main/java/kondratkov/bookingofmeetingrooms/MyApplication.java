package kondratkov.bookingofmeetingrooms;

import android.app.Application;
import android.content.Context;

import kondratkov.bookingofmeetingrooms.database.Repository;
import kondratkov.bookingofmeetingrooms.model.pojo.MeetingRoom;
import kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import kondratkov.bookingofmeetingrooms.model.pojo.User;
import kondratkov.bookingofmeetingrooms.view.navigation.NavigationViewMyApp;

import java.util.List;

/**
 * Created by kondratkov on 05.11.2017.
 */

public class MyApplication extends Application {

    private List<Reservation> mReservations;
    private Reservation mReservation;
    private MeetingRoom mMeetingRoom;
    private User mUser;
    private NavigationViewMyApp mNavigationViewMyApp;
    private Repository mRepository;
    private Context mContext;


    private static MyApplication singleton;
    // Возвращает экземпляр данного класса
    public static MyApplication getInstance() {
        return singleton;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        singleton = this;

    }
    public void createdata(Context context){
        mRepository = new Repository(context);
    }

    public NavigationViewMyApp getNavigationViewMyApp() {
        return mNavigationViewMyApp;
    }

    public void setNavigationViewMyApp(NavigationViewMyApp navigationViewMyApp) {
        mNavigationViewMyApp = navigationViewMyApp;
    }

    public List<Reservation> getReservations() {
        return mReservations;
    }

    public void setReservations(List<Reservation> reservations) {
        mReservations = reservations;
    }

    public Reservation getReservation() {
        return mReservation;
    }

    public void setReservation(Reservation reservation) {
        mReservation = reservation;
    }

    public MeetingRoom getMeetingRoom() {
        return mMeetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        mMeetingRoom = meetingRoom;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Repository getRepository() {
        return mRepository;
    }

    public void setRepository(Repository Repository) {
        mRepository = Repository;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

}
