package kondratkov.bookingofmeetingrooms.view.listroom;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import kondratkov.bookingofmeetingrooms.R;
import kondratkov.bookingofmeetingrooms.model.pojo.MeetingRoom;
import kondratkov.bookingofmeetingrooms.representation.DataTimePepresentation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kondratkov on 01.11.2017.
 */

public class ListRoomsHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    @BindView(R.id.imageView_itemRoom_projector)ImageView imageView_itemRoom_projector;
    @BindView(R.id.imageView_itemRoom_blackboard)ImageView imageView_itemRoom_blackboard;
    @BindView(R.id.tv_itemRoom_chair)TextView tv_itemRoom_chair;
    @BindView(R.id.tv_itemRoom_roomname)TextView tv_itemRoom_roomname;
    @BindView(R.id.tv_itemRoom_free)TextView tv_itemRoom_free;
    @BindView(R.id.linearlayout_itemRoom)LinearLayout linearlayout_itemRoom;

    DataTimePepresentation dataTimePepresentation;
    ListRoomsItemClickListener mListRoomsItemClickListener;

    public ListRoomsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setTag(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void bind(@NonNull MeetingRoom mMeetingRoom){
        dataTimePepresentation = new DataTimePepresentation();

        tv_itemRoom_roomname.setText(mMeetingRoom.getName());
        tv_itemRoom_chair.setText(R.string.number_chair);
        tv_itemRoom_chair.setText(tv_itemRoom_chair.getText()+" "+String.valueOf(mMeetingRoom.getNumberChair()));
        if(mMeetingRoom.isBlackboard()){
            imageView_itemRoom_blackboard.setImageResource(R.drawable.obuchenie);
        }else {
            imageView_itemRoom_blackboard.setImageResource(R.drawable.notblackboard);
        }
        if(mMeetingRoom.isProjector()){
            imageView_itemRoom_projector.setImageResource(R.drawable.projector);
        }else{
            imageView_itemRoom_projector.setImageResource(R.drawable.notprojector);
        }
        if (mMeetingRoom.isFreedomStatus()==false){
            try {
                tv_itemRoom_free.setText("занята с "+dataTimePepresentation.getData_HHmm(mMeetingRoom.getDateReserv(), tv_itemRoom_free.getContext()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            linearlayout_itemRoom.setBackgroundResource(R.color.colorFonCloseFree);
        }else{
            if(mMeetingRoom.getDateReserv()==null){
                tv_itemRoom_free.setText(R.string.freedom);
                linearlayout_itemRoom.setBackgroundResource(R.color.colorFonFree);
            }else{
                try {
                    tv_itemRoom_free.setText("забронирована на "+dataTimePepresentation.getData_HHmm(mMeetingRoom.getDateReserv(), tv_itemRoom_free.getContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                linearlayout_itemRoom.setBackgroundResource(R.color.colorFonNotFree);
            }
        }
    }

    public void setClickListener(ListRoomsItemClickListener mListRoomsItemClickListener) {
        this.mListRoomsItemClickListener = mListRoomsItemClickListener;
    }

    @Override
    public void onClick(View view) {
        mListRoomsItemClickListener.onClick(view, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        mListRoomsItemClickListener.onClick(view, getAdapterPosition(), true);
        return true;
    }
}
