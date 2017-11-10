package com.example.kondratkov.bookingofmeetingrooms.view.reservation;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kondratkov.bookingofmeetingrooms.R;
import com.example.kondratkov.bookingofmeetingrooms.model.pojo.Reservation;

import java.util.List;

import butterknife.BindView;


public class ReservationItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private List<Reservation>mReservationList;

    public ReservationItemFragment() {
    }

    public ReservationItemFragment(List<Reservation>mReservationList){
        this.mReservationList = mReservationList;
    }

    public static ReservationItemFragment newInstance(int columnCount) {
        ReservationItemFragment fragment = new ReservationItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_reservation, container, false);
        RecyclerView mRecyclerView;
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_reserv);

        // Set the adaptermRecyclerView.
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            ReservationRoomAdapter reservationRoomAdapter = new ReservationRoomAdapter(context, mReservationList);
            mRecyclerView.setAdapter(reservationRoomAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
