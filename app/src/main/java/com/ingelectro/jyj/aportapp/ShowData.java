package com.ingelectro.jyj.aportapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowData extends AppCompatActivity {

    int salaryValue;
    double baseValue,saludValue,pensionValue,arlValue,totalValue;

    @BindView(R.id.base) TextView baseText;
    @BindView(R.id.salud) TextView saludText;
    @BindView(R.id.pension) TextView pensionText;
    @BindView(R.id.arl) TextView arlText;
    @BindView(R.id.total) TextView totalText;
    @BindView(R.id.imageView2) ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.calculator).into(imageView2);
        //Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView2);
        calculateValues();
        displayData();

    }

    public void calculateValues(){

        salaryValue = getIntent().getIntExtra("salary",0);
        Log.d("Prueba",Integer.toString(salaryValue));
        baseValue = salaryValue*0.4;
        saludValue = baseValue*0.125;
        pensionValue = baseValue*0.16;
        arlValue = 7500;
        totalValue = saludValue + pensionValue + arlValue;

    }

    public void displayData(){

        DecimalFormat df = new DecimalFormat("$ #,###");
        baseText.setText("IBC: "+ df.format(baseValue));
        saludText.setText("Salud: "+ df.format(saludValue));
        pensionText.setText("Pensión: "+ df.format(pensionValue));
        arlText.setText("ARL: "+ df.format(arlValue));
        totalText.setText("Total: "+ df.format(totalValue));

    }

    @OnClick(R.id.modifyButton)
    public void modifySalary(){

        Intent intentShow = new Intent(this,InputData.class);
        startActivity(intentShow);

    }

}
