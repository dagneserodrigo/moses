package br.com.cwi.moses.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.cwi.moses.activities.ChatActivity;
import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

/**
 * Created by Murillo on 30/05/2017.
 */

public class ChatService {

    // TODO
    private static final String URL_SEND_MESSAGE = "http://www.mocky.io/v2/592dcac01000003f0cd0dbd9";

    private ChatView chatView;
    private ChatActivity activity;

    public ChatService(ChatView chatView, ChatActivity activity) {
        this.chatView = chatView;
        this.activity = activity;
    }

    public void sendMessage(ChatMessage chatMessage) {
        RequestQueue queue = Volley.newRequestQueue((Context)this.activity);
        StringRequest strRequest = new StringRequest(Request.Method.POST, this.URL_SEND_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            onSendMessageSuccess(json.getString("response"));
                        } catch (JSONException je) {
                            Log.e(this.getClass().getName(), je.getMessage(), je);
                        }
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
