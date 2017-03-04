package com.example.hammer.task_4_crudsharedpreferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hammer.task_4_crudsharedpreferences.database.CRUDSharedPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etId, etName, etSurename, etPhone, etMail, etSkype;
    private Button btnSave;
    private CRUDSharedPreferences crudSharedPreferences;

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
        crudSharedPreferences = new CRUDSharedPreferences();

        switch (v.getId()) {
            case R.id.btn_main_save:
                Person personModel = new Person(
                        Integer.valueOf(etId.getText().toString()),
                        etName.getText().toString(),
                        etSurename.getText().toString(),
                        etPhone.getText().toString(),
                        etMail.getText().toString(),
                        etSkype.getText().toString());

                crudSharedPreferences.addPerson(this, personModel);
                clearText();
                followToListPersons();
                break;
            default:
                break;
        }
//        // From usage Intent
//        Intent intent = new Intent(MainActivity.this, ListPersonActivity.class);
//
//        intent.putExtra("id", person.getmId());
//        intent.putExtra("name", person.getmName());
//        intent.putExtra("surename", person.getmSurename());
//        intent.putExtra("phone", person.getmPhoneNumber());
//        intent.putExtra("mail", person.getmMail());
//        intent.putExtra("skype", person.getmSkype());
//        startActivity(intent);

        //string id = i.getetString("id");
        //putExtras('id' . etID);
    }

    private void clearText() {
        etId.setText("");
        etName.setText("");
        etPhone.setText("");
        etSurename.setText("");
        etSkype.setText("");
        etMail.setText("");
    }

    private void followToListPersons() {
        Intent intent = new Intent(MainActivity.this, ListPersonActivity.class);
//        startActivityForResult(intent, 0);
        startActivity(intent);
    }

    @Override                                                                                        // зачем это?????
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_list_persons){
            followToListPersons();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        etId.setText(data.getStringExtra("id"));
        etName.setText(data.getStringExtra("name"));
        etSurename.setText(data.getStringExtra("surename"));
        etPhone.setText(data.getStringExtra("phone"));
        etSkype.setText(data.getStringExtra("skype"));
        etMail.setText(data.getStringExtra("mail"));
    }
}
