package br.com.cwi.moses.services;

import android.util.Patterns;
import android.widget.EditText;

/**
 * Created by Murillo on 01/06/2017.
 */

public class FormValidatorService {

    private static final String CAMPO_OBRIGATORIO = "Este campo é obrigatório.";
    private static final String EMAIL_FORMATO_INVALIDO = "Este e-mail é inválido.";
    private static final String SENHA_MUITO_CURTA = "Sua senha deve ter, ao menos, 6 caracteres.";
    private static final String SENHAS_NAO_CONFEREM = "As senhas não conferem.";

    private static final int MIN_LENGTH_SENHA = 6;

    public void cleanFieldErrors(EditText editText) {
        editText.setError(null);
    }

    public boolean isntFieldEmpty(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            this.invalidaInput(editText, CAMPO_OBRIGATORIO);
            return false;
        }
        return true;
    }

    public boolean emailPatternsMatches(EditText editText) {
        String text = editText.getText().toString().trim();
        if (!this.emailPatternMatches(text)) {
            this.invalidaInput(editText, EMAIL_FORMATO_INVALIDO);
            return false;
        }
        return true;
    }

    public boolean isInputBiggerThanMinLength(EditText editText) {
        String text = editText.getText().toString();
        if (this.isntFieldEmpty(editText) && text.length() >= MIN_LENGTH_SENHA) {
            return true;
        }
        this.invalidaInput(editText, SENHA_MUITO_CURTA);
        return false;
    }

    public boolean arePassAndConfirmationEquals(EditText pass, EditText passConfirm) {
        if (!pass.getText().toString().equals(passConfirm.getText().toString())) {
            this.invalidaInput(pass, SENHAS_NAO_CONFEREM);
            this.invalidaInput(passConfirm, SENHAS_NAO_CONFEREM);
            return false;
        }
        return true;
    }

    private void invalidaInput(EditText editText, String errorMessage) {
        editText.setError(errorMessage);
        editText.requestFocus();
    }

    private boolean emailPatternMatches(String text) {
        return Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }

}
