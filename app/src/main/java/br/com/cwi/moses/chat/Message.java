package br.com.cwi.moses.chat;

import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.Date;

/**
 * Created by Murillo on 02/06/2017.
 */

public class Message implements IMessage {

    private String id;
    private String text;
    private Author author;
    private Date createdAt;

    public Message(String text, Author user) {
        this.text = text;
        this.createdAt = new Date();
        this.id = "id";
        this.author = user;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Author getUser() {
        return author;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }
}