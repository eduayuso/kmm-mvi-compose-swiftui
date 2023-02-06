package dev.eduayuso.kmcs.data.source.remote

import dev.eduayuso.kmcs.AppConstants
import dev.eduayuso.kmcs.data.model.UserListResponse
import dev.eduayuso.kmcs.data.model.UserResponse

class UsersRemoteDataSource(

    private val apiClient: ApiClient,
    private val resourceName: String = AppConstants.Apis.DummyApi.users
) {

    suspend fun getAll() = apiClient.get<UserListResponse>(resourceName)

    suspend fun getById(id: Int) = apiClient.get<UserResponse>("$resourceName/$id")
}