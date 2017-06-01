package br.com.cwi.moses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.com.cwi.moses.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
