package br.com.cwi.moses.models;

/**
 * Created by Murillo on 31/05/2017.
 */

public enum TipoTicket {

    SUGESTAO("Sugestão"),
    DUVIDA("Dúvida"),
    PROBLEMA("Problema");

    private String name;

    TipoTicket(String name) {
        this.name = name;
    }
}
