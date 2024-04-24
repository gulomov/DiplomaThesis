package com.diploma.work.repository.generic

import com.diploma.work.repository.resource.Resource
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import timber.log.Timber

inline fun <reified T : Any> fetchFromDatabase(
    path: String,
    database: FirebaseDatabase,
): Flow<T?> = callbackFlow {
    try {
        // Using Firebase asynchronous retrieval with await() from kotlinx-coroutines-play-services
        val dataSnapshot = database.getReference(path).get().await()
        if (dataSnapshot.exists()) {
            dataSnapshot.getValue<T>().also {
                Timber.d("Data fetched successfully: $it")
                trySend(it)
            }
        } else {
            Timber.e("No data found at path: $path")
            trySend(null) // Sending null if no data is found
        }
    } catch (e: Exception) {
        Timber.e("Error fetching data from Firebase: ${e.message}")
        close(e) // Close the flow with an exception
    }

    awaitClose {
        // Optional cleanup can be performed here if necessary
        Timber.d("Closing the fetchFromDatabase flow")
    }
}
