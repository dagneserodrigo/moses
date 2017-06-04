package br.com.cwi.moses.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MessageService extends FirebaseMessagingService {
    private final String TAG = "onMessageReceived";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            Log.d(TAG, "From: " + remoteMessage.getFrom());
            Log.d(TAG, "Notification Message Title: " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
            Map<String, String> data = remoteMessage.getData();
            String idTicket = data.get("ticketId");
        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }
}
