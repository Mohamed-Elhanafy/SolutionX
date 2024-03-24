package com.example.solutionx.features.authentication.data.mappers

import com.example.solutionx.features.authentication.data.model.dto.UserDto
import com.example.solutionx.features.authentication.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.domain.models.User

object UserMapper {
    fun mapToDto(userEntity: UserEntity): UserDto {
        return UserDto(
            id = userEntity.userId,
            name = userEntity.username,
            gender = userEntity.gender
        )
    }


    fun mapToEntity(user: User): UserEntity {
        return UserEntity(
            userId = user.id,
            username = user.name,
            gender = user.gender
        )
    }

    fun mapToDomain(userDto: UserDto): User {
        return User(
            id = userDto.id,
            name = userDto.name,
            gender = userDto.gender
        )
    }
}