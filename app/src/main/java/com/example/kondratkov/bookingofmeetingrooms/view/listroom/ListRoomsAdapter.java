package com.example.kondratkov.bookingofmeetingrooms.view.listroom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kondratkov.bookingofmeetingrooms.MyApplication;
import com.example.kondratkov.bookingofmeetingrooms.R;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.MeetingRoom;
import com.example.kondratkov.bookingofmeetingrooms.view.reservation.ReservationRoom;

import java.util.List;

/**
 * Created by kondratkov on 01.11.2017.
 */

public class ListRoomsAdapter extends RecyclerView.Adapter<ListRoomsHolder>{

    private Context mContext;
    List<MeetingRoom> mMeetingRooms;

    ListRoomsAdapter(Context mContext, List<MeetingRoom> mMeetingRooms){
        this.mMeetingRooms = mMeetingRooms;
        this.mContext = mContext;
    }

    @Override
    public ListRoomsHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_room, viewGroup, false);
        ListRoomsHolder pvh = new ListRoomsHolder(v);

        return pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final ListRoomsHolder holder, int position) {
        holder.bind(mMeetingRooms.get(position));
        holder.setClickListener(new ListRoomsItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent= new Intent(mContext, ReservationRoom.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("MeetingRoomsID", mMeetingRooms.get(position).getId());
                intent.putExtra("MeetingRoomsName", mMeetingRooms.get(position).getName());
                intent.putExtra("MeetingRoomsNumberChair", mMeetingRooms.get(position).getNumberChair());
                intent.putExtra("MeetingRoomsBlackboard", mMeetingRooms.get(position).isBlackboard());
                intent.putExtra("MeetingRoomsProjector", mMeetingRooms.get(position).isProjector());
                intent.putExtra("MeetingRoomsInfo", mMeetingRooms.get(position).getInfo());
                MyApplication.getInstance().setMeetingRoom(mMeetingRooms.get(position));

                mContext.startActivity(intent);
                Toast.makeText(mContext, "#" + position + " - " + mMeetingRooms.get(position).getName() + " (Long click)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        int d = 0;
        try{
            d =mMeetingRooms.size();
        }catch (Exception e){}
        return d;
    }

}
