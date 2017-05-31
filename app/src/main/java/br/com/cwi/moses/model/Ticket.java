package br.com.cwi.moses.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Murillo on 31/05/2017.
 */

public class Ticket implements Serializable {

    private String titulo;

    private String descricao;

    private String respostaTicket;

    private TipoTicket tipoTicket;

    private SituacaoTicket situacaoTicket;

    private Date dataAberturaTicket;

    public Ticket(String titulo, String descricao, TipoTicket tipoTicket, SituacaoTicket situacaoTicket) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipoTicket = tipoTicket;
        this.situacaoTicket = situacaoTicket;
        this.dataAberturaTicket = new Date();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getRespostaTicket() {
        return respostaTicket;
    }

    public TipoTicket getTipoTicket() {
        return tipoTicket;
    }

    public SituacaoTicket getSituacaoTicket() {
        return situacaoTicket;
    }

    public Date getDataAberturaTicket() {
        return dataAberturaTicket;
    }
}

