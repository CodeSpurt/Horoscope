package com.codespurt.horoscope.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codespurt.horoscope.R;
import com.codespurt.horoscope.pojo.HoroscopeListItemData;

import java.util.List;

/**
 * Created by CodeSpurt on 7/22/2017.
 */

public class HoroscopeListAdapter extends RecyclerView.Adapter<HoroscopeListAdapter.MyViewHolder> {

    private List<HoroscopeListItemData> list;

    public HoroscopeListAdapter(List<HoroscopeListItemData> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horoscope_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HoroscopeListItemData data = list.get(position);
        holder.title.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.horoscope_sign_title);
        }
    }
}
