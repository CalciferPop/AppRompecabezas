package com.example.hugopc.rompecabezas2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.onurkaganaldemir.ktoastlib.KToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeleccionActivity extends AppCompatActivity {

    @BindView(R.id.btn_jugar)Button btn_jugar;
    @BindView(R.id.rdb_4piezas)RadioButton btn_4piezas;
    @BindView(R.id.rdb_8piezas)RadioButton btn_8piezas;
    @BindView(R.id.rdb_16piezas)RadioButton btn_16piezas;





    String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);
        ButterKnife.bind(this);
        nickname = getIntent().getStringExtra("nickname");
    }





    @OnClick({R.id.btn_jugar})
    public void onClick(View view) {


        if (!btn_4piezas.isChecked() && !btn_8piezas.isChecked() && !btn_16piezas.isChecked()){
            CharSequence textd = getApplicationContext().getString(R.string.adve_RadioBu);
            KToast.customColorToast(SeleccionActivity.this, textd.toString(), Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.warning, R.drawable.ic_warning_black_24dp);

        }else{
            if (btn_4piezas.isChecked() ){
                CharSequence textd = getApplicationContext().getString(R.string.jugar4piezas);
                KToast.customColorToast(SeleccionActivity.this, textd.toString(), Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.easy, R.drawable.ic_filter_4_black_24dp);
                Intent IntentSelectImage = new Intent(SeleccionActivity.this,SelectImagenActivity.class);
                String dif= "facil";
                IntentSelectImage.putExtra("dificultad",dif);
                IntentSelectImage.putExtra("nickname",nickname);
                SeleccionActivity.this.startActivity(IntentSelectImage);


            }
            else if(btn_8piezas.isChecked() ){

                CharSequence textd = getApplicationContext().getString(R.string.jugar8piezas);
                KToast.customColorToast(SeleccionActivity.this, textd.toString(), Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.medium, R.drawable.ic_filter_6_black_24dp);

                Intent IntentSelectImage = new Intent(SeleccionActivity.this,SelectImagenActivity.class);
                String dif= "media";
                IntentSelectImage.putExtra("dificultad",dif);
                IntentSelectImage.putExtra("nickname",nickname);
                SeleccionActivity.this.startActivity(IntentSelectImage);
            }

            else if(btn_16piezas.isChecked() ){
                CharSequence textd = getApplicationContext().getString(R.string.jugar16piezas);
                KToast.customColorToast(SeleccionActivity.this, textd.toString(), Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.hard, R.drawable.ic_filter_8_black_24dp);

                Intent IntentSelectImage = new Intent(SeleccionActivity.this,SelectImagenActivity.class);
                String dif= "alta";
                IntentSelectImage.putExtra("dificultad",dif);
                IntentSelectImage.putExtra("nickname",nickname);
                SeleccionActivity.this.startActivity(IntentSelectImage);
            }

        }

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
                Intent ListarRanking4 = new Intent(SeleccionActivity.this, Raking4Activity.class);
               SeleccionActivity.this.startActivity(ListarRanking4);
                break;
            case R.id.itm8piezas:
                Intent ListarRanking8 = new Intent(SeleccionActivity.this, Ranking8Activity.class);
               SeleccionActivity.this.startActivity(ListarRanking8);
                break;
            case R.id.itm16piezas:
                Intent ListarRanking16 = new Intent(SeleccionActivity.this, Ranking16Activity.class);
               SeleccionActivity.this.startActivity(ListarRanking16);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
