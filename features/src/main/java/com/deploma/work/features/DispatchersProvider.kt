package com.deploma.work.features

import kotlinx.coroutines.CoroutineDispatcher

class DispatchersProvider constructor(
    val io: CoroutineDispatcher,
    val main: CoroutineDispatcher,
    val default: CoroutineDispatcher,
    val single: CoroutineDispatcher
)
