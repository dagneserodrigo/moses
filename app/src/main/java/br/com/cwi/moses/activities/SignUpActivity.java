package br.com.cwi.moses.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.cwi.moses.R;
import br.com.cwi.moses.services.AuthService;
import br.com.cwi.moses.services.FormValidatorService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity {
    private AuthService authService;
    private FormValidatorService formValidatorService;

    @BindView(R.id.txtName) EditText txtName;
    @BindView(R.id.txtEmail) EditText txtEmail;
    @BindView(R.id.txtPassword) EditText txtPassword;
    @BindView(R.id.btnSignUp) Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents() {
        formValidatorService = new FormValidatorService();
        authService = new AuthService(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNameValid() && isEmailValid() && arePasswordsValid()) {
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
//        formValidatorService.cleanFieldErrors(txtConfirm);

        return formValidatorService.isntFieldEmpty(txtPassword)
                && formValidatorService.isInputBiggerThanMinLength(txtPassword);
//                && formValidatorService.isntFieldEmpty(txtConfirm)
//                && formValidatorService.arePassAndConfirmationEquals(txtPassword, txtConfirm);
    }
}
