package br.com.cwi.moses.services;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cwi.moses.models.SituacaoTicket;
import br.com.cwi.moses.models.Ticket;
import br.com.cwi.moses.models.TipoTicket;
import br.com.cwi.moses.utils.Constants;

public class TicketService implements ChildEventListener {

    private FirebaseDatabase database;
    private List<Ticket> tickets;

    public TicketService(){
        tickets = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        DatabaseReference dbReference = database.getReference(Constants.USER_CHILD_FRD);
        dbReference.addChildEventListener(this);
    }

    public List<Ticket> getAllTickets() {
        return this.getFakeList();
    }

    private List<Ticket> getFakeList() {
        List<Ticket> list = new ArrayList<>();

        list.add(new Ticket("Caronas da Empresa", "Poderiamos ter um sistema de gerenciamento de caronas para otimizar as vagas no estacionamento", TipoTicket.SUGESTAO, SituacaoTicket.ABERTO));
        list.add(new Ticket("Conectar Notebook na Rede", "Não  Consigo conectar minha máquina na Rede. Help-me!", TipoTicket.PROBLEMA, SituacaoTicket.ABERTO));
        list.add(new Ticket("Senha do Wifi", "Esqueci a senha do WIFI. Help-me!", TipoTicket.DUVIDA, SituacaoTicket.ABERTO));
        list.add(new Ticket("Videogame", "Podíamos ter videogames à disposição dos funcionários para os horários de lazer. Fica a dica.", TipoTicket.SUGESTAO, SituacaoTicket.ABERTO));
        list.add(new Ticket("Trabalhar de Bermuda", "Pode trabalhar de bermuda no verão?", TipoTicket.DUVIDA, SituacaoTicket.ABERTO));
        list.add(new Ticket("Caronas da Empresa", "Poderiamos ter um sistema de gerenciamento de caronas para otimizar as vagas no estacionamento", TipoTicket.SUGESTAO, SituacaoTicket.ABERTO));
        list.add(new Ticket("Conectar Notebook na Rede", "Não  Consigo conectar minha máquina na Rede. Help-me!", TipoTicket.PROBLEMA, SituacaoTicket.ABERTO));
        list.add(new Ticket("Senha do Wifi", "Esqueci a senha do WIFI. Help-me!", TipoTicket.DUVIDA, SituacaoTicket.ABERTO));
        list.add(new Ticket("Videogame", "Podíamos ter videogames à disposição dos funcionários para os horários de lazer. Fica a dica.", TipoTicket.SUGESTAO, SituacaoTicket.ABERTO));
        list.add(new Ticket("Trabalhar de Bermuda", "Pode trabalhar de bermuda no verão?", TipoTicket.DUVIDA, SituacaoTicket.ABERTO));

        return list;
    }

    public Ticket getTicketById(String id){
        for(Ticket t : tickets)
            if(t.id.equals(id))
                return t;
        return null;
    }

    public List<Ticket> getByUser(String userId){
        List<Ticket> list = new ArrayList<>();
        for(Ticket t : tickets)
            if(t.userId.equals(userId))
                list.add(t);
        return list;
    }

    public void add(Ticket ticket){
        DatabaseReference dbReference = database.getReference(Constants.USER_CHILD_FRD);
        String keyId = dbReference.child(ticket.userId).child(Constants.TICKET_CHILD_FRD).push().getKey();
        ticket.id = keyId;
        dbReference.child(ticket.userId).child(Constants.TICKET_CHILD_FRD).child(keyId).setValue(ticket);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        DataSnapshot ticketChild = dataSnapshot.child(Constants.TICKET_CHILD_FRD);
        for(DataSnapshot item : ticketChild.getChildren()){
            Ticket ticket = item.getValue(Ticket.class);
            if(getTicketById(ticket.id) == null)
                tickets.add(ticket);
        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        DataSnapshot ticketChild = dataSnapshot.child(Constants.TICKET_CHILD_FRD);
        for(DataSnapshot item : ticketChild.getChildren()){
            Ticket ticket = item.getValue(Ticket.class);
            tickets.remove(getTicketById(ticket.id));
            tickets.add(ticket);
        }
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        DataSnapshot ticketChild = dataSnapshot.child(Constants.TICKET_CHILD_FRD);
        for(DataSnapshot item : ticketChild.getChildren()){
            Ticket ticket = item.getValue(Ticket.class);
            tickets.remove(getTicketById(ticket.id));
        }
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

    @Override
    public void onCancelled(DatabaseError databaseError) { }
}
