package br.com.cwi.moses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.cwi.moses.util.Constantes;

public class LoginActivity extends BaseActivity {

    private EditText txtEmail;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void initializeComponents(){
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
    }

    private void login(String email, String password){
        showLoader(Constantes.WAIT_LOADER);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            hideLoader();
                            if (!task.isSuccessful()) {
                                if(task.getException().getMessage().indexOf("network error") != -1){
                                    showError(Constantes.INTERNET_ERROR);
                                }else{
                                    showError("Falha na authenticaçao");
                                }
                            } else {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
    }

    public void login(View view){

        String email = txtEmail.getText().toString();
        String senha = txtPassword.getText().toString();

        if(!email.isEmpty() && !senha.isEmpty()){
            login(email, senha);
        }
    }

    public void openNewAccount(View view){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}
