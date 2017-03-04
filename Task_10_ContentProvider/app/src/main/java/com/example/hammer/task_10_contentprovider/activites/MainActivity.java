package com.example.hammer.task_10_contentprovider.activites;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hammer.task_10_contentprovider.R;
import com.example.hammer.task_10_contentprovider.db.DBContentProvider;
import com.example.hammer.task_10_contentprovider.db.PersonContract;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName, etSurname, etPhoneNumber, etMail, etSkype;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.edit_text_main_name);
        etSurname = (EditText) findViewById(R.id.edit_text_main_surname);
        etPhoneNumber = (EditText) findViewById(R.id.edit_text_main_phone_number);
        etMail = (EditText) findViewById(R.id.edit_text_main_mail);
        etSkype = (EditText) findViewById(R.id.edit_text_main_skype);
        btnSave = (Button) findViewById(R.id.btn_main_save);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_save:
                ContentValues cv = new ContentValues();
                cv.put(PersonContract.KEY_NAME, etName.getText().toString());//tmp.getmName()
                cv.put(PersonContract.KEY_SURNAME, etSurname.getText().toString());//tmp.getmSurname()
                cv.put(PersonContract.KEY_PHONE, etPhoneNumber.getText().toString());//tmp.getmPhoneNumber()
                cv.put(PersonContract.KEY_MAIL, etMail.getText().toString());//tmp.getmMail()
                cv.put(PersonContract.KEY_SKYPE, etSkype.getText().toString());//tmp.getmSkype()
                getContentResolver().insert(DBContentProvider.PERSONS_CONTENT_URI, cv);
                clearText();
                followToListActivity();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    private void followToListActivity() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    private void clearText() {
        etName.setText("");
        etSurname.setText("");
        etPhoneNumber.setText("");
        etMail.setText("");
        etSkype.setText("");
    }
}