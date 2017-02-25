package com.example.hammer.task_9_sqlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by hammer on 24.02.2017.
 */

public class DetailsPersonsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_details);

        TextView tvNameDetailsPerson = (TextView) findViewById(R.id.text_view_details_name);
        TextView tvSurnameDetailsPerson = (TextView) findViewById(R.id.text_view_details_surname);
        TextView tvPhoneDetailsNumberPerson = (TextView) findViewById(R.id.text_view_details_phone_number);
        TextView tvMailDetailsPerson = (TextView) findViewById(R.id.text_view_details_mail);
        TextView tvSkypeDetailsPerson = (TextView) findViewById(R.id.text_view_details_skype);
        if (Person.selectedPerson != null) {
            Person itemPerson = Person.selectedPerson;
            tvNameDetailsPerson.setText(itemPerson.getmName());
            tvSurnameDetailsPerson.setText(itemPerson.getmSurename());
            tvPhoneDetailsNumberPerson.setText(itemPerson.getmPhoneNumber());
            tvMailDetailsPerson.setText(itemPerson.getmMail());
            tvSkypeDetailsPerson.setText(itemPerson.getmSkype());
        }
    }
}
