package br.com.cwi.moses.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import br.com.cwi.moses.R;
import br.com.cwi.moses.models.Ticket;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketDetalheActivity extends AppCompatActivity {

    @BindView(R.id.detalhe_txt_titulo)
    TextView detalhe_txt_titulo;

    @BindView(R.id.item_list_txt_data)
    TextView item_list_txt_data;

    @BindView(R.id.item_list_txt_situacao)
    TextView item_list_txt_situacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detalhe);
        ButterKnife.bind(this);

        this.initData();
    }

    private void initData() {
        Ticket ticket = (Ticket) getIntent().getSerializableExtra("TICKET");

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM HH:mm");
        String data = sdfDate.format(ticket.getDataAberturaTicket());

        this.item_list_txt_data.setText(data);
        this.detalhe_txt_titulo.setText(ticket.getTitulo());
        this.item_list_txt_situacao.setText(ticket.getSituacaoTicket().name());
    }
}
