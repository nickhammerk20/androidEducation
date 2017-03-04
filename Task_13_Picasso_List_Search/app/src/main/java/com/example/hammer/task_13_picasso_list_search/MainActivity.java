package com.example.hammer.task_13_picasso_list_search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> pList;
    private RecyclerView recycledView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pList = new ArrayList<>();
        setData(pList);

        recycledView = (RecyclerView) findViewById(R.id.reycle_view_layout);
        recycledView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        MyAdapter myAdapter = new MyAdapter(this, setData(pList));
        recycledView
        //MyAdapter + this, products
        //setAdapter

    }

    private ArrayList<Product> setData(ArrayList<Product> list){
        ArrayList<Product> newList;
        newList = list;
        newList.add(new Product(1, "first", ""));
        newList.add(new Product(2, "second", ""));
        newList.add(new Product(3, "three", ""));
        return newList;
    }
}
