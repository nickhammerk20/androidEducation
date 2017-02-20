package com.example.hammer.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvMA;
    EditText etFirstValue, etSecondValue;
    Button btnPlus, btnMinus, btnMultiply, btnDivide;

    @Override
    public void onClick(View view) {
        String strFirstValue = etFirstValue.getText().toString();
        String strSecondValue = etSecondValue.getText().toString();
        int a = Integer.valueOf(strFirstValue);
        int b = Integer.valueOf(strSecondValue);
        int result;
        switch (view.getId())
        {
            case R.id.btn_main_plus:
                result = a+b;
                tvMA.setText(String.valueOf(result));
                break;
            case R.id.btn_main_minus:
                result = a-b;
                tvMA.setText(String.valueOf(result));
                break;
            case R.id.btn_main_multiply:
                result = a*b;
                tvMA.setText(String.valueOf(result));
                break;
            case R.id.btn_main_devide:
                result = a/b;
                tvMA.setText(String.valueOf(result));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d("","");

//        tvMA = (TextView) findViewById(R.id.tv_main_result);

        etFirstValue = (EditText) findViewById(R.id.et_display);

        btnPlus = (Button) findViewById(R.id.btn_main_plus);
        btnMinus = (Button) findViewById(R.id.btn_main_minus);
        btnMultiply = (Button) findViewById(R.id.btn_main_multiply);
        btnDivide = (Button) findViewById(R.id.btn_main_devide);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
