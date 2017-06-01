package br.com.cwi.moses.utils;

import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Murillo on 01/06/2017.
 */

public class FormValidator {
    private static void clear(TextView widget) {
        widget.setError(null);
    }

    public static boolean isEmpty(TextView widget) {
        clear(widget);
        String text = widget.getText().toString().trim();

        if (text.isEmpty()) {
            setError(widget, Constants.CAMPO_OBRIGATORIO);
            return true;
        }

        return false;
    }

    public static boolean emailIsvalid(TextView widget) {
        clear(widget);
        String text = widget.getText().toString().trim();
        boolean valid = Patterns.EMAIL_ADDRESS.matcher(text).matches();

        if (!valid) {
            setError(widget, Constants.EMAIL_FORMATO_INVALIDO);
            return false;
        }

        return true;
    }

    public static boolean passwordIsValid(TextView widget) {
        clear(widget);
        String text = widget.getText().toString().trim();

        if (!isEmpty(widget) && text.length() < Constants.MIN_LENGTH_SENHA) {
            setError(widget, Constants.SENHA_MUITO_CURTA);
            return false;
        }

        return true;
    }

    public static boolean passwordConfirmationIsValid(TextView password, TextView passwordConfirmation) {
        if (!password.getText().toString().equals(passwordConfirmation.getText().toString())) {
            setError(password, Constants.SENHAS_NAO_CONFEREM);
            setError(passwordConfirmation, Constants.SENHAS_NAO_CONFEREM);
            return false;
        }

        return true;
    }

    private static void setError(TextView widget, String errorMessage) {
        widget.setError(errorMessage);
        widget.requestFocus();
    }
}
