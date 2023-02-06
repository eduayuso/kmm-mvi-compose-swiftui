package dev.eduayuso.kmcs.domain.interactors.impl

import dev.eduayuso.kmcs.domain.entities.PostEntity
import dev.eduayuso.kmcs.domain.interactors.GetPostListUseCase
import dev.eduayuso.kmcs.domain.repository.IPostsRepository

class GetPostListInteractor(

    private val repository: IPostsRepository

): GetPostListUseCase() {

    override val block: suspend () -> List<PostEntity>
        get() = {
            repository.getAll()
        }
}