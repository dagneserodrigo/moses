package br.com.cwi.moses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import br.com.cwi.moses.R;
import br.com.cwi.moses.services.AuthService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private AuthService authService;

    @BindView(R.id.cardLogout) CardView cardLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents() {
        authService = new AuthService(this);

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authService.signOut();
            }
        });
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
