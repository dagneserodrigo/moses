package br.com.cwi.moses.utils;

public class Constants {
    //LOGS
    public static final String LOG_FIREBASE = "FIREBASE_AUTHENTICATION";

    //ENDPOINTS

    //MESSAGE
    public static final String INTERNET_ERROR = "Verifique a internet";
    public static final String AUTH_FAILED = "Não foi possível autenticar o usuário";
    public static final String SIGN_UP_FAILED = "Não foi possível cadastrar o usuário";
    public static final String CAMPO_OBRIGATORIO = "Este campo é obrigatório.";
    public static final String EMAIL_FORMATO_INVALIDO = "Este e-mail é inválido.";
    public static final String SENHA_MUITO_CURTA = "Sua senha deve ter, ao menos, 6 caracteres.";
    public static final String SENHAS_NAO_CONFEREM = "As senhas não conferem.";
    public static final int MIN_LENGTH_SENHA = 6;

    //LOADERS
    public static final String WAIT_LOADER = "Aguarde...";

    //Firebase Database
    public static final String USER_CHILD_FRD = "Users";
    public static final String TICKET_CHILD_FRD = "Tickets";
    public static final String USERNAME_CHILD_FRD = "Name";
}
