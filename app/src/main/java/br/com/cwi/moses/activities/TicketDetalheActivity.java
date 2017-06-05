package br.com.cwi.moses.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
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

    @BindView(R.id.detalhe_txt_dataAbertura)
    TextView detalhe_txt_dataAbertura;

    @BindView(R.id.detalhe_txt_situacao)
    TextView detalhe_txt_situacao;

    @BindView(R.id.detalhe_txt_pergunta)
    TextView detalhe_txt_pergunta;

    @BindView(R.id.detalhe_txt_resposta_titulo)
    TextView detalhe_txt_resposta_titulo;

    @BindView(R.id.detalhe_txt_resposta)
    TextView detalhe_txt_resposta;

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

        this.detalhe_txt_dataAbertura.setText(data);
        this.detalhe_txt_titulo.setText(ticket.getTitulo());
        this.detalhe_txt_situacao.setText(ticket.getSituacaoTicket().getName());
        this.detalhe_txt_pergunta.setText(ticket.getDescricao());

        String respostaTicket = ticket.getRespostaTicket();
        if (respostaTicket != null && !respostaTicket.trim().isEmpty()) {
            this.detalhe_txt_resposta_titulo.setVisibility(View.VISIBLE);
            this.detalhe_txt_resposta.setVisibility(View.VISIBLE);
            this.detalhe_txt_resposta.setText(respostaTicket);

            this.detalhe_txt_resposta_titulo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            this.detalhe_txt_resposta.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        }
    }
}
