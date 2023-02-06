package dev.eduayuso.kmcs.domain.interactors.impl

import dev.eduayuso.kmcs.domain.entities.UserEntity
import dev.eduayuso.kmcs.domain.interactors.GetUserListUseCase
import dev.eduayuso.kmcs.domain.repository.IUsersRepository

class GetUserListInteractor(

    private val repository: IUsersRepository

): GetUserListUseCase() {

    override val block: suspend () -> List<UserEntity>
        get() = {
            repository.getAll()
        }
}