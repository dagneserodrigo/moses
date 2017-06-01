package br.com.cwi.moses.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {

    protected FirebaseAuth mAuth;
    protected FirebaseAuth.AuthStateListener mAuthListener;
    protected FirebaseDatabase database;

    protected final String TAG_FIREBASE = "FIREBASE_AUTHENTICATION";

    private AlertDialog.Builder alert;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG_FIREBASE, "onAuthStateChanged:signed_in" + user.getUid());
                }else{
                    Log.d(TAG_FIREBASE, "onAuthStateChanged:signed_out");
                }
            }
        };

        database = FirebaseDatabase.getInstance();

        progress = new ProgressDialog(this);
        progress.setCancelable(false);

        alert = new AlertDialog.Builder(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    protected void showLoader(String text){
        progress.setMessage(text);
        progress.show();
    }

    protected void hideLoader(){
        progress.dismiss();
    }

    protected void showError(String text){
        alert.setMessage(text);
        alert.show();
    }

    protected void logout(){
        showLoader("Saindo...");
        mAuth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        hideLoader();
    }
}