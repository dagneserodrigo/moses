package br.com.cwi.moses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.cwi.moses.R;
import br.com.cwi.moses.adapters.TicketAdapter;
import br.com.cwi.moses.models.Ticket;
import br.com.cwi.moses.models.TipoTicket;
import br.com.cwi.moses.services.TicketService;

public class TicketListActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView ticket_list;
    private RecyclerView.LayoutManager layoutManager;
    private TicketAdapter adapter;

    private TicketService ticketService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);

        setTitle("Tickets");

        this.initComponents();
        this.initList();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        this.ticketService = TicketService.getInstance();

        this.ticket_list = (RecyclerView) findViewById(R.id.ticket_list);
    }

    private void initList() {
        this.layoutManager = new LinearLayoutManager(this);
        this.adapter = new TicketAdapter(this.ticketService.getAllTickets());
        this.ticketService.setListener(this.adapter);

        this.ticket_list.setLayoutManager(this.layoutManager);
        this.ticket_list.setAdapter(this.adapter);
    }

    @Override
    public void onClick(View v) {
        int position = ticket_list.getChildLayoutPosition(v);
        Ticket ticket = this.adapter.getTicketFromPosition(position);
        Intent intentTicketForm = new Intent(this, TicketDetalheActivity.class);
        intentTicketForm.putExtra("TICKET", ticket);
        startActivity(intentTicketForm);
    }
}
