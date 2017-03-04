package com.example.hammer.task_10_sqlite_asynctask_loader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_list_main_activity){
            followToMainActivity();
            return true;
        }
        else if(id == R.id.action_list_delete_all){
            crudsqLite.deleteAllPerson();
            followToMainActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void followToMainActivity()
    {
        Intent intent = new Intent(PersonsListActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
