package com.example.hugopc.rompecabezas2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by hugopc on 17/03/18.
 */

public class UsuariosDbHelper extends SQLiteOpenHelper {

    String sql_create_usuario= "CREATE TABLE usuario (" +
            "cve_usuario INTEGER PRIMARY KEY," +
            "password varchar(50) NOT NULL," +
            "nickname  VARCHAR(25))";

    String sql_create_ranking_4 ="CREATE TABLE ranking_4 (" +
            "id_ranking INT PRIMARY KEY," +
            "cve_usuario INTEGER NOT NULL," +
            "puntuacion DOUBLE NOT NULL," +
            "movimientos INTEGER NOT NULL," +
            "tiempo DOUBLE NOT NULL," +
            "FOREIGN KEY (cve_usuario) REFERENCES usuario (cve_usuario))";

    String sql_create_ranking_16 ="CREATE TABLE ranking_16 (" +
            "id_ranking INT PRIMARY KEY," +
            "cve_usuario INTEGER NOT NULL," +
            "puntuacion DOUBLE NOT NULL," +
            "movimientos INTEGER NOT NULL," +
            "tiempo DOUBLE NOT NULL," +
            "FOREIGN KEY (cve_usuario) REFERENCES usuario (cve_usuario))";

    String sql_create_ranking_8 ="CREATE TABLE ranking_8 (" +
            "id_ranking INT PRIMARY KEY," +
            "cve_usuario INTEGER NOT NULL," +
            "puntuacion DOUBLE NOT NULL," +
            "movimientos INTEGER NOT NULL," +
            "tiempo DOUBLE NOT NULL," +
            "FOREIGN KEY (cve_usuario) REFERENCES usuario (cve_usuario))";

    public UsuariosDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
        db.execSQL(sql_create_usuario);
        db.execSQL("INSERT INTO usuario (nickname,password) VALUES ('Calcifer','hugopc')");
        db.execSQL("INSERT INTO usuario (nickname,password) VALUES ('CalciferPop','hugopc')");
        db.execSQL("INSERT INTO usuario (nickname,password) VALUES ('qwe','qwe')");
        db.execSQL(sql_create_ranking_4);
        db.execSQL(sql_create_ranking_16);
        db.execSQL(sql_create_ranking_8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList m_llenar_lista() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_lista_usuarios = "SELECT * FROM usuario";
        Cursor registro = db.rawQuery(sql_lista_usuarios,null);
        if (registro.moveToFirst()){
            do{
                lista.add(registro.getString(0) + ": " + registro.getString(1) + ": " + registro.getString(2));
            }while (registro.moveToNext());
        }
        return  lista;
    }



    public ArrayList m_llenar_ranking4() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_select_ranking4 = "SELECT u.nickname,ra.puntuacion FROM ranking_4 ra INNER JOIN usuario u ON u.cve_usuario=ra.cve_usuario ORDER BY ra.puntuacion DESC";
        Cursor registro = db.rawQuery(sql_select_ranking4,null);
        if (registro.moveToFirst()){
            do{
                lista.add(registro.getString(0) + ": " + registro.getDouble(1));
            }while (registro.moveToNext());
        }
        return  lista;
    }

    public ArrayList m_llenar_ranking8() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_select_ranking4 = "SELECT u.nickname,ra.puntuacion FROM ranking_8 ra INNER JOIN usuario u ON u.cve_usuario=ra.cve_usuario ORDER BY ra.puntuacion DESC";
        Cursor registro = db.rawQuery(sql_select_ranking4,null);
        if (registro.moveToFirst()){
            do{
                lista.add(registro.getString(0) + ": " + registro.getDouble(1));
            }while (registro.moveToNext());
        }
        return  lista;
    }

    public ArrayList m_llenar_ranking16() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_select_ranking4 = "SELECT u.nickname,ra.puntuacion FROM ranking_16 ra INNER JOIN usuario u ON u.cve_usuario=ra.cve_usuario ORDER BY ra.puntuacion DESC";
        Cursor registro = db.rawQuery(sql_select_ranking4,null);
        if (registro.moveToFirst()){
            do{
                lista.add(registro.getString(0) + ": " + registro.getDouble(1));
            }while (registro.moveToNext());
        }
        return  lista;
    }





    public void m_imprimeLista(ArrayList p_arraylist){
        for (int i=0;i<=p_arraylist.size()-1;i++)
            System.out.println(p_arraylist.get(i));
    }

}
