package br.com.cwi.moses.service;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

/**
 * Created by Murillo on 30/05/2017.
 */

public class ChatApiService {

    private ChatView chatView;

    public ChatApiService(ChatView chatView) {
        this.chatView = chatView;
    }

    public void sendMessage(ChatMessage chatMessage) {
        // TODO
    }

    public void adicionaMensagemRecebida(String mensagem) {
        this.chatView.addMessage(new ChatMessage(mensagem, System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
    }
}
