package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    RecyclerView recyclerView;
    MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> subjectList = new ArrayList<String>();
        subjectList.add("모바일 소프트웨어");
        subjectList.add("네트워크");
        subjectList.add("웹서비스");
        subjectList.add("운영체제");
        subjectList.add("웹프로그래밍2");

        adapter = new MyRecyclerAdapter(this, R.layout.item_layout, subjectList);

        recyclerView = findViewById(R.id.recyclerView);

        /*recyclerView 의 항목의 표현방법 지정 (가로/세로)*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        recyclerView.setAdapter(adapter);



    }




}