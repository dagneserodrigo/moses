package br.com.cwi.moses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.cwi.moses.adapter.TicketAdapter;
import br.com.cwi.moses.model.TipoTicket;
import br.com.cwi.moses.service.TicketApiService;

public class TicketListActivity extends AppCompatActivity {

    private RecyclerView ticket_list;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private TicketApiService ticketApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);

        setTitle("Tickets");

        this.initComponents();
        this.initList();
    }

    public void novoTicketSugestao(View view) {
        this.goToNewTicket(TipoTicket.SUGESTAO);
    }

    public void novoTicketDuvida(View view) {
        this.goToNewTicket(TipoTicket.DUVIDA);
    }

    public void novoTicketProblema(View view) {
        this.goToNewTicket(TipoTicket.PROBLEMA);
    }

    private void goToNewTicket(TipoTicket tipoTicket) {
        Intent intentTicketForm = new Intent(this, TicketFormActivity.class);
        intentTicketForm.putExtra("TICKET_TIPO", tipoTicket);
        startActivity(intentTicketForm);
    }

    private void initComponents() {
        this.ticketApiService = new TicketApiService();

        this.ticket_list = (RecyclerView) findViewById(R.id.ticket_list);
    }

    private void initList() {
        this.layoutManager = new LinearLayoutManager(this);
        this.adapter = new TicketAdapter(this.ticketApiService.getAllTickets());

        this.ticket_list.setLayoutManager(this.layoutManager);
        this.ticket_list.setAdapter(this.adapter);
    }
}
