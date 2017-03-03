package com.example.hammer.task_10_sqlite_asynctask_loader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hammer.task_10_sqlite_asynctask_loader.R;
import com.example.hammer.task_10_sqlite_asynctask_loader.db.CRUDSQLite;
import com.example.hammer.task_10_sqlite_asynctask_loader.model.Person;

/**
 * Created by hammer on 02.03.2017.
 */

public class PersonDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSave, btnDelete;
    private CRUDSQLite crudsqLite;
    private TextView tvIdDetailsPerson, tvNameDetailsPerson,
            tvSurnameDetailsPerson, tvPhoneDetailsPerson,
            tvSkypeDetailsPerson, tvMailDetailsPerson;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Detail");

        tvIdDetailsPerson = (TextView) findViewById(R.id.text_view_details_id);
        tvNameDetailsPerson = (TextView) findViewById(R.id.text_view_details_name);
        tvSurnameDetailsPerson = (TextView) findViewById(R.id.text_view_details_surname);
        tvPhoneDetailsPerson = (TextView) findViewById(R.id.text_view_details_phone);
        tvSkypeDetailsPerson = (TextView) findViewById(R.id.text_view_details_skype);
        tvMailDetailsPerson = (TextView) findViewById(R.id.text_view_details_mail);

        if(Person.selectedPerson != null)
        {
            Person itemPerson = Person.selectedPerson;

            tvIdDetailsPerson.setText(String.valueOf(itemPerson.getmId()));
            tvNameDetailsPerson.setText(itemPerson.getmName());
            tvSurnameDetailsPerson.setText(itemPerson.getmSurname());
            tvPhoneDetailsPerson.setText(itemPerson.getmPhone());
            tvSkypeDetailsPerson.setText(itemPerson.getmSkype());
            tvMailDetailsPerson.setText(itemPerson.getmMail());
        }
        btnSave = (Button) findViewById(R.id.btn_detail_save);
        btnSave.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.btn_detail_delete);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        crudsqLite = new CRUDSQLite(this);

        switch (v.getId()){
            case R.id.btn_detail_save:
                crudsqLite.updatePerson(
                        Integer.parseInt(tvIdDetailsPerson.getText().toString()),
                        tvNameDetailsPerson.getText().toString(),
                        tvSurnameDetailsPerson.getText().toString(),
                        tvPhoneDetailsPerson.getText().toString(),
                        tvSkypeDetailsPerson.getText().toString(),
                        tvMailDetailsPerson.getText().toString());

                followToListActivity();
                break;

            case R.id.btn_detail_delete:
                int id = Integer.parseInt(tvIdDetailsPerson.getText().toString());
                crudsqLite.deletePerson(id);
                followToListActivity();
                break;

            default:
                break;
        }
    }

    private void followToListActivity() {
        Intent intent = new Intent(PersonDetailActivity.this, PersonsListActivity.class);
        startActivity(intent);
    }
}
