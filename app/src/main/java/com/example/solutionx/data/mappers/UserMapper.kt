package com.example.solutionx.data.mappers

import com.example.solutionx.data.model.dto.UserDto
import com.example.solutionx.data.model.entity.UserEntity
import com.example.solutionx.domain.models.User

class UserMapper {
    fun mapToDto(userEntity: UserEntity): UserDto {
        return UserDto(
            id = userEntity.userId,
            name = userEntity.username,
            gender = userEntity.gender
        )
    }

    fun mapToEntity(userDto: UserDto): UserEntity {
        return UserEntity(
            userId = userDto.id,
            username = userDto.name,
            gender = userDto.gender
        )
    }

    //map to domain
    fun mapToDomain(userDto: UserDto): User {
        return User(
            id = userDto.id,
            name = userDto.name,
            gender = userDto.gender
        )
    }
}