package com.example.hammer.task_10_sqlite_asynctask_loader.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.hammer.task_10_sqlite_asynctask_loader.R;
import com.example.hammer.task_10_sqlite_asynctask_loader.adapter.MyAdapter;
import com.example.hammer.task_10_sqlite_asynctask_loader.db.CRUDSQLite;
import com.example.hammer.task_10_sqlite_asynctask_loader.model.Person;

import java.util.ArrayList;

/**
 * Created by hammer on 02.03.2017.
 */

public class PersonsListActivity extends AppCompatActivity {
    private ListView lvPersons;
    private ArrayList<Person> persons;
    private CRUDSQLite crudsqLite;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_list);
        setTitle("List of Person");

        lvPersons = (ListView) findViewById(R.id.list_view_persons_list);
        persons = new ArrayList<Person>();
        crudsqLite = new CRUDSQLite(this);
        persons = crudsqLite.getAllPerson();
        myAdapter = new MyAdapter(this, persons);
        lvPersons.setAdapter(myAdapter);
    }

}
