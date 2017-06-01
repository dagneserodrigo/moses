package br.com.cwi.moses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import br.com.cwi.moses.R;
import br.com.cwi.moses.services.MessageService;
import br.com.cwi.moses.services.TokenService;

public class MainActivity extends BaseActivity {

    private MessageService messageService;
    private TokenService tokenService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String token = FirebaseInstanceId.getInstance().getToken();

        messageService = new MessageService();
        tokenService = new TokenService();
    }

    public void goToChat(View view) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void goToTicket(View view) {
        Intent intent = new Intent(this, TicketListActivity.class);
        startActivity(intent);
    }
}
