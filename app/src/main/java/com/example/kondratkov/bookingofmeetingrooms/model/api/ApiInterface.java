package com.example.kondratkov.bookingofmeetingrooms.model.api;

import com.example.kondratkov.bookingofmeetingrooms.model.pojo.MeetingRoom;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Service;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by kondratkov on 07.11.2017.
 */

public interface ApiInterface {

    //запрос списка комнат
    @GET("/api/MeetingRooms/GetMeetingRooms")
    Call<List<MeetingRoom>>getMeeringRooms();

    //запрос списка брони
    @GET("/api/Reservations/GetReservationsForMeetingRoom/{id_room}")
    Call<List<Reservation>>getReservation(@Path("id_room")int id_room);

    //запрос на бронирование
    @POST("/api/Reservations/AddReservations")
    Call<Reservation> addReservationRoom(@Body Reservation reservation);

    //запрос на сервер через сервис
    @GET("/api/Services/GetServicesID/{id_user}")
    Call<List<Service>>getService(@Path("id_user")int idUser);

    @GET("/api/Services/GetServicesDeleteID/{id_user}")
    Call<List<Service>>getServicesDeleteID(@Path("id_user")int idUser);

    //запрос на регистрацию
    @POST("/api/Users/Registration")
    Call<User>register(@Body User user);

    //запрос на аутентификацию
    @POST("/api/Users/Authentication")
    Call<User>authentication(@Body User user);

}
