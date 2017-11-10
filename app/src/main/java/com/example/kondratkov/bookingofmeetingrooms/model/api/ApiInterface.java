package com.example.kondratkov.bookingofmeetingrooms.model.api;

import android.text.Editable;

import com.example.kondratkov.bookingofmeetingrooms.model.pojo.MeetingRoom;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.ServicePush;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.User;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by kondratkov on 02.11.2017.
 */

public interface ApiInterface {

    //запрос списка комнат
    @GET("/api/MeetingRooms/GetMeetingRooms")
    Call<List<MeetingRoom>>getMeeringRooms();

    //запрос списка брони
    @GET("/api/Reservations/GetReservations/{id_room}")
    Call<List<Reservation>>getReservation(@Path("id_room")int id_room);

    //запрос на бронирование
    @POST("/api/Reservations/AddReservations")
    Call<Reservation> addReservationRoom(@Body Reservation reservation);

    //запрос на сервер через сервис
    @GET("/api/Service/GetService/{id_user}")
    Call<ServicePush>getService(@Path("id_user")int idUser);

    //запрос на регистрацию
    @POST("/api/Users/Registration")
    Call<User>register(@Body User user);

    //запрос на аутентификацию
    @POST("/api/Users/Authentication")
    Call<User>authentication(@Body User user);

}
