package com.deploma.work.diplomathesis

import android.app.Application
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.android.BasicLogcatConfigurator
import ch.qos.logback.core.util.StatusPrinter
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import org.slf4j.LoggerFactory

@HiltAndroidApp
class DiplomaThesisApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
