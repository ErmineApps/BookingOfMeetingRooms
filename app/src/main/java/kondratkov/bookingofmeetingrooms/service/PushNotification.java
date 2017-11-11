package kondratkov.bookingofmeetingrooms.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.view.View;

import kondratkov.bookingofmeetingrooms.MyApplication;
import kondratkov.bookingofmeetingrooms.R;
import kondratkov.bookingofmeetingrooms.model.api.ApiInterface;
import kondratkov.bookingofmeetingrooms.model.api.Controller;
import kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import kondratkov.bookingofmeetingrooms.model.pojo.ReservationEntity;
import kondratkov.bookingofmeetingrooms.model.pojo.Service;
import kondratkov.bookingofmeetingrooms.representation.DataTimePepresentation;
import kondratkov.bookingofmeetingrooms.view.listroom.ListRoomsActivity;

import org.joda.time.LocalDateTime;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by kondratkov on 07.11.2017.
 */

public class PushNotification {

    private Context mContext;
    DataTimePepresentation mDataTimePepresentation = new DataTimePepresentation();
    List<Service>mServicePushes;
    private static ApiInterface mApiInterface;
    private int notificationCounter = 0;

    public PushNotification(Context context){
        mContext = context;
        mApiInterface = Controller.getApi();
    }

    public void checkPush(){

        mApiInterface.getService(MyApplication.getInstance().getUser().getId()).enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {

                String s = "dd";
                mServicePushes = response.body();
            }
            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
            }
        });
        mApiInterface.getServicesDeleteID(MyApplication.getInstance().getUser().getId()).enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {}
            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
            }
        });

        if(mServicePushes!=null){
            for (Service service:mServicePushes) {
                pushReservation(service.getPushReservation());
            }
        }
        pushReminder();
    }

    private void pushReservation(Reservation reservations){
        try {
            if(reservations.isStatus()) {
                sendNotification("Подтверждение бронировании комнаты ","Подтверждение бронировании комнаты "
                        + mDataTimePepresentation.getData_HHmm(reservations.getDateStart(),mContext), "в "+mDataTimePepresentation.getData_HHmm(reservations.getDateStart(),mContext));
                ReservationEntity reservationEntity = new ReservationEntity();
                reservationEntity.setDateStart((int) mDataTimePepresentation.getStringToDate(reservations.getDateStart()).getTime());
                reservationEntity.setDateFinish((int) mDataTimePepresentation.getStringToDate(reservations.getDateFinish()).getTime());
                reservationEntity.setStatusReminder(0);
                reservationEntity.setMeetingRoomName(reservations.getMeetingRoom().getName());
                MyApplication.getInstance().getRepository().addReservation(reservationEntity);
            }
            else {
                if(reservations.isStatus()) {
                    sendNotification("Вам отказали в бронировании комнаты ","Вам отказали в бронировании комнаты "+reservations.getMeetingRoom().getName(), reservations.getMeetingRoom().getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pushReminder(){
        Date dateToUse = new LocalDateTime().toDate();
        for (ReservationEntity reservation:MyApplication.getInstance().getRepository().getAllReservationReminder((int)dateToUse.getTime(), (int)dateToUse.getTime()+900000)) {
            if(reservation.getStatusReminder()==0){
                try {
                    sendNotification("Напоминание ","Вы забронировали комнату "+reservation.getMeetingRoomName()+ " на "
                                    +mDataTimePepresentation.getData_long_HHmm((long)reservation.getDateStart(),mContext)
                            , " на " +mDataTimePepresentation.getData_long_HHmm((long)reservation.getDateStart(),mContext));
                    reservation.setStatusReminder(1);
                    MyApplication.getInstance().getRepository().updateReservation(reservation);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendNotification(String sTicker, String sContentTitle, String sContentText) {

        Intent notificationIntent =  new Intent(mContext, ListRoomsActivity.class);;

        PendingIntent contentIntent = PendingIntent.getActivity(mContext,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = mContext.getResources();


        Notification.Builder builder = new Notification.Builder(mContext);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_menu_gallery)
                // большая картинка
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_menu_send))
                .setTicker(res.getString(R.string.action_about)) //
                .setTicker(sTicker)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("dfdfdsfsdf") //
                .setContentTitle(sContentTitle)
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText(sContentText); //
        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) mContext
                .getSystemService(NOTIFICATION_SERVICE);
        notificationCounter++;
        notificationManager.notify(notificationCounter, notification);
    }

}
