package com.example.kondratkov.bookingofmeetingrooms.view.reservation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kondratkov.bookingofmeetingrooms.R;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import com.example.kondratkov.bookingofmeetingrooms.view.listroom.ListRoomsActivity;

import java.util.List;

/**
 * Created by kondratkov on 04.11.2017.
 */

public class ReservationRoomAdapter extends RecyclerView.Adapter<ReservationRoomHolder> {

    private Context mContext;
    List<Reservation>mRecyclerViews;

    ReservationRoomAdapter(Context mContext, List<Reservation>mReservations){
        this.mContext = mContext;
        this.mRecyclerViews = mReservations;
    }

    @Override
    public ReservationRoomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_reservation, parent, false);
        ReservationRoomHolder reservationRoomHolder = new ReservationRoomHolder(v);
        return reservationRoomHolder;
    }

    @Override
    public void onBindViewHolder(ReservationRoomHolder holder, int position) {
        holder.bind(mRecyclerViews.get(position));
    }

    @Override
    public int getItemCount() {
        if(mRecyclerViews !=null)
            return mRecyclerViews.size();
        return 0;
    }
}
