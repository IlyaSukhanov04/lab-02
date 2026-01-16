package com.example.androidbasics;

import android.widget.ListView;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    int pos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            cityList = findViewById(R.id.city_list);

            String []cities = {"Edmonton", "Vancouver", "Moscow"};
            //"Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

            dataList = new ArrayList<>();
            dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);

        EditText addCityField = findViewById(R.id.add_city_field);
        Button addBtn = findViewById(R.id.add_button);
        Button confirmBtn = findViewById(R.id.confirm_button);
        Button deleteBtn = findViewById(R.id.delete_button);

        cityList.setOnItemClickListener((adapterView, view, position, id) -> {
            pos = position;
        });

        addBtn.setOnClickListener(v -> {
            addCityField.setVisibility(android.view.View.VISIBLE);
            confirmBtn.setVisibility(android.view.View.VISIBLE);
        });

        confirmBtn.setOnClickListener(v -> {
            String cityName = addCityField.getText().toString();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                addCityField.setText("");
            }
            addCityField.setVisibility(android.view.View.GONE);
            confirmBtn.setVisibility(android.view.View.GONE);
        });

        deleteBtn.setOnClickListener(v -> {
            if (pos != -1) {
                dataList.remove(pos);
                cityAdapter.notifyDataSetChanged();
                pos = -1;
            }
        });
    }
}