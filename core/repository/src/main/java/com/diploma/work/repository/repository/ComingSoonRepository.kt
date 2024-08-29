package com.diploma.work.repository.repository

import com.diploma.work.database.dao.ComingSoonDao
import com.diploma.work.database.entity.ComingSoonProductEntity
import com.diploma.work.repository.data.ComingSoonProductList
import com.diploma.work.repository.generic.fetchFromDatabase
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ComingSoonRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val comingSoonDao: ComingSoonDao
) {
    suspend fun fetchAndSaveComingSoonProducts() {
        fetchFromDatabase<ComingSoonProductList>(
            "home/comingSoon",
            firebaseDatabase
        ).flowOn(Dispatchers.IO).collect { data ->
            data?.comingSoonProductList?.map {
                comingSoonDao.insert(
                    ComingSoonProductEntity(
                        id = it.id ?: 0,
                        image = it.image.toString(),
                        startingDate = it.startingDate.toString(),
                        brand = it.brand.toString()
                    )
                )
            }
        }
    }
}