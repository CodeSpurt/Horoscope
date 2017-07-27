package com.codespurt.horoscope.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codespurt.horoscope.R;
import com.codespurt.horoscope.async.HttpGetRequest;
import com.codespurt.horoscope.async.Urls;
import com.codespurt.horoscope.engine.HttpGetData;
import com.codespurt.horoscope.pojo.HoroscopeData;
import com.codespurt.horoscope.utils.Utility;

/**
 * Created by CodeSpurt on 7/22/2017.
 */

public class HoroscopeToday extends Fragment {

    private LinearLayout linearLayout;
    private ProgressBar progressBar;
    private TextView title, data;
    private String sign = "";
    private Utility utility;

    HttpGetData httpGetData = new HttpGetData() {

        @Override
        public void getData(String data) {
            HoroscopeData parsedData = utility.parseData(data);
            progressBar.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);

            if (parsedData != null) {
                HoroscopeToday.this.title.setText("Today's Horoscope");
                HoroscopeToday.this.data.setText(utility.filterData(parsedData.getHoroscope()));
            } else {
                HoroscopeToday.this.data.setText(getResources().getString(R.string.no_internet_connection));
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horoscope, container, false);

        linearLayout = (LinearLayout) rootView.findViewById(R.id.linear_layout);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        title = (TextView) rootView.findViewById(R.id.tv_title);
        data = (TextView) rootView.findViewById(R.id.tv_data);

        Bundle bundle = getArguments();
        sign = bundle.getString("sign");

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        utility = new Utility(getContext());
        if (utility.isInternetAvailable()) {
            getHoroscope();
        } else {
            data.setText(getResources().getString(R.string.no_internet_connection));
        }
    }

    private void getHoroscope() {
        HttpGetRequest request = new HttpGetRequest(httpGetData);
        request.execute(new Urls().getBaseUrl() + "/today/" + sign);
    }
}
