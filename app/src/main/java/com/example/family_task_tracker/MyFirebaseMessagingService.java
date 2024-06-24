package com.example.family_task_tracker;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import android.util.Log;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Обработка сообщений
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            // Твои данные здесь
            handleDataMessage(remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            // Показ уведомления пользователю
            showNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void handleDataMessage(Map<String, String> data) {
        // Обработка данных из сообщения
        // Обнови класс или сделай что-то полезное с данными.
    }

    private void showNotification(String messageBody) {
        // Показ уведомления пользователю
    }
}

