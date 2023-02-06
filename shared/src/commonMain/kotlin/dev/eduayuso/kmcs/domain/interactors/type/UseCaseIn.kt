package dev.eduayuso.kmcs.domain.interactors.type

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

abstract class UseCaseIn<IN> {

    operator fun invoke(param: IN): Flow<Resource<Unit>> = flow {
        emit(
            try {
                Resource.Success(block(param))
            } catch (ex: Exception) {
                Resource.Error(exception = ex)
            }
        )
    }

    protected abstract val block: suspend (param: IN) -> Unit
}