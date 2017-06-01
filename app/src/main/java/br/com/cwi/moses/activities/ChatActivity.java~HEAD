package br.com.cwi.moses.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.cwi.moses.R;
import br.com.cwi.moses.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.chat_view)
    ChatView chatView;

    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        this.initComponents();
    }

    private void initComponents() {
        final ChatService chatService = new ChatService(this.chatView, this);
        this.chatService = chatService;

        this.chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                chatService.sendMessage(chatMessage);
                return true;
            }
        });

        this.chatService.adicionaMensagemRecebida("Olá, em que posso ajudar?");
    }
}
