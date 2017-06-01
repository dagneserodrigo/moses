package br.com.cwi.moses.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {
    private AlertDialog.Builder alert;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        alert = new AlertDialog.Builder(this);
    }

    public void showLoader(String text){
        progress.setMessage(text);
        progress.show();
    }

    public void hideLoader(){
        progress.dismiss();
    }

    public void showError(String text){
        alert.setMessage(text);
        alert.show();
    }
}