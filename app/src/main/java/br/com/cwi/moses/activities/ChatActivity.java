package br.com.cwi.moses.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import br.com.cwi.moses.R;
import br.com.cwi.moses.chat.Message;
import br.com.cwi.moses.services.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity implements MessageInput.InputListener {

    @BindView(R.id.messagesList)
    MessagesList messagesList;

    @BindView(R.id.input)
    MessageInput input;

    private MessagesListAdapter<Message> adapter;
    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        this.initComponents();
    }

    private void initComponents() {
        adapter = new MessagesListAdapter<>("user", null);
        messagesList.setAdapter(adapter);
        input.setInputListener(this);
        chatService = new ChatService(this, this.adapter);
    }

    @Override
    public boolean onSubmit(CharSequence charSequence) {
        this.chatService.sendMessage(charSequence.toString());
        return true;
    }
}
