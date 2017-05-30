package br.com.cwi.moses.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import br.com.cwi.moses.ChatActivity;
import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

/**
 * Created by Murillo on 30/05/2017.
 */

public class ChatApiService {

    // TODO
    private static final String URL_SEND_MESSAGE = "www.teste.cwi.com";

    private ChatView chatView;
    private ChatActivity activity;

    public ChatApiService(ChatView chatView, ChatActivity activity) {
        this.chatView = chatView;
        this.activity = activity;
    }

    public void sendMessage(ChatMessage chatMessage) {
        RequestQueue queue = Volley.newRequestQueue((Context)this.activity);
        StringRequest strRequest = new StringRequest(Request.Method.POST, this.URL_SEND_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onSendMessageSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onSendMessageError(error);
                    }
                }
        );
        queue.add(strRequest);
    }

    private void onSendMessageSuccess(String response) {
        this.adicionaMensagemRecebida(response);
    }

    private void onSendMessageError(VolleyError error) {
        this.adicionaMensagemRecebida("Não recebi sua mensagem. Verifique sua conexão com a internet.");
        this.adicionaErroNaTela();
    }

    public void adicionaMensagemRecebida(String mensagem) {
        this.chatView.addMessage(new ChatMessage(mensagem, System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
    }
    
    private void adicionaErroNaTela() {
        // TODO
    }
}
