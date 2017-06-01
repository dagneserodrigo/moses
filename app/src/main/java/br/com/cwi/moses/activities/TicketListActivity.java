package br.com.cwi.moses.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.cwi.moses.R;

public class TicketListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);

        setTitle("Tickets");
    }

    public void novoTicketSugestao(View view) {
        this.goToNewTicket("Sugestão");
    }

    public void novoTicketDuvida(View view) {
        this.goToNewTicket("Dúvida");
    }

    public void novoTicketReclamacao(View view) {
        this.goToNewTicket("Problema");
    }

    private void goToNewTicket(String ticketTipo) {
        Intent intentTicketForm = new Intent(this, TicketFormActivity.class);
        intentTicketForm.putExtra("TICKET_TIPO", ticketTipo);
        startActivity(intentTicketForm);
    }
}
