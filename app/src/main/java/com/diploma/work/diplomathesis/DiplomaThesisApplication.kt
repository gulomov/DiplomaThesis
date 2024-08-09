package com.diploma.work.diplomathesis

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DiplomaThesisApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.d("Fetching FCM registration token failed\", task.exception")
                    return@addOnCompleteListener
                }
                val token = task.result
                Timber.d("FCM Registration token: $token")
            }
    }
}
