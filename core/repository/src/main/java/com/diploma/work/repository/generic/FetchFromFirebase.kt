package com.diploma.work.repository.generic

import com.diploma.work.repository.resource.Resource
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

inline fun <reified T : Any> fetchFromFirebase(
    path: String,
    database: FirebaseDatabase,
): Flow<Resource<T>> = callbackFlow {
    val reference = database.getReference(path).get()

    reference.addOnCompleteListener { result ->
        val response = if (result.isSuccessful) {
            Resource.Success(result.result.getValue<T>())
        } else {
            result.exception?.cause?.let { Resource.Error(it) }
        }
        response?.let { trySend(it).isSuccess }

    }
    awaitClose {
        close()
    }
}