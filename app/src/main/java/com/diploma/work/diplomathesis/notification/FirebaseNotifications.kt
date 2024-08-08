package com.diploma.work.diplomathesis.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class FirebaseNotifications : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // TODO: Will be implemented soon.
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let {
            // TODO: Will be implemented soon.
        }
    }
}