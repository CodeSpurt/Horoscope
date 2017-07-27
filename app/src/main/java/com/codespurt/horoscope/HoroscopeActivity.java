package com.codespurt.horoscope;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.codespurt.horoscope.fragments.HoroscopeMonth;
import com.codespurt.horoscope.fragments.HoroscopeToday;
import com.codespurt.horoscope.fragments.HoroscopeWeek;
import com.codespurt.horoscope.fragments.HoroscopeYear;

/**
 * Created by CodeSpurt on 7/22/2017.
 */

public class HoroscopeActivity extends FragmentActivity {

    private HoroscopeToday today;
    private HoroscopeWeek week;
    private HoroscopeMonth month;
    private HoroscopeYear year;

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private String sign = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);

        viewPager = (ViewPager) findViewById(R.id.pager);

        if (getIntent().getStringExtra("sign") != null) {
            sign = getIntent().getStringExtra("sign");
        }

        // set adapter
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), sign);
        viewPager.setAdapter(adapter);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private Bundle bundle;

        public ViewPagerAdapter(FragmentManager fm, String sign) {
            super(fm);
            bundle = new Bundle();
            bundle.putString("sign", sign);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    today = new HoroscopeToday();
                    today.setArguments(bundle);
                    return today;
                case 1:
                    week = new HoroscopeWeek();
                    week.setArguments(bundle);
                    return week;
                case 2:
                    month = new HoroscopeMonth();
                    month.setArguments(bundle);
                    return month;
                case 3:
                    year = new HoroscopeYear();
                    year.setArguments(bundle);
                    return year;
                default:
                    today = new HoroscopeToday();
                    today.setArguments(bundle);
                    return today;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
