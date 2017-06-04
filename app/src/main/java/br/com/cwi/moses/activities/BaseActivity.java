package br.com.cwi.moses.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.cwi.moses.services.TicketService;

public class BaseActivity extends AppCompatActivity {
    private AlertDialog.Builder alert;
    private ProgressDialog progress;

    // Este service precisa ser instanciado aqui para já começar a ouvir eventos do Firebase.
    private TicketService ticketService = TicketService.getInstance();

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