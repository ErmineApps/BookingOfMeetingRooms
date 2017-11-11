package kondratkov.bookingofmeetingrooms.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kondratkov on 06.11.2017.
 */

public class Reservation {
    @SerializedName("Id")
    @Expose
    private int mId;

    @SerializedName("MeetingRooms")
    @Expose
    MeetingRoom mMeetingRoom;

    @SerializedName("Users")
    @Expose
    User mUser;

    @SerializedName("DateStart")
    @Expose
    String mDateStart;

    @SerializedName("DateFinish")
    @Expose
    String mDateFinish;

    @SerializedName("MeetingRoom_Id")
    @Expose
    int MeetingRoom_Id;

    @SerializedName("User_id")
    @Expose
    int User_id;

    @SerializedName("Status")
    @Expose
    Boolean Status= null;

    public Reservation(MeetingRoom meetingRoom, User user, String dateStart, String dateFinish) {
        mMeetingRoom = meetingRoom;
        mUser = user;
        mDateStart = dateStart;
        mDateFinish = dateFinish;
    }

    public Reservation(String dateStart, String dateFinish, int meetingRoom_Id, int user_id) {
        mDateStart = dateStart;
        mDateFinish = dateFinish;
        MeetingRoom_Id = meetingRoom_Id;
        User_id = user_id;
    }

    public Reservation(int id, MeetingRoom meetingRoom, User user, String dateStart, String dateFinish) {
        mId = id;
        mMeetingRoom = meetingRoom;
        mUser = user;
        mDateStart = dateStart;
        mDateFinish = dateFinish;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
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

    public String getDateStart() {
        return mDateStart;
    }

    public void setDateStart(String dateStart) {
        mDateStart = dateStart;
    }

    public String getDateFinish() {
        return mDateFinish;
    }

    public void setDateFinish(String dateFinish) {
        mDateFinish = dateFinish;
    }

    public int getMeetingRoom_Id() {
        return MeetingRoom_Id;
    }

    public void setMeetingRoom_Id(int meetingRoom_Id) {
        MeetingRoom_Id = meetingRoom_Id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
