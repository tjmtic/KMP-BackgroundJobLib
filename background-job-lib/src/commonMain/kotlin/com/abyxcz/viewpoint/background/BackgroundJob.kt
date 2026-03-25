package com.abyxcz.viewpoint.background

interface BackgroundJob {
    val id: String
    suspend fun execute(): Result

    sealed class Result {
        object Success : Result()
        object Failure : Result()
        object Retry : Result()
    }
}
