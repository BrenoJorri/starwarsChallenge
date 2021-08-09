package com.trivago.core.base

abstract class BaseRepository {

    protected suspend fun <T> networkBoundResource(
        saveCallResult: suspend (T) -> Unit = {},
        shouldFetch: (T?) -> Boolean = { true },
        makeApiCall: suspend () -> T, loadFromDb: (suspend () -> T)? = null
    ): T? {

        val dataFromDb = loadFromDb?.let { it() }

        return if (shouldFetch(dataFromDb)) {
            try {
                makeApiCall.invoke()?.let { result ->
                    saveCallResult(result)
                    result
                }
            } catch (t: Throwable) {
                throw t
            }
        } else {
            dataFromDb
        }
    }
}
