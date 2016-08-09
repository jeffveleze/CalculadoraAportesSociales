package com.ingelectro.jyj.aportapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.StringCharacterIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputData extends AppCompatActivity {

    @BindView(R.id.inputSalary) EditText inputSalaryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        ButterKnife.bind(this);
        formatInputField();

    }

    @OnClick(R.id.calculateButton)
    public void calculate(){

        if(!inputSalaryText.getText().toString().matches("")) {

            String inputSalaryString = inputSalaryText.getText().toString();
            String cleanString = inputSalaryString.replaceAll("[$,.]", "");
            int salary = Integer.parseInt(cleanString)/100;
            Intent intentInput = new Intent(this, ShowData.class);
            intentInput.putExtra("salary",salary);
            startActivity(intentInput);


        }else {
            Toast.makeText(getApplicationContext(),"Ingresa tu salario",Toast.LENGTH_SHORT).show();
        }

    }

    public void formatInputField(){

        inputSalaryText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals("")){
                    inputSalaryText.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll("[$,.]", "");
                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));
                    inputSalaryText.setText(formatted);
                    inputSalaryText.setSelection(formatted.length());
                    inputSalaryText.addTextChangedListener(this);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
