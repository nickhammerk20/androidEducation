package com.example.hammer.task_9_sqlite.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hammer.task_9_sqlite.model.Person;
import com.example.hammer.task_9_sqlite.R;
import com.example.hammer.task_9_sqlite.database.CRUDSQLite;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etId, etName, etSurename, etPhone, etMail, etSkype;
    private Button btnSave;
    private CRUDSQLite crudsqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.edit_text_dialog_name);
        etSurename = (EditText) findViewById(R.id.edit_text_dialog_surname);
        etPhone = (EditText) findViewById(R.id.edit_text_dialog_phone_number);
        etMail = (EditText) findViewById(R.id.edit_text_dialog_mail);
        etSkype = (EditText) findViewById(R.id.edit_text_dialog_skype);
        btnSave = (Button) findViewById(R.id.btn_main_save);
        btnSave.setOnClickListener(this);
        crudsqLite = new CRUDSQLite(this);
    }

    @Override
    public void onClick(View v) {
        crudsqLite = new CRUDSQLite(this);
        switch (v.getId()){
            case R.id.btn_main_save:
                Person personModel = new Person(
                        etName.getText().toString(),
                        etSurename.getText().toString(),
                        etPhone.getText().toString(),
                        etMail.getText().toString(),
                        etSkype.getText().toString());
                crudsqLite.addPerson(personModel);
                followToListActivity();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_list_activity) {
            followToListActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void followToListActivity() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
    private void followToListActivityWithIntentData() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        try {
            intent.putExtra("id", Integer.parseInt(etId.getText().toString()));
        } catch (NumberFormatException e) {}
        intent.putExtra("name", etName.getText().toString());
        intent.putExtra("surname", etSurename.getText().toString());
        intent.putExtra("phone", etPhone.getText().toString());
        intent.putExtra("mail", etMail.getText().toString());
        intent.putExtra("skype", etSkype.getText().toString());
        clearText();
        startActivity(intent);
    }
    private void clearText(){
        etId.setText("");
        etName.setText("");
        etSurename.setText("");
        etPhone.setText("");
        etMail.setText("");
        etSkype.setText("");
    }
}
