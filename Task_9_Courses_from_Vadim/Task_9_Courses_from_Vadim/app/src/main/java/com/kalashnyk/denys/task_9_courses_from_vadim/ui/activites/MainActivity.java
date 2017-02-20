package com.kalashnyk.denys.task_9_courses_from_vadim.ui.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kalashnyk.denys.task_9_courses_from_vadim.R;
import com.kalashnyk.denys.task_9_courses_from_vadim.db.CRUDSQLite;
import com.kalashnyk.denys.task_9_courses_from_vadim.model.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etId, etName, etSurname, etPhoneNumber, etMail, etSkype;
    private Button btnSave;
    private CRUDSQLite crudsqLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        etId = (EditText) findViewById(R.id.edit_text_main_id);
        etName = (EditText) findViewById(R.id.edit_text_main_name);
        etSurname = (EditText) findViewById(R.id.edit_text_main_surname);
        etPhoneNumber = (EditText) findViewById(R.id.edit_text_main_phone_number);
        etMail = (EditText) findViewById(R.id.edit_text_main_mail);
        etSkype = (EditText) findViewById(R.id.edit_text_main_skype);
        btnSave = (Button) findViewById(R.id.btn_main_save);
        btnSave.setOnClickListener(this);
        crudsqLite = new CRUDSQLite(this);
    }

    @Override
    public void onClick(View view) {
        crudsqLite = new CRUDSQLite(this);
        switch (view.getId()){
            case R.id.btn_main_save:
                Person personModel = new Person(etName.getText().toString(), etSurname.getText().toString(),
                        etPhoneNumber.getText().toString(), etMail.getText().toString(), etSkype.getText().toString());
                crudsqLite.addPerson(personModel);
//                clearText();
                followToListActivity();
//                followToListActivityWithIntentData();
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

    private void followToListActivity(){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
    private void followToListActivityWithIntentData() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        try {
            intent.putExtra("id", Integer.parseInt(etId.getText().toString()));
        } catch (NumberFormatException e) {}
        intent.putExtra("name", etName.getText().toString());
        intent.putExtra("surname", etSurname.getText().toString());
        intent.putExtra("phone", etPhoneNumber.getText().toString());
        intent.putExtra("mail", etMail.getText().toString());
        intent.putExtra("skype", etSkype.getText().toString());
        clearText();
        startActivity(intent);
    }
    private void clearText(){
        etId.setText("");
        etName.setText("");
        etSurname.setText("");
        etPhoneNumber.setText("");
        etMail.setText("");
        etSkype.setText("");
    }

}
