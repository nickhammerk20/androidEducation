package com.kalashnyk.denys.task_4_courses_from_vadim.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.kalashnyk.denys.task_4_courses_from_vadim.R;
import com.kalashnyk.denys.task_4_courses_from_vadim.database.CRUDSharedPreferences;
import com.kalashnyk.denys.task_4_courses_from_vadim.model.Person;
import com.kalashnyk.denys.task_4_courses_from_vadim.ui.adapters.MyAdapter;
import com.kalashnyk.denys.task_4_courses_from_vadim.ui.swipe.SwipeHelper;

import java.util.ArrayList;

/**
 * Created by User on 17.12.2016.
 */

public class SecondActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private ArrayList<Person> persons;
    private MyAdapter adapter;
    CRUDSharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        persons = new ArrayList<>();
        sharedPreferences = new CRUDSharedPreferences();
        persons = sharedPreferences.getPersons(this);

        recycler = (RecyclerView) findViewById(R.id.recycler_view_person);
        recycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(this, persons);
        recycler.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SwipeHelper(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recycler);
    }

}
