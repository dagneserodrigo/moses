package br.com.cwi.moses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class ChatActivity extends AppCompatActivity {

    private ChatView chatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.initComponents();
    }

    private void initComponents() {
        this.chatView = (ChatView) findViewById(R.id.chat_view);

        this.chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                // perform actual message sending
                return true;
            }
        });

        this.chatView.addMessage(new ChatMessage("RESPOSTA", 5494, ChatMessage.Type.RECEIVED));
    }
}
