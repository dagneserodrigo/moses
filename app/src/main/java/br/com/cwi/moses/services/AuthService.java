package br.com.cwi.moses.services;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.cwi.moses.activities.BaseActivity;
import br.com.cwi.moses.activities.MainActivity;
import br.com.cwi.moses.utils.Constants;

/**
 * Created by dagneserodrigo on 5/31/17.
 */

public class AuthService {
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private BaseActivity activity;

    public AuthService(BaseActivity baseActivity) {
        this.auth = FirebaseAuth.getInstance();
        setAuthListener();
        activity = baseActivity;
    }

    private void setAuthListener() {
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String logMessage = user != null ? "onAuthStateChanged:signed_in" + user.getUid() : "onAuthStateChanged:signed_out";
                Log.d(Constants.LOG_FIREBASE, logMessage);
            }
        };
    }

    public void signIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d(Constants.LOG_FIREBASE, "signInWithEmail:onComplete:" + task.isSuccessful());

                    if (!task.isSuccessful()) {
                        Log.w(Constants.LOG_FIREBASE, "signInWithEmail:failed", task.getException());
                        activity.showError(task.getException().getMessage());
                    } else {
                        Log.w(Constants.LOG_FIREBASE, "signInWithEmail:success", task.getException());
                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                    }
                }
            });
    }

    public void signUp(String name, String email, String password) {
        final String username = name;
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d(Constants.LOG_FIREBASE, "createUserWithEmail:onComplete:" + task.isSuccessful());

                    if (!task.isSuccessful()) {
                        Log.w(Constants.LOG_FIREBASE, "createUserWithEmail:failed", task.getException());
                        activity.showError(task.getException().getMessage());
                    } else {
                        Log.w(Constants.LOG_FIREBASE, "createUserWithEmail:success", task.getException());
                        FirebaseUser user = task.getResult().getUser();

                        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference(Constants.USER_CHILD_FRD);
                        dbReference.child(user.getUid()).child(Constants.USERNAME_CHILD_FRD).setValue(username);

                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                    }
                }
            });
    }

    public void signOut(){
        auth.signOut();
    }

    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }
}
