package br.com.cwi.moses.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Murillo on 31/05/2017.
 */

public class Ticket implements Serializable {

    public String id;

    public String titulo;

    public String descricao;

    public String respostaTicket;

    public TipoTicket tipoTicket;

    public SituacaoTicket situacaoTicket;

    public Date dataAberturaTicket;

    public String userId;

    public Ticket(String titulo, String descricao, TipoTicket tipoTicket, SituacaoTicket situacaoTicket) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipoTicket = tipoTicket;
        this.situacaoTicket = situacaoTicket;
        this.dataAberturaTicket = new Date();
    }

    public Ticket(){}

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

