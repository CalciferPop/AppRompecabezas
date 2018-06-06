package com.example.hugopc.rompecabezas2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcercaDeActivity extends AppCompatActivity {

    @BindView(R.id.txtWebTec) TextView txtWebItc;
    @BindView(R.id.txtEmailHugo) TextView txtEmailHugo;
    @BindView(R.id.txtPhoneHugo) TextView txtPhoneHugo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        ButterKnife.bind(this);

        Linkify.addLinks(txtWebItc,Linkify.WEB_URLS);
        Linkify.addLinks(txtEmailHugo,Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks(txtPhoneHugo,Linkify.PHONE_NUMBERS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itm4piezas:
                Intent ListarRanking4 = new Intent(AcercaDeActivity.this, Raking4Activity.class);
               AcercaDeActivity.this.startActivity(ListarRanking4);
                break;
            case R.id.itm8piezas:
                Intent ListarRanking8 = new Intent(AcercaDeActivity.this, Ranking8Activity.class);
                AcercaDeActivity.this.startActivity(ListarRanking8);
                break;
            case R.id.itm16piezas:
                Intent ListarRanking16 = new Intent(AcercaDeActivity.this, Ranking16Activity.class);
                AcercaDeActivity.this.startActivity(ListarRanking16);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
