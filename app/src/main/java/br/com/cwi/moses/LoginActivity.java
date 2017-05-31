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
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;

import br.com.cwi.moses.models.Ticket;
import br.com.cwi.moses.models.User;
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
                                FirebaseUser user = task.getResult().getUser();

                                User userModel = new User();
                                userModel.email = user.getEmail();
                                userModel.userId = user.getUid();

                                addUserDatabase(userModel);

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

    private void addUserDatabase(User user){
        user.name = user.email.split("@")[0];
        user.tickets.add(new Ticket("primeiro ticket", "data do primeiro"));
        user.tickets.add(new Ticket("segundo ticket", "data do segundo"));
        DatabaseReference dbReference = database.getReference(user.userId);
        String userJson = new Gson().toJson(user);
        dbReference.setValue(userJson);
    }
}
