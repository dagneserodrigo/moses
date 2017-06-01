package br.com.cwi.moses.service;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cwi.moses.model.SituacaoTicket;
import br.com.cwi.moses.model.Ticket;
import br.com.cwi.moses.model.TipoTicket;
import br.com.cwi.moses.util.Constantes;

public class TicketApiService implements ChildEventListener {

    private FirebaseDatabase database;
    private List<Ticket> tickets;

    public TicketApiService(){
        tickets = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        DatabaseReference dbReference = database.getReference(Constantes.USER_CHILD_FRD);
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

        DatabaseReference dbReference = database.getReference(Constantes.USER_CHILD_FRD);
        DatabaseReference dbTicketsReference = dbReference.child(userId).child(Constantes.TICKET_CHILD_FRD);

        String key = dbTicketsReference.getKey();

        return list;
    }

    public void add(Ticket ticket){
        DatabaseReference dbReference = database.getReference(Constantes.USER_CHILD_FRD);
        String keyId = dbReference.child(ticket.userId).child(Constantes.TICKET_CHILD_FRD).push().getKey();
        ticket.id = keyId;
        dbReference.child(ticket.userId).child(Constantes.TICKET_CHILD_FRD).child(keyId).setValue(ticket);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        DataSnapshot retorno = dataSnapshot.child(Constantes.TICKET_CHILD_FRD);
        for(DataSnapshot item : retorno.getChildren()){
            Ticket ticket = item.getValue(Ticket.class);
            if(getTicketById(ticket.id) == null)
                tickets.add(ticket);
        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        DataSnapshot ticketChild = dataSnapshot.child(Constantes.TICKET_CHILD_FRD);
        for(DataSnapshot item : ticketChild.getChildren()){
            Ticket ticket = item.getValue(Ticket.class);
            tickets.remove(getTicketById(ticket.id));
            tickets.add(ticket);
        }
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        DataSnapshot ticketChild = dataSnapshot.child(Constantes.TICKET_CHILD_FRD);
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
