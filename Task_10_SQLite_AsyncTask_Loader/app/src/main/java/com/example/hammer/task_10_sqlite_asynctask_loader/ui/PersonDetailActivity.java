package com.example.hammer.task_10_sqlite_asynctask_loader.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.hammer.task_10_sqlite_asynctask_loader.R;

/**
 * Created by hammer on 02.03.2017.
 */

public class PersonDetailActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvIdDetailsPerson = (TextView) findViewById(R.id.text_view_details_id);
        TextView tvNameDetailsPerson = (TextView) findViewById(R.id.text_view_details_name);
        TextView tvSurnameDetailsPerson = (TextView) findViewById(R.id.text_view_details_surname);
        TextView tvPhoneDetailsPerson = (TextView) findViewById(R.id.text_view_details_phone);
        TextView tvSkypeDetailsPerson = (TextView) findViewById(R.id.text_view_details_skype);
        TextView tvMailDetailsPerson = (TextView) findViewById(R.id.text_view_details_mail);

    }
}
