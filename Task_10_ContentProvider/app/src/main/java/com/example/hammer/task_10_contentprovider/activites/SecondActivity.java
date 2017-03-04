package com.example.hammer.task_10_contentprovider.activites;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;


import com.example.hammer.task_10_contentprovider.R;
import com.example.hammer.task_10_contentprovider.adapters.FilterAdapter;
import com.example.hammer.task_10_contentprovider.adapters.MyAdapter;
import com.example.hammer.task_10_contentprovider.db.DBContentProvider;
import com.example.hammer.task_10_contentprovider.db.PersonContract;
import com.example.hammer.task_10_contentprovider.model.Person;

import java.util.ArrayList;

/**
 * Created by User on 05.01.2017.
 */

public class SecondActivity extends AppCompatActivity {
    private ListView lvPerson;
    private ArrayList<Person> persons;
    private MyAdapter myAdapter;
    private FilterAdapter filterAdapter;
    private EditText tvSelect;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lvPerson = (ListView) findViewById(R.id.list_view_second_person);
        persons = new ArrayList<>();

        String selection = null;
        String sortOrder = null;

        Cursor cursor = getContentResolver().query(DBContentProvider.PERSONS_CONTENT_URI, null, selection, null, sortOrder);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(PersonContract.KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(PersonContract.KEY_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(PersonContract.KEY_SURNAME));
            String phone = cursor.getString(cursor.getColumnIndex(PersonContract.KEY_PHONE));
            String mail = cursor.getString(cursor.getColumnIndex(PersonContract.KEY_MAIL));
            String skype = cursor.getString(cursor.getColumnIndex(PersonContract.KEY_SKYPE));
            persons.add(new Person(id, name, surname, phone, mail, skype));
        }

//        myAdapter = new MyAdapter(this, persons);
        filterAdapter = new FilterAdapter(this, persons);
        lvPerson.setAdapter(filterAdapter);
        lvPerson.setTextFilterEnabled(true);
        tvSelect = (EditText) findViewById(R.id.text_view_list_select);
        tvSelect.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < before) {
                    filterAdapter.resetData();
                }

                filterAdapter.getFilter().filter(s.toString());

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
            filterAdapter.dropListPersons();
            return true;
        } else if (id == R.id.action_second_get_person) {
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
                ArrayList<Person> newListPerson = new ArrayList<Person>();
                Cursor cursor = getContentResolver().query(DBContentProvider.PERSONS_CONTENT_URI.buildUpon().
                        appendPath(etListDialogId.getText().toString()).build(), null, null, null, null);
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(PersonContract.KEY_ID));
                    String name = cursor.getString(cursor.getColumnIndex(PersonContract.KEY_NAME));
                    String surname = cursor.getString(cursor.getColumnIndex(PersonContract.KEY_SURNAME));
                    String phone = cursor.getString(cursor.getColumnIndex(PersonContract.KEY_PHONE));
                    String mail = cursor.getString(cursor.getColumnIndex(PersonContract.KEY_MAIL));
                    String skype = cursor.getString(cursor.getColumnIndex(PersonContract.KEY_SKYPE));
                    Person person = new Person(id, name, surname, phone, mail, skype);
                    filterAdapter.changePersonsList(newListPerson, person);
                }
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
