package br.com.cwi.moses.services;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import br.com.cwi.moses.utils.Constants;

public class TokenService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference(Constants.USER_CHILD_FRD);
        dbReference.child(user.getUid()).child(Constants.TOKEN_CHILD_FRD).setValue(refreshedToken);
        Log.d("onRefreshToken", "Refreshed Token: " + refreshedToken);
    }
}
