package br.com.cwi.moses.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MessageService extends FirebaseMessagingService {
    private final String TAG = "onMessageReceived";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        Map<String, String> data = remoteMessage.getData();
        double desconto = Double.valueOf(data.get("desconto"));
        Log.d(TAG, "Desconto na locação: " + desconto);
    }
}
