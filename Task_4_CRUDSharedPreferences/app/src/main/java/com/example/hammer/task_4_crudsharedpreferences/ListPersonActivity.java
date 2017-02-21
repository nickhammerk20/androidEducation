package com.example.hammer.task_4_crudsharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.hammer.task_4_crudsharedpreferences.database.CRUDSharedPreferences;

import java.util.ArrayList;

/**
 * Created by hammer on 08.02.2017.
 */

public class ListPersonActivity extends AppCompatActivity
{
    private ListView lvPerson;
    private ArrayList<Person> persons;
    private MyAdapter adapter;
    CRUDSharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_person);

        persons = new ArrayList<>();
        sharedPreferences = new CRUDSharedPreferences();
        persons = sharedPreferences.getPersons(this);

        lvPerson = (ListView) findViewById(R.id.list_view_person);

//        //from usage Intent
//        Intent intent = getIntent();
//
//        int id = intent.getIntExtra("id", 0);
//        String name = intent.getStringExtra("name");
//        String surename = intent.getStringExtra("surename");
//        String phoneNumber = intent.getStringExtra("phoneNumber");
//        String mail = intent.getStringExtra("mail");
//        String skype = intent.getStringExtra("skype");
//
//        Person person = new Person(5 , "vasja","dupkin", "05864654", "sdsd@sd.sd","asdasdfgfs");
//        persons.add(person);

        adapter = new MyAdapter(this, persons);
        lvPerson.setAdapter(adapter);
    }

}
