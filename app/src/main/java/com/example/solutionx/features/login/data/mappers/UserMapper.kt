package com.example.solutionx.features.login.data.mappers

import com.example.solutionx.common.data.mapper.Mapper
import com.example.solutionx.features.login.data.model.dto.UserDto
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.domain.models.User

object UserMapper : Mapper<UserDto, User, UserEntity>() {
    override fun dtoToDomain(model: UserDto): User {
        return User(
            id = model.id ?: -1,
            name = model.firstname + " " + model.lastname.orEmpty(),
            email = model.email.orEmpty()
        )
    }

    override fun domainToEntity(model: User): UserEntity {
        return UserEntity(
            userId = model.id,
            name = model.name,
            email = model.email
        )
    }

    override fun entityToDomain(model: UserEntity): User {
        return User(
            id = model.userId ,
            name = model.name,
            email = model.email
        )
    }
}