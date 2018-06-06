package com.example.hugopc.rompecabezas2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.onurkaganaldemir.ktoastlib.KToast;

public class PuzzleActivity extends AppCompatActivity implements Runnable, View.OnTouchListener {

    PuzzleLayout puzzleLayout;
    TextView tvTips;
    ImageView ivTips;
    int squareRootNum = 0;
    int drawableId= 0;
    int piezas=0;
    String mipmapID;
    double puntuacion=0;
    long tiempo_inicial=0,
            tiempo_final=0,diferencia_tiempo=0;
    double segundosTranscurridos =0 ;
    String nickname;
    int cve_usuario=0;

    private Cursor fila;


    UsuariosDbHelper objUsua;
    SQLiteDatabase objSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

    //    drawableId = getIntent().getStringExtra("drawable_ID");
        drawableId = getIntent().getIntExtra("drawable_ID",0);
        piezas = getIntent().getIntExtra("piezas",0);
        nickname = getIntent().getStringExtra("nickname");

        squareRootNum = piezas;
        tiempo_inicial= System.currentTimeMillis();

        ivTips = (ImageView) findViewById(R.id.iv_tips);
        ivTips.setImageResource(drawableId);
        tvTips = (TextView) findViewById(R.id.tv_tips);
        tvTips.setOnTouchListener(this);
        puzzleLayout = (PuzzleLayout) findViewById(R.id.activity_swipe_card);
        puzzleLayout.setImage(drawableId, squareRootNum);
        puzzleLayout.setOnCompleteCallback(new PuzzleLayout.OnCompleteCallback() {
            @Override
            public void onComplete() {
                puzzleLayout.postDelayed(PuzzleActivity.this, 800);
            }
        });
    }

    @Override
    public void run() {
        squareRootNum++;
        drawableId++;
        if(squareRootNum > 1){
            Toast.makeText(PuzzleActivity.this, R.string.complete, Toast.LENGTH_SHORT).show();
            showDialog();
        }else {
            ivTips.setImageResource(drawableId);
            puzzleLayout.setImage(drawableId, squareRootNum);
        }
    }

    private void showDialog() {
        new AlertDialog.Builder(PuzzleActivity.this)
                .setTitle(R.string.success)
                .setMessage(R.string.restart)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tiempo_final= System.currentTimeMillis();
                                diferencia_tiempo=tiempo_final-tiempo_inicial;
                                segundosTranscurridos = diferencia_tiempo/1000.0;
                                m_puntuacion(puzzleLayout.movimientos,segundosTranscurridos,piezas);
                                Toast.makeText(getApplicationContext(),"Tu puntuacion es:" + puntuacion,Toast.LENGTH_SHORT).show();
                                m_insertarpuntuacion(piezas);
                                Intent nuevoJuego = new Intent(PuzzleActivity.this,SeleccionActivity.class);
                                nuevoJuego.putExtra("nickname", nickname);
                                PuzzleActivity.this.startActivity(nuevoJuego);
                                finish();
                            }
                        }).setNegativeButton(R.string.exit,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tiempo_final= System.currentTimeMillis();
                        diferencia_tiempo=tiempo_final-tiempo_inicial;
                        segundosTranscurridos = diferencia_tiempo/1000.0;
                        m_puntuacion(puzzleLayout.movimientos,segundosTranscurridos,piezas);
                        Toast.makeText(getApplicationContext(),"Tu puntuacion es:" + puntuacion,Toast.LENGTH_SHORT).show();
                        m_insertarpuntuacion(piezas);
                        Intent nuevologin = new Intent(PuzzleActivity.this,Login2Activity.class);
                        PuzzleActivity.this.startActivity(nuevologin);
                        finish();
                    }
                }).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                ivTips.setVisibility(View.VISIBLE);
                break;
            default:
                ivTips.setVisibility(View.GONE);
        }
        return true;
    }

    public double m_puntuacion(int p_mov,double p_tiempo,int p_piezas){

        if (p_piezas == 2){
            puntuacion = ((p_mov * .5) / p_tiempo)*100;

        }else if (p_piezas == 3 ){

            puntuacion = ((p_mov * .10) / p_tiempo)*100;

        }else if(p_piezas == 4){
            puntuacion = ((p_mov * .15) / p_tiempo)*100;
        }
        return puntuacion;
    }


    public void m_insertarpuntuacion(int p_piezas){
        UsuariosDbHelper objBD = new UsuariosDbHelper(this,"ROMPECABEZAS",null,1);
        SQLiteDatabase db = objBD.getWritableDatabase();
        CharSequence text = this.getString(R.string.str_insertR4) + "  "+ nickname;


        fila= db.rawQuery("SELECT cve_usuario FROM usuario where nickname='" + nickname + "'",null);
        if(fila.moveToFirst()==true) {
             cve_usuario = fila.getInt(0);
        }
        if (p_piezas == 2){
            String sql_insert_ranking4 = "INSERT INTO ranking_4 (cve_usuario,puntuacion,movimientos,tiempo) " +
                    "values (" + cve_usuario + "," + puntuacion + ","+ puzzleLayout.movimientos + ","+ segundosTranscurridos+")";
            db.execSQL(sql_insert_ranking4);
            KToast.customColorToast(PuzzleActivity.this, text+"", Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.info, R.drawable.ic_add_black_24dp);
        }else if (p_piezas == 3 ){
            String sql_insert_ranking8 = "INSERT INTO ranking_8 (cve_usuario,puntuacion,movimientos,tiempo) " +
                    "values (" + cve_usuario + "," + puntuacion + ","+ puzzleLayout.movimientos + ","+ segundosTranscurridos+")";
            db.execSQL(sql_insert_ranking8);
            KToast.customColorToast(PuzzleActivity.this, text+"", Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.info, R.drawable.ic_add_black_24dp);
        }else if(p_piezas == 4){
            String sql_insert_ranking16 = "INSERT INTO ranking_16 (cve_usuario,puntuacion,movimientos,tiempo) " +
                    "values (" + cve_usuario + "," + puntuacion + ","+ puzzleLayout.movimientos + ","+ segundosTranscurridos+")";
            db.execSQL(sql_insert_ranking16);
            KToast.customColorToast(PuzzleActivity.this, text+"", Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.info, R.drawable.ic_add_black_24dp);
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
                Intent ListarRanking4 = new Intent(PuzzleActivity.this, Raking4Activity.class);
                PuzzleActivity.this.startActivity(ListarRanking4);
                break;
            case R.id.itm8piezas:
                Intent ListarRanking8 = new Intent(PuzzleActivity.this, Ranking8Activity.class);
                PuzzleActivity.this.startActivity(ListarRanking8);
                break;
            case R.id.itm16piezas:
                Intent ListarRanking16 = new Intent(PuzzleActivity.this, Ranking16Activity.class);
                PuzzleActivity.this.startActivity(ListarRanking16);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
