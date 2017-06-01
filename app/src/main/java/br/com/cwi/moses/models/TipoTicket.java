package br.com.cwi.moses.models;

import java.io.Serializable;

/**
 * Created by Murillo on 31/05/2017.
 */

public enum TipoTicket implements Serializable {

    SUGESTAO("Sugestão"),
    DUVIDA("Dúvida"),
    PROBLEMA("Problema");

    private String name;

    TipoTicket(String name) {
        this.name = name;
    }
}
