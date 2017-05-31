package br.com.cwi.moses.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    public String name;
    public String email;
    public String userId;

    public List<Ticket> tickets;

    public User(){
        tickets = new ArrayList<>();
    }
}
