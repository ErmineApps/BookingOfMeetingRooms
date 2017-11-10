package com.example.kondratkov.bookingofmeetingrooms.view.bookinghistory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kondratkov.bookingofmeetingrooms.R;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.ReservationEntity;
import com.example.kondratkov.bookingofmeetingrooms.view.listroom.ListRoomsHolder;

import java.util.List;

/**
 * Created by kondratkov on 05.11.2017.
 */

public class BookingHistoryAdatpter extends RecyclerView.Adapter<BookingHistoryHolder> {

    private Context mContext;
    List<ReservationEntity>mReservations;

    BookingHistoryAdatpter(Context context, List<ReservationEntity> reservations) {
        this.mContext = context;
        this.mReservations = reservations;
    }

    @Override
    public BookingHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        BookingHistoryHolder pvh = new BookingHistoryHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(BookingHistoryHolder holder, int position) {
        holder.bind(mReservations.get(position));
    }

    @Override
    public int getItemCount() {
        int d = 0;
        try{
            d =mReservations.size();
        }catch (Exception e){}
        return d;
    }
}
