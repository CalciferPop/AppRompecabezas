package com.example.hugopc.rompecabezas2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onurkaganaldemir.ktoastlib.KToast;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Login2Activity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;

    UsuariosDbHelper objUsua;
    SQLiteDatabase objSQL;

    EditText txt_nickname;
    EditText txt_pass;
    private Cursor fila;

    String [] array_niknames;
    Boolean a_flag =true;





    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {

        }
    };



    private Button btn_acceder;
    TextView txt_acercaDe;





    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login2);

        btn_acceder = (Button)findViewById(R.id.btn_acceder);
        txt_acercaDe = (TextView) findViewById(R.id.txtAcercade);
        txt_nickname = (EditText) findViewById(R.id.txt_login_nickname);
        txt_pass = (EditText) findViewById(R.id.txt_login_password);

        btn_acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence textd = getApplicationContext().getString(R.string.str_noNick);
                if (txt_nickname.getText().length()==0 || txt_pass.getText().length()==0){
                    KToast.customColorToast(Login2Activity.this, textd.toString(), Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.warning, R.drawable.ic_warning_black_24dp);
                }else{
                    m_ingresar();
                }
            }
        });

        txt_acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AcercaDEIntent = new Intent(Login2Activity.this,AcercaDeActivity.class);
                Login2Activity.this.startActivity(AcercaDEIntent);
            }
        });

        mVisible = true;
    }


    public void m_ingresar(){
        UsuariosDbHelper objBD = new UsuariosDbHelper(this,"ROMPECABEZAS",null,1);
        SQLiteDatabase db = objBD.getWritableDatabase();

        String nickname= txt_nickname.getText().toString();
        String password = txt_pass.getText().toString();
        //String sql_insert_usuario = "INSERT INTO usuario (nickname) values ('" + nickname + "')";
        String sql_insert_usuario = "INSERT INTO usuario (nickname,password) values ('" + nickname + "','" + password + "')";
        //Toast.makeText(this,sql_insert_usuario,Toast.LENGTH_LONG).show();
        fila= db.rawQuery("SELECT nickname,password FROM usuario where nickname='" + nickname + "'",null);
        if(fila.moveToFirst()==true){
            String nick = fila.getString(0);
            String pass = fila.getString(1);
            if(pass.equals(password)){
                CharSequence text = this.getString(R.string.str_bienvenido) + "  "+ nickname;
                if (nick.equals(nickname)){
                    KToast.customColorToast(Login2Activity.this, text + "", Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.success, R.drawable.ic_check_black_24dp);
                    Intent UsuaExi = new Intent(Login2Activity.this,SeleccionActivity.class);
                    UsuaExi.putExtra("nickname", nickname);
                    Login2Activity.this.startActivity(UsuaExi);
                    //txt_nickname.setText("");
                }
            }else{
                CharSequence text = this.getString(R.string.str_bad_pass);
                KToast.customColorToast(Login2Activity.this, text + "", Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.warning, R.drawable.ic_warning_black_24dp);
            }

        }else{
            CharSequence text = this.getString(R.string.str_nuevoUsuario) + "  "+ nickname;

            KToast.customColorToast(Login2Activity.this, text+"", Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.info, R.drawable.ic_add_black_24dp);
            db.execSQL(sql_insert_usuario);
            Intent UsuarioNuevo = new Intent(Login2Activity.this,SeleccionActivity.class);
            UsuarioNuevo.putExtra("nickname", nickname);
            Login2Activity.this.startActivity(UsuarioNuevo);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
//        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }




}
