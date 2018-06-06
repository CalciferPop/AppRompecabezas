package com.example.hugopc.rompecabezas2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * Created by hugopc on 9/03/18.
 */



public class hilo extends AsyncTask<Void,Integer,Void> {

    private ProgressBar progressBar;
    private Context context;

    public hilo(ProgressBar progressBar, Context context){
        this.progressBar = progressBar;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Intent InicioIntent = new Intent(context,Login2Activity.class);
        context.startActivity(InicioIntent);


    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);

    }

    @Override
    protected Void doInBackground(Void... voids) {
        //for(int i=1;i<=5;i++){
        //publishProgress(i*20);

        for (int i=1;i<=100;i++){
            publishProgress(i);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }





}
