package com.example.hammer.task_4_crudsharedpreferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etId, etName, etSurename, etPhone, etMail, etSkype;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // вешаем слушатель на кнопку
        btnSave = (Button) findViewById(R.id.btn_main_save);
        btnSave.setOnClickListener(this);
        //Find by ID
        etId = (EditText) findViewById(R.id.et_main_id);
        etName = (EditText) findViewById(R.id.et_main_name);
        etSurename = (EditText) findViewById(R.id.et_main_surname);
        etPhone = (EditText) findViewById(R.id.et_main_phone);
        etMail = (EditText) findViewById(R.id.et_main_mail);
        etSkype = (EditText) findViewById(R.id.et_main_skype);

    }

    @Override
    public void onClick(View v)
    {
     Person person = new Person(Integer.valueOf(etId.getText().toString()), etName.getText().toString(),
             etSurename.getText().toString(),
             etPhone.getText().toString(),
             etMail.getText().toString(),
             etSkype.getText().toString());
        Intent intent = new Intent(MainActivity.this, ListPersonActivity.class);

        intent.putExtra("id", person.getmId());
        intent.putExtra("name", person.getmName());
        intent.putExtra("surename", person.getmSurename());
        intent.putExtra("phone", person.getmPhoneNumber());
        intent.putExtra("mail", person.getmMail());
        intent.putExtra("skype", person.getmSkype());
        startActivity(intent);

        //string id = i.getetString("id");
        //putExtras('id' . etID);

    }
}
