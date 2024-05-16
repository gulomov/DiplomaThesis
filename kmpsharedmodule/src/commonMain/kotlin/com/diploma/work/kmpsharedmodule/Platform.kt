package com.diploma.work.kmpsharedmodule

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform