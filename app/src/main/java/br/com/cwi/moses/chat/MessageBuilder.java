package br.com.cwi.moses.chat;

/**
 * Created by Murillo on 02/06/2017.
 */

public class MessageBuilder {

    private Author user = new Author("user", "Você", "");
    private Author moses = new Author("moses", "Moses",
            "https://yt3.ggpht.com/-ALlyRhpxD1c/AAAAAAAAAAI/AAAAAAAAAAA/uV6kPtoz7w0/s88-c-k-no-mo-rj-c0xffffff/photo.jpg");

    public Message createMessageUser(String text) {
        return new Message(text, user);
    }

    public Message createMessageMoses(String text) {
        return new Message(text, moses);
    }
}
