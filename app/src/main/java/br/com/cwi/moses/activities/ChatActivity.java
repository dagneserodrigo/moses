package br.com.cwi.moses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import br.com.cwi.moses.R;
import br.com.cwi.moses.chat.Message;
import br.com.cwi.moses.models.TipoTicket;
import br.com.cwi.moses.services.ChatService;
import br.com.cwi.moses.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.com.cwi.moses.R.color.colorAccent;

public class ChatActivity extends AppCompatActivity
        implements MessageInput.InputListener,
        MessagesListAdapter.OnMessageClickListener<Message> {

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
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Picasso.with(ChatActivity.this).load(url).into(imageView);
            }
        };

        adapter = new MessagesListAdapter<>("user", imageLoader);
        adapter.setOnMessageClickListener(this);
        messagesList.setAdapter(adapter);
        input.setInputListener(this);
        chatService = new ChatService(this, this.adapter);

        chatService.adicionaMensagemRecebida("Olá, em que posso ajudar?");
    }

    @Override
    public boolean onSubmit(CharSequence charSequence) {
        this.chatService.sendMessage(charSequence.toString());
        return true;
    }

    @Override
    public void onMessageClick(Message message) {
        if (message.getText().equals(Constants.IR_PARA_TICKET)) {
            this.abrirTicketProblema();
        }
    }

    private void abrirTicketProblema() {
        Intent intentTicketForm = new Intent(this, TicketFormActivity.class);
        intentTicketForm.putExtra("TICKET_TIPO", TipoTicket.PROBLEMA);
        startActivity(intentTicketForm);
    }
}
