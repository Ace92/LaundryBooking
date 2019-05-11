package com.hackathon.laundrybooking.full;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tvattroom.BookStatus;
import com.example.tvattroom.Booked;
import com.example.tvattroom.Free;
import com.example.tvattroom.TimeSlot;
import com.hackathon.laundrybooking.MainActivity;
import com.hackathon.laundrybooking.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 适配器
 * Created by huanghaibin on 2017/12/4.
 */

public class ArticleAdapter extends GroupRecyclerAdapter<String, TimeSlot> {
    private Context context;
    private LinkedHashMap<String, List<TimeSlot>> map;
    private ItemClick listener;

    public ArticleAdapter(Context context, LinkedHashMap<String, List<TimeSlot>> list) {
        super(context);
        this.context = context;
        map = list;
        List<String> titles = new ArrayList<>();
        // map.put("12 May 2019", create(0));
        titles.add("今日推荐");
        resetGroups(map, titles);
    }

    public void setItemClickListener(ItemClick listener){
        this.listener = listener;
    }


    public void notifyDataSetChange() {
        List<String> titles = new ArrayList<>();
        if (map.keySet().size() > 0)
            titles.add(map.keySet().toArray()[0].toString());
        resetGroups(map, titles);
    }


    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ArticleViewHolder(mInflater.inflate(R.layout.item_list_time_slot, parent, false));
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, final TimeSlot item, int position) {
        ArticleViewHolder h = (ArticleViewHolder) holder;
        h.mTextTitle.setText(getTimeSlotTime(item.getStartTime(), item.getEndTime()));
        h.mTextContent.setText(getTextForStatus(item.getBookStatus(), item.isMy()));
        h.container.setBackgroundColor(MainActivity.Companion.getTimeSlotColor(context.getResources(), item.getBookStatus()));
        if (item.getBookStatus() instanceof Free) {
            h.mBookButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(item);
                }
            });
            h.mBookButton.setVisibility(View.VISIBLE);
        } else {
            h.mBookButton.setOnClickListener(null);
            h.mBookButton.setVisibility(View.INVISIBLE);
        }
    }

    private String getTextForStatus(BookStatus bookStatus, boolean my) {
        String text = "";
        if (bookStatus instanceof Free) {
            text = "Free";
        } else if (bookStatus instanceof Booked) {
            text = "Booked";
            if (my)
                text += " by me";
        } else {
            text = "Maintain";
        }
        return text;

    }

    private String getTimeSlotTime(Date startTime, Date endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(startTime) + " - " + sdf.format(endTime);
    }

    private static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle,
                mTextContent;
        private View container;
        private Button mBookButton;

        private ArticleViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.time);
            mTextContent = (TextView) itemView.findViewById(R.id.status);
            mBookButton = (Button) itemView.findViewById(R.id.bookButton);
            container = itemView;
        }
    }
}

