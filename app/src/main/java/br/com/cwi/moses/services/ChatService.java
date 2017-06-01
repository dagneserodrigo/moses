package br.com.cwi.moses.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import br.com.cwi.moses.activities.ChatActivity;
import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

/**
 * Created by Murillo on 30/05/2017.
 */

public class ChatService {

    private static final String URL_SEND_MESSAGE = "http://moses-api.herokuapp.com/bot";
    private static final String TAG_CHAT_SERVICE = "ChatService";

    private ChatView chatView;
    private ChatActivity activity;

    public ChatService(ChatView chatView, ChatActivity activity) {
        this.chatView = chatView;
        this.activity = activity;
    }

    public void sendMessage(ChatMessage chatMessage) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("mensagem", chatMessage.getMessage());
        } catch (JSONException e) {
            Log.e(TAG_CHAT_SERVICE, "Erro ao setar mensagem do body", e);
        }
        final String requestBody = jsonBody.toString();
        RequestQueue queue = Volley.newRequestQueue((Context)this.activity);
        StringRequest strRequest = new StringRequest(Request.Method.POST, this.URL_SEND_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            onSendMessageSuccess(json.getString("resposta"));
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
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

        };
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
