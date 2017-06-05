package br.com.cwi.moses.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.cwi.moses.R;
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

    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_home:
                this.goHome();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goHome() {

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}