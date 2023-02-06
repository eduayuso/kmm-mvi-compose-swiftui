package dev.eduayuso.kmcs.domain.interactors.impl

import dev.eduayuso.kmcs.domain.entities.PostEntity
import dev.eduayuso.kmcs.domain.interactors.GetPostDetailUseCase
import dev.eduayuso.kmcs.domain.repository.IPostsRepository

class GetPostDetailInteractor(

    private val repository: IPostsRepository

): GetPostDetailUseCase() {

    override val block: suspend (param: String) -> PostEntity
        get() = {
            repository.getById(it)!!
        }
}