package com.codespurt.horoscope.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codespurt.horoscope.R;
import com.codespurt.horoscope.pojo.HoroscopeListItemData;

import java.util.List;

/**
 * Created by CodeSpurt on 7/23/2017.
 */

public class HoroscopeGridAdapter extends BaseAdapter {

    private Context context;
    private List<HoroscopeListItemData> list;

    public HoroscopeGridAdapter(Context context, List<HoroscopeListItemData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.horoscope_list_row, null);
            TextView title = (TextView) grid.findViewById(R.id.horoscope_sign_title);

            HoroscopeListItemData data = list.get(i);
            title.setText(data.getTitle());
        } else {
            grid = (View) convertView;
        }
        return grid;
    }
}
