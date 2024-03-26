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
        BasicLogcatConfigurator.configureDefaultContext()

        System.setProperty("log.name", "Log")
        System.setProperty("log.path", filesDir.absolutePath)

        // Initialize Logback with the configuration
        val lc = LoggerFactory.getILoggerFactory() as LoggerContext
        try {
            StatusPrinter.print(lc)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
