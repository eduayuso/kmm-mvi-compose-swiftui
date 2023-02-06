package dev.eduayuso.kmcs.domain.interactors

import dev.eduayuso.kmcs.domain.entities.CommentEntity
import dev.eduayuso.kmcs.domain.entities.PostEntity
import dev.eduayuso.kmcs.domain.entities.UserEntity
import dev.eduayuso.kmcs.domain.interactors.type.UseCaseInOut
import dev.eduayuso.kmcs.domain.interactors.type.UseCaseOut

abstract class GetPostListUseCase: UseCaseOut<List<PostEntity>>()

abstract class GetPostDetailUseCase: UseCaseInOut<String, PostEntity>()

abstract class GetPostCommentsUseCase: UseCaseInOut<String, List<CommentEntity>>()

abstract class GetUserListUseCase: UseCaseOut<List<UserEntity>>()