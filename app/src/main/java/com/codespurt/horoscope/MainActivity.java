package com.codespurt.horoscope;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.codespurt.horoscope.adapter.HoroscopeGridAdapter;
import com.codespurt.horoscope.adapter.HoroscopeListAdapter;
import com.codespurt.horoscope.pojo.HoroscopeListItemData;
import com.codespurt.horoscope.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridview;

    private String[] horoscopeArray;
    private List<HoroscopeListItemData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        gridview = (GridView) findViewById(R.id.gridview);

        // get data and add to list
        horoscopeArray = getResources().getStringArray(R.array.horoscope_signs);
        list = new ArrayList<>();
        for (int countr = 0; countr < horoscopeArray.length; countr++) {
            HoroscopeListItemData item = new HoroscopeListItemData();
            item.setTitle(horoscopeArray[countr]);
            list.add(item);
        }

        setListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_change_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_list:
                setListView();
                return true;
            case R.id.menu_grid:
                setGridView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setListView() {
        gridview.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        // set adapter
        HoroscopeListAdapter adapter = new HoroscopeListAdapter(list);

        // set layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        // set recyclerView listener
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                showHoroscope(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    private void setGridView() {
        recyclerView.setVisibility(View.GONE);
        gridview.setVisibility(View.VISIBLE);

        // set adapter
        HoroscopeGridAdapter adapter = new HoroscopeGridAdapter(this, list);
        gridview.setAdapter(adapter);

        // set gridview listener
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                showHoroscope(position);
            }
        });
    }

    private void showHoroscope(int position) {
        Utility utility = new Utility(this);
        if (utility.isInternetAvailable()) {
            Intent intent = new Intent(this, HoroscopeActivity.class);
            for (int countr = 0; countr < horoscopeArray.length; countr++) {
                if (countr == position) {
                    intent.putExtra("sign", horoscopeArray[position]);
                }
            }
            startActivity(intent);
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }
}
