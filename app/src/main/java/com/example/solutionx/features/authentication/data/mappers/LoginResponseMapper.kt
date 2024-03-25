package com.example.solutionx.features.authentication.data.mappers

import com.example.solutionx.common.data.mapper.Mapper
import com.example.solutionx.features.authentication.data.model.dto.LoginResponseDto
import com.example.solutionx.features.authentication.data.model.dto.UserDto
import com.example.solutionx.features.authentication.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.authentication.domain.models.LoginResponse

object LoginResponseMapper : Mapper<LoginResponseDto, LoginResponse, LoginResponseEntity>() {
    override fun dtoToDomain(model: LoginResponseDto): LoginResponse {
        return LoginResponse(
            accessToken = model.token.orEmpty(),
            user = UserMapper.dtoToDomain(model.userDto ?: UserDto())
        )
    }

    override fun domainToEntity(model: LoginResponse): LoginResponseEntity {
        return LoginResponseEntity(
            accessToken = model.accessToken,
            user = UserMapper.domainToEntity(model.user)
        )
    }
}