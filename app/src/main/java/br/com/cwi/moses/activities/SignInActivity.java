package br.com.cwi.moses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.cwi.moses.R;
import br.com.cwi.moses.services.AuthService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends BaseActivity {

    private AuthService authService;

    @BindView(R.id.txtEmail) EditText txtEmail;
    @BindView(R.id.txtPassword) EditText txtPassword;
    @BindView(R.id.btnSignIn) Button btnSignIn;
    @BindView(R.id.btnSignUp) Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        initComponents();

    }

    private void initComponents() {
        authService = new AuthService(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(txtEmail.getText().toString(), txtPassword.getText().toString());
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

//    PASSAR CONTROLE PARA BASE ACTIVITY
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
//    }

    private void signIn(String email, String password){
        authService.signIn(email, password);
    }

    public void signUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
