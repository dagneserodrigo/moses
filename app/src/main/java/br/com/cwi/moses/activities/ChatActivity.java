package br.com.cwi.moses.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.cwi.moses.R;
import br.com.cwi.moses.services.ChatService;
import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class ChatActivity extends AppCompatActivity {

    private ChatView chatView;

    private ChatService chatApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.initComponents();
    }

    private void initComponents() {
        this.chatView = (ChatView) findViewById(R.id.chat_view);

        final ChatService chatApiService = new ChatService(this.chatView, this);
        this.chatApiService = chatApiService;

        this.chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                chatApiService.sendMessage(chatMessage);
                return true;
            }
        });

        this.chatApiService.adicionaMensagemRecebida("Olá, em que posso ajudar?");
    }
}
