package com.diploma.work.repository.generic

import com.diploma.work.repository.resource.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

inline fun <reified T : Any> fetchFromDatabase(
    path: String,
    database: FirebaseDatabase,
): Flow<Resource<T>> =
    callbackFlow {
        val reference = database.getReference(path).get()

        reference.addOnCompleteListener { task ->
            val response = if (task.isSuccessful) {
                val result = task.result.getValue<T>()
                Resource.Success(result)
            } else {
                task.exception?.cause?.let { Resource.Error(it) }
            }
            response?.let { trySend(it).isSuccess }
        }

        awaitClose {
            close()
        }
    }