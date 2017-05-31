package br.com.cwi.moses.models;

public class Ticket {
    public String description;
    public String createAt;

    public Ticket(String description, String createAt){
        this.description = description;
        this.createAt = createAt;
    }
}
