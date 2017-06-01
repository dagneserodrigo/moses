package br.com.cwi.moses.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.cwi.moses.R;
import br.com.cwi.moses.services.AuthService;
import br.com.cwi.moses.utils.FormValidator;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity {
    private AuthService authService;

    @BindView(R.id.txtName) EditText txtName;
    @BindView(R.id.txtEmail) EditText txtEmail;
    @BindView(R.id.txtPassword) EditText txtPassword;
    @BindView(R.id.txtConfirmPassword) EditText txtConfirmPassword;
    @BindView(R.id.btnSignUp) Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents() {
        authService = new AuthService(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameIsValid() && emailIsValid() && credentialIsValid()) {
                    String username = txtName.getText().toString();
                    String email = txtEmail.getText().toString();
                    String password = txtPassword.getText().toString();

                    signUp(username, email, password);
                }
            }
        });
    }

    private void signUp(String name, String email, String password) {
        authService.signUp(name, email, password);
    }

    private boolean nameIsValid() {
        return !FormValidator.isEmpty(txtName);
    }

    private boolean emailIsValid() {
        return !FormValidator.isEmpty(txtEmail)
            && FormValidator.emailIsvalid(txtEmail);
    }

    private boolean credentialIsValid() {
        return !FormValidator.isEmpty(txtPassword)
            && FormValidator.passwordIsValid(txtPassword)
            && !FormValidator.isEmpty(txtConfirmPassword)
            && FormValidator.passwordConfirmationIsValid(txtPassword, txtConfirmPassword);
    }
}
