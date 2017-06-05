package br.com.cwi.moses.services;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cwi.moses.adapters.TicketAdapter;
import br.com.cwi.moses.models.SituacaoTicket;
import br.com.cwi.moses.models.Ticket;
import br.com.cwi.moses.models.TipoTicket;
import br.com.cwi.moses.utils.Constants;

public class TicketService implements ChildEventListener {

    private static FirebaseUser USER;

    private static TicketService ticketService;

    private FirebaseDatabase database;

    private List<Ticket> tickets;

    private TicketAdapter adapter;

    private TicketService(){
        tickets = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        DatabaseReference dbReference = database.getReference(Constants.USER_CHILD_FRD);
        dbReference.addChildEventListener(this);
    }

    public static TicketService getInstance( ) {
        if(ticketService == null)
            ticketService = new TicketService();
        return ticketService;
    }

    public List<Ticket> getAllTickets() {
        this.updateCurrentUser();
        return getByUser(USER.getUid());
    }

    public void addTicketFromForm(String titulo, String descricao, TipoTicket tipo) {
        this.updateCurrentUser();
        Ticket ticket = new Ticket(titulo, descricao, tipo, SituacaoTicket.ABERTO, USER.getUid());
        this.add(ticket);
    }

    private void updateCurrentUser() {
        this.USER = FirebaseAuth.getInstance().getCurrentUser();
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
        notifyChanges();
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

    public void notifyChanges(){
        if(adapter != null) {
            adapter.setSource(getAllTickets());
            adapter.notifyDataSetChanged();
        }
    }

    public void setListener(TicketAdapter adapter){
        this.adapter = adapter;
    }
}
