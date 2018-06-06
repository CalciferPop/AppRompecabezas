package com.example.hugopc.rompecabezas2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.onurkaganaldemir.ktoastlib.KToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectImagenActivity extends AppCompatActivity {

    String dificultad;
    String nickname;
    boolean img_01_check=false,img_02_check=false;
    String mipmapID;
    int drawable_ID=0;
    int piezas=0;

    @BindView(R.id.img_var_01) ImageView img_var01;
    @BindView(R.id.img_var_02) ImageView img_var02;
    @BindView(R.id.btn_lanza_puzzle)
    Button btn_lanza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_imagen);
        ButterKnife.bind(this);
        dificultad = getIntent().getStringExtra("dificultad");
        nickname = getIntent().getStringExtra("nickname");

        m_carga_img(dificultad);
    }


    public void m_carga_img(String p_dif){

        if (p_dif.equals("facil")){
       //     KToast.customColorToast(SelectImagenActivity.this, "Carga Imagenes Facil: " + nickname, Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.info, R.drawable.ic_info);
            img_var01.setImageResource(R.mipmap.pic_facil_01);
            img_var02.setImageResource(R.mipmap.pic_facil_02);

        }else if(p_dif.equals("media")){
          //  KToast.customColorToast(SelectImagenActivity.this, "Carga Imagenes Media: " + nickname, Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.info, R.drawable.ic_info);
            img_var01.setImageResource(R.mipmap.pic_media_01);
            img_var02.setImageResource(R.mipmap.pic_media_02);


        }else if(p_dif.equals("alta")){
           // KToast.customColorToast(SelectImagenActivity.this, "Carga Imagenes Alta: " +nickname, Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.info, R.drawable.ic_info);
            img_var01.setImageResource(R.mipmap.pic_alta_01);
            img_var02.setImageResource(R.mipmap.pic_alta_02);
        }
    }

    @OnClick({R.id.img_var_01})
    public void onClick(View view) {
        if (img_02_check=true && dificultad.equals("facil")){
            img_var01.setImageResource(R.mipmap.pic_facil_01_check);
            img_var02.setImageResource(R.mipmap.pic_facil_02);
            img_01_check=true;
            img_02_check=false;
            mipmapID="R.mipmap.pic_facil_01";
            drawable_ID =R.mipmap.pic_facil_01;
            piezas=2;
        }else if(img_02_check=true && dificultad.equals("media")){
            img_var01.setImageResource(R.mipmap.pic_media_01_check);
            img_var02.setImageResource(R.mipmap.pic_media_02);
            img_01_check=true;
            img_02_check=false;
            mipmapID="R.mipmap.pic_media_01";
            drawable_ID =R.mipmap.pic_media_01;
            piezas=3;
        }else if(img_02_check = true && dificultad.equals("alta")){
            img_var01.setImageResource(R.mipmap.pic_alta_01_check);
            img_var02.setImageResource(R.mipmap.pic_alta_02);
            img_01_check=true;
            img_02_check=false;
            mipmapID="R.mipmap.pic_alta_01";
            drawable_ID=R.mipmap.pic_alta_01;
            piezas=4;
        }


    }

    @OnClick({R.id.img_var_02})
    public void onClick2(View view) {
        if (img_01_check=true && dificultad.equals("facil")){
            img_var02.setImageResource(R.mipmap.pic_facil_02_check);
            img_var01.setImageResource(R.mipmap.pic_facil_01);
            img_02_check=true;
            img_01_check=false;
            mipmapID="R.mipmap.pic_facil_02";
            drawable_ID =R.mipmap.pic_facil_02;
            piezas=2;
        }else if(img_01_check=true && dificultad.equals("media")){
            img_var02.setImageResource(R.mipmap.pic_media_02_check);
            img_var01.setImageResource(R.mipmap.pic_media_01);
            img_02_check=true;
            img_01_check=false;
            mipmapID="R.mipmap.pic_media_02";
            drawable_ID=R.mipmap.pic_media_02;
            piezas=3;
        }else if(img_01_check=true && dificultad.equals("alta")){
            img_var02.setImageResource(R.mipmap.pic_alta_02_check);
            img_var01.setImageResource(R.mipmap.pic_alta_01);
            img_02_check=true;
            img_01_check=false;
            mipmapID="R.mipmap.pic_alta_02";
            drawable_ID=R.mipmap.pic_alta_02;
            piezas=4;
        }



    }

    @OnClick({R.id.btn_lanza_puzzle})
    public void onClickbtn(View view) {
        Intent IntentPuzzle = new Intent(SelectImagenActivity.this,PuzzleActivity.class);
       // IntentPuzzle.putExtra("mipmapID", mipmapID);
        IntentPuzzle.putExtra("drawable_ID",drawable_ID);
        IntentPuzzle.putExtra("piezas",piezas);
        IntentPuzzle.putExtra("nickname",nickname);
        SelectImagenActivity.this.startActivity(IntentPuzzle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.itm4piezas:
                Intent ListarUsuarios = new Intent(SelectImagenActivity.this,Raking4Activity.class);
                SelectImagenActivity.this.startActivity(ListarUsuarios );
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
