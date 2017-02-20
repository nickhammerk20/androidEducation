package com.kalashnyk.denys.task_4_courses_from_vadim.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kalashnyk.denys.task_4_courses_from_vadim.R;
import com.kalashnyk.denys.task_4_courses_from_vadim.database.CRUDSharedPreferences;
import com.kalashnyk.denys.task_4_courses_from_vadim.model.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSave;
    private EditText etID, etName, etSurname, etPhoneNumber, etMail, etSkype;
    private CRUDSharedPreferences crudSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        etID = (EditText) findViewById(R.id.edit_text_id);
        etName = (EditText) findViewById(R.id.edit_text_name);
        etSurname = (EditText) findViewById(R.id.edit_text_surname);
        etPhoneNumber = (EditText) findViewById(R.id.edit_text_phone_number);
        etMail = (EditText) findViewById(R.id.edit_text_mail);
        etSkype = (EditText) findViewById(R.id.edit_text_skype);
    }

    @Override
    public void onClick(View view) {
        crudSharedPreferences = new CRUDSharedPreferences();
        switch (view.getId()){
            case R.id.btn_save:
                Person personModel = new Person(Integer.valueOf(etID.getText().toString()), etName.getText().toString(),
                        etSurname.getText().toString(), etPhoneNumber.getText().toString(),
                        etMail.getText().toString(), etSkype.getText().toString());
                crudSharedPreferences.addPerson(this, personModel);
                clearText();
                followToListPersons();
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

        if (id == R.id.action_list_persons) {
            followToListPersons();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void followToListPersons(){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(intent, 0);
    }

    private void clearText(){
        etID.setText("");
        etName.setText("");
        etSurname.setText("");
        etPhoneNumber.setText("");
        etMail.setText("");
        etSkype.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        etID.setText(data.getStringExtra("id"));
        etName.setText(data.getStringExtra("name"));
        etSurname.setText(data.getStringExtra("surname"));
        etPhoneNumber.setText(data.getStringExtra("phoneNumber"));
        etMail.setText(data.getStringExtra("mail"));
        etSkype.setText(data.getStringExtra("skype"));
    }
}
