package com.diploma.work.composables

import kotlinx.coroutines.CoroutineDispatcher

class DispatchersProvider constructor(
    val io: CoroutineDispatcher,
    val main: CoroutineDispatcher,
    val default: CoroutineDispatcher,
    val single: CoroutineDispatcher
)
