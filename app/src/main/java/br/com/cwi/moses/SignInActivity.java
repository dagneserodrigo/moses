package br.com.cwi.moses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import br.com.cwi.moses.models.Ticket;
import br.com.cwi.moses.models.User;
import br.com.cwi.moses.util.Constantes;

public class SignInActivity extends BaseActivity {

    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initializeComponents();
    }

    public void initializeComponents(){
        txtName = (EditText)findViewById(R.id.txtName);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtConfirm = (EditText)findViewById(R.id.txtConfirm);
    }

    public void singIn(final String username, String email, String senha){
        showLoader(Constantes.WAIT_LOADER);
        if(!email.isEmpty() && !senha.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email, senha)
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
                                userModel.name = username;
                                userModel.userId = user.getUid();

                                addUserDatabase(userModel);

                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
        }
    }

    public void openLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void signIn(View view){
        String username = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        if(!email.isEmpty() && !password.isEmpty() && !username.isEmpty()){
            singIn(username, email, password);
        }
    }

    private void addUserDatabase(User user){
        DatabaseReference dbReference = database.getReference(user.userId);
        dbReference.setValue(user);
    }
}
