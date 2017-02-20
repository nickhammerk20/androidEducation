package com.example.hammer.task_4_crudsharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hammer on 08.02.2017.
 */

public class ListPersonActivity extends AppCompatActivity
{
   // private RecyclerView recycler;
    private ArrayList<Person> persons;
    private MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_person);

        ListView lvPerson = (ListView) findViewById(R.id.list_view_person);
        ArrayList<Person> persons = new ArrayList<>();
        Intent intent = getIntent();

        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String surename = intent.getStringExtra("surename");
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String mail = intent.getStringExtra("mail");
        String skype = intent.getStringExtra("skype");

        Person person = new Person(5 , "vasja","dupkin", "05864654", "sdsd@sd.sd","asdasdfgfs");
        persons.add(person);

        MyAdapter myAdapter = new MyAdapter(this, persons);
        lvPerson.setAdapter(myAdapter);
    }

}
