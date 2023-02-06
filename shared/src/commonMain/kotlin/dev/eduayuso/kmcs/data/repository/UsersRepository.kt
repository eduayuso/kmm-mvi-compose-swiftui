package dev.eduayuso.kmcs.data.repository

import dev.eduayuso.kmcs.data.map
import dev.eduayuso.kmcs.data.source.remote.UsersRemoteDataSource
import dev.eduayuso.kmcs.domain.entities.UserEntity
import dev.eduayuso.kmcs.domain.repository.IUsersRepository

class UsersRepository(

    private val data: UsersRemoteDataSource

): IUsersRepository {

    override suspend fun getAll(): List<UserEntity> =

        data.getAll()?.map() ?: emptyList()

    override suspend fun getById(id: Int) =

        data.getById(id)?.map()
}