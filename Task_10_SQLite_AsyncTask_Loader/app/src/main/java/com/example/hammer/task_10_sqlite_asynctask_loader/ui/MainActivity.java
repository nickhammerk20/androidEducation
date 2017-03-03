package com.example.hammer.task_10_sqlite_asynctask_loader.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hammer.task_10_sqlite_asynctask_loader.R;
import com.example.hammer.task_10_sqlite_asynctask_loader.db.CRUDSQLite;
import com.example.hammer.task_10_sqlite_asynctask_loader.model.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etName, etSurname, etPhone, etSkype, etMail;
    private Button btnSave;
    private CRUDSQLite crudsqLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");

        etName = (EditText) findViewById(R.id.edit_text_main_name);
        etSurname = (EditText) findViewById(R.id.edit_text_main_surname);
        etPhone = (EditText) findViewById(R.id.edit_text_main_phone_number);
        etSkype = (EditText) findViewById(R.id.edit_text_main_skype);
        etMail = (EditText) findViewById(R.id.edit_text_main_mail);

        btnSave = (Button) findViewById(R.id.btn_main_save);
        btnSave.setOnClickListener(this);

        crudsqLite = new CRUDSQLite(this);
    }

    @Override
    public void onClick(View v) {

        crudsqLite = new CRUDSQLite(this);

        switch (v.getId())
        {
            case R.id.btn_main_save:
                Person person = new Person(
                        etName.getText().toString(),
                        etSurname.getText().toString(),
                        etPhone.getText().toString(),
                        etSkype.getText().toString(),
                        etMail.getText().toString());
                //записать в базу данных
                crudsqLite.addPerson(person);
                // переключить на активность PersonListActivity
                followToListActivity();
                break;
            default:
                break;
        }
    }

    private void followToListActivity()
    {
        Intent intent = new Intent(MainActivity.this, PersonsListActivity.class);
        startActivity(intent);
    }
}
