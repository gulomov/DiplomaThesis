package com.diploma.work.analytics

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAnalyticsManager @Inject constructor(
    @ApplicationContext context: Context
) {
    @SuppressLint("MissingPermission")
    private val firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    fun logEvent(eventName: String, params: Map<String, Any>? = null) {
        val bundle = Bundle().apply {
            params?.forEach { (key, value) ->
                when (value) {
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                    is Double -> putDouble(key, value)
                    is Float -> putFloat(key, value)
                    is Boolean -> putBoolean(key, value)
                    else -> putString(key, value.toString())
                }
            }
        }
        firebaseAnalytics.logEvent(eventName, bundle)
    }

    fun logScreenView(screenName: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        }
    }

    fun logSavedProduct(productId: Int) {
        logEvent(
            eventName = "product_saved",
            params = (mapOf("product_name" to productId))
        )

    }

    fun logSavedProductFromGalley(
        productId: Int,
        productTitle: String,
        productBrand: String,
    ) {
        logEvent(
            eventName = "product_booked_from_gallery",
            params = (mapOf(
                "product_name" to productTitle,
                "product_id" to productId,
                "product_brand" to productBrand,
            ))
        )
    }

    fun logButtonClicked(buttonName: String) {
        logEvent(eventName = "button_clicked", mapOf("button_name" to buttonName))
    }

    companion object {
        const val FAVORITE_SCREEN = "favorite_screen"
        const val PRODUCT_DETAIL_SCREEN = "product_detail_screen"
    }
}