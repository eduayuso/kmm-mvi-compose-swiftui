package dev.eduayuso.kmcs.domain.repository

import dev.eduayuso.kmcs.domain.entities.UserEntity

interface IUsersRepository {

    suspend fun getAll(): List<UserEntity>

    suspend fun getById(id: Int): UserEntity?
}