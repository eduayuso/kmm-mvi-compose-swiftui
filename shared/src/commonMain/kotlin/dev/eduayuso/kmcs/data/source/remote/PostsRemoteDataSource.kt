package dev.eduayuso.kmcs.data.source.remote

import dev.eduayuso.kmcs.AppConstants
import dev.eduayuso.kmcs.data.model.CommentListResponse
import dev.eduayuso.kmcs.data.model.PostListResponse
import dev.eduayuso.kmcs.data.model.PostResponse

class PostsRemoteDataSource(

    private val apiClient: ApiClient,
    private val resourceName: String = AppConstants.Apis.DummyApi.posts
) {

    suspend fun getAll() = apiClient.get<PostListResponse>(resourceName)

    suspend fun getById(id: String) = apiClient.get<PostResponse>("$resourceName/$id")

    suspend fun getComments(id: String) =

        apiClient.get<CommentListResponse>("$resourceName/$id/comment")
}