package br.com.cwi.moses.chat;

/**
 * Created by Murillo on 02/06/2017.
 */

public class MessageBuilder {

    private Author user = new Author("user", "Você", "");
    private Author moses = new Author("moses", "Moses", "");

    public Message createMessageUser(String text) {
        return new Message(text, user);
    }

    public Message createMessageMoses(String text) {
        return new Message(text, moses);
    }
}
