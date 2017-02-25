package com.example.hammer.task_9_sqlite;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hammer.task_9_sqlite.adapter.MyAdapter;
import com.example.hammer.task_9_sqlite.database.CRUDSQLite;

import java.util.ArrayList;

/**
 * Created by hammer on 24.02.2017.
 */

public class SecondActivity extends AppCompatActivity {
    private ListView lvPerson;
    private ArrayList<Person> persons;
    private CRUDSQLite crudsqLite;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lvPerson = (ListView) findViewById(R.id.list_view_second_person);
        persons = new ArrayList<>();
        crudsqLite = new CRUDSQLite(this);
        persons = crudsqLite.getAllPerson();
        myAdapter = new MyAdapter(this, persons);
        lvPerson.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_second_delete_all) {
            myAdapter.dropListPersons();
            return true;
        }else if (id == R.id.action_second_get_person) {
            openGetPersonDialog(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void openGetPersonDialog(Context context) {

        LayoutInflater dialogInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View root = dialogInflater.inflate(R.layout.dialog_list_get_person, null);

        final EditText etListDialogId = (EditText) root.findViewById(R.id.edit_text_dialog_list_id);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setMessage("Put persons id!");
        alertDialogBuilder.setView(root);
        alertDialogBuilder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                crudsqLite = new CRUDSQLite(getBaseContext());
                myAdapter.changePersonsList(crudsqLite.getPerson(Integer.parseInt(etListDialogId.getText().toString())));
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }
}
