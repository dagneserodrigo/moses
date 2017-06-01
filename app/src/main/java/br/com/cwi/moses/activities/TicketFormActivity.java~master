package br.com.cwi.moses.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import br.com.cwi.moses.R;

public class TicketFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_form);

        setTitle("Novo Ticket - " + getIntent().getStringExtra("TICKET_TIPO"));
    }

    public void salvarTicket(View view) {

        this.goToListTickets();
    }

    private void goToListTickets() {
        Intent intent = new Intent(this, TicketListActivity.class);
        startActivity(intent);
    }
}
