package kondratkov.bookingofmeetingrooms.view.reservation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kondratkov.bookingofmeetingrooms.R;
import kondratkov.bookingofmeetingrooms.model.pojo.Reservation;
import kondratkov.bookingofmeetingrooms.representation.DataTimePepresentation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kondratkov on 04.11.2017.
 */

public class ReservationRoomHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_item_reser_fromtime)TextView tv_item_reser_fromtime;
    @BindView(R.id.tv_item_reser_totime)TextView tv_item_reser_totime;
    @BindView(R.id.tv_item_reser_date)TextView tv_item_reser_date;

    public ReservationRoomHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Reservation mReservation){
        DataTimePepresentation dataTimePepresentation = new DataTimePepresentation();

        try {
            tv_item_reser_date.setText(dataTimePepresentation.getData_ddMMyyyy(mReservation.getDateStart(),tv_item_reser_date.getContext()));
            tv_item_reser_fromtime.setText(dataTimePepresentation.getData_HHmm(mReservation.getDateStart(),tv_item_reser_fromtime.getContext()));
            tv_item_reser_totime.setText(dataTimePepresentation.getData_HHmm(mReservation.getDateFinish(),tv_item_reser_totime.getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
