package br.com.cwi.moses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.cwi.moses.service.ChatApiService;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.chat_view)
    ChatView chatView;

    private ChatApiService chatApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        this.initComponents();
    }

    private void initComponents() {
        final ChatApiService chatApiService = new ChatApiService(this.chatView, this);
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
