package com.diploma.work.testing

import com.diploma.work.repository.data.NewsInfo
import com.diploma.work.repository.repository.HomeRepository
import com.diploma.work.repository.resource.Resource
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class HomeRepositoryTest {
    @Mock
    private lateinit var database: FirebaseDatabase
    private lateinit var homeRepository: HomeRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        homeRepository = HomeRepository(database)
    }

    @Test
    fun `getNewsInfo returns correct data`() =
        runBlocking {
            flowOf(Resource.Success(NewsInfo())).apply {
                whenever(homeRepository.getNewsInfo()).thenReturn(this)
            }

            val results = mutableListOf<Resource<NewsInfo>>()
            homeRepository.getNewsInfo().collect { results.add(it) }

            assert(results.first() is Resource.Success)
        }
}
