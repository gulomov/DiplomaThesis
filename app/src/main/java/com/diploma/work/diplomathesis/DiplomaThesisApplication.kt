package com.diploma.work.diplomathesis

import android.content.Context
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Application
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DiplomaThesisApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        firebase(context = this)
        createNotificationChanel(context = this)
    }
}

private fun firebase(context: Context) {
    FirebaseApp.initializeApp(context)
    Firebase.analytics
    FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
        if (!task.isSuccessful) {
            Timber.e("Fetching FCM registration token failed\", task.exception")
            return@addOnCompleteListener
        }
        val token = task.result
        Timber.d("FCM Registration token: $token")
    }
}

private fun createNotificationChanel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "thesis_chanel",
            "General Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "App default notifications"
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
