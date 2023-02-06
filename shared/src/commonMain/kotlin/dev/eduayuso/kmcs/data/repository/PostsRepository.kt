package dev.eduayuso.kmcs.data.repository

import dev.eduayuso.kmcs.data.map
import dev.eduayuso.kmcs.data.source.remote.PostsRemoteDataSource
import dev.eduayuso.kmcs.domain.entities.CommentEntity
import dev.eduayuso.kmcs.domain.entities.PostEntity
import dev.eduayuso.kmcs.domain.repository.IPostsRepository

class PostsRepository(

    private val data: PostsRemoteDataSource

): IPostsRepository {

    override suspend fun getAll(): List<PostEntity> =

        data.getAll()?.map() ?: emptyList()

    override suspend fun getById(id: String) =

        data.getById(id)?.map()

    override suspend fun getComments(id: String) =

        data.getComments(id)?.map() ?: emptyList()

}