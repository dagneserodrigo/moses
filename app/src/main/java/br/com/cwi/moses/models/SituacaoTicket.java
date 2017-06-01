package br.com.cwi.moses.models;

/**
 * Created by Murillo on 31/05/2017.
 */

public enum SituacaoTicket {

    ABERTO("Aberto"),
    RESPONDIDO("Respondido");

    private String name;

    SituacaoTicket(String name) {
        this.name = name;
    }
}
