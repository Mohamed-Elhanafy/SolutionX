package com.example.solutionx.features.login.data.repository.local

import am.leon.utilities.android.helpers.logging.LoggerFactory
import android.util.Base64
import com.example.solutionx.android.helpers.security.KeystoreUtils
import com.example.solutionx.features.login.data.model.entity.LoginResponseEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.example.solutionx.features.login.domain.repository.local.ILocalDataSource
import com.google.gson.Gson

internal class LocalDataSource(
    private val keyValueStorage: KeyValueStorage,
    private val gson: Gson
) : ILocalDataSource {



    override suspend fun saveLogin(loginResponse: LoginResponseEntity) {
        val userJson = gson.toJson(loginResponse.user)
        val (userIv, encryptedUser) = KeystoreUtils.encrypt(userJson)
        val encryptedUserString = Base64.encodeToString(encryptedUser, Base64.DEFAULT)

        // Save the encrypted user string and its IV in the datastore
        keyValueStorage.save(USER_ENCRYPTED, encryptedUserString)
        keyValueStorage.save(USER_IV, Base64.encodeToString(userIv, Base64.DEFAULT))

        val (accessTokenIv, encryptedAccessToken) = KeystoreUtils.encrypt(loginResponse.accessToken)
        val encryptedAccessTokenString = Base64.encodeToString(encryptedAccessToken, Base64.DEFAULT)

        // Save the encrypted access token string and its IV in the datastore
        keyValueStorage.save(ACCESS_TOKEN_ENCRYPT, encryptedAccessTokenString)
        keyValueStorage.save(ACCESS_TOKEN_IV, Base64.encodeToString(accessTokenIv, Base64.DEFAULT))

        keyValueStorage.save(IS_USER_LOGGED_IN, true.toString())

        logger.info("Login saved")
    }

    override suspend fun getAccessToken(): String {
        val encryptedAccessTokenString = keyValueStorage.get<String, String>(ACCESS_TOKEN_ENCRYPT)
        val accessTokenIvString = keyValueStorage.get<String, String>(ACCESS_TOKEN_IV)

        val encryptedAccessToken = Base64.decode(encryptedAccessTokenString, Base64.DEFAULT)
        val accessTokenIv = Base64.decode(accessTokenIvString, Base64.DEFAULT)

        return KeystoreUtils.decrypt(accessTokenIv, encryptedAccessToken)
    }

    override suspend fun getUser(): UserEntity {
        val encryptedUserString = keyValueStorage.get<String, String>(USER_ENCRYPTED)
        val userIvString = keyValueStorage.get<String, String>(USER_IV)

        val encryptedUser = Base64.decode(encryptedUserString, Base64.DEFAULT)
        val userIv = Base64.decode(userIvString, Base64.DEFAULT)

        val userJson = KeystoreUtils.decrypt(userIv, encryptedUser)
        return gson.fromJson(userJson, UserEntity::class.java)
    }


    companion object {
        val logger = LoggerFactory.getLogger(LocalDataSource::class.java)
        const val ACCESS_TOKEN_IV = "access_token_iv"
        const val IS_USER_LOGGED_IN = "is_user_logged_in"
        const val USER_IV = "user_iv"
        const val USER_ENCRYPTED = "user_encrypted"
        const val ACCESS_TOKEN_ENCRYPT = "access_token_encrypted"
    }
}