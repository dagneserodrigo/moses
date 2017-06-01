package br.com.cwi.moses.activities;

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

import br.com.cwi.moses.R;
import br.com.cwi.moses.service.FormValidatorService;
import br.com.cwi.moses.util.Constantes;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends BaseActivity {

    @BindView(R.id.txtName)
    EditText txtName;

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.txtConfirm)
    EditText txtConfirm;

    private FormValidatorService formValidatorService = new FormValidatorService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    public void singIn(final String username, String email, String senha) {
        showLoader(Constantes.WAIT_LOADER);

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideLoader();
                        if (!task.isSuccessful()) {
                            if (task.getException().getMessage().indexOf("network error") != -1) {
                                showError(Constantes.INTERNET_ERROR);
                            } else {
                                showError("Falha na authenticaçao");
                            }
                        } else {
                            FirebaseUser user = task.getResult().getUser();
                            DatabaseReference dbReference = database.getReference(user.getUid());
                            dbReference.setValue(username);
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });

    }

    public void openLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        if (this.isNameValid() && this.isEmailValid() && this.arePasswordsValid()) {
            String username = txtName.getText().toString();
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();

            singIn(username, email, password);
        }
    }

    private boolean isNameValid() {
        formValidatorService.cleanFieldErrors(txtName);

        return formValidatorService.isntFieldEmpty(txtName);
    }

    private boolean isEmailValid() {
        formValidatorService.cleanFieldErrors(txtEmail);

        return formValidatorService.isntFieldEmpty(txtEmail)
                && formValidatorService.emailPatternsMatches(txtEmail);
    }

    private boolean arePasswordsValid() {
        formValidatorService.cleanFieldErrors(txtPassword);
        formValidatorService.cleanFieldErrors(txtConfirm);

        return formValidatorService.isntFieldEmpty(txtPassword)
                && formValidatorService.isInputBiggerThanMinLength(txtPassword)
                && formValidatorService.isntFieldEmpty(txtConfirm)
                && formValidatorService.arePassAndConfirmationEquals(txtPassword, txtConfirm);
    }
}
