package dev.eduayuso.kmcs.domain.repository

import dev.eduayuso.kmcs.domain.entities.CommentEntity
import dev.eduayuso.kmcs.domain.entities.PostEntity

interface IPostsRepository {

    suspend fun getAll(): List<PostEntity>

    suspend fun getById(id: String): PostEntity?

    suspend fun getComments(id: String): List<CommentEntity>
}