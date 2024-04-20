package com.example.solutionx.android.helpers.security

import javax.crypto.SecretKey

interface IKeystoreUtils {
    fun getSecretKey(): SecretKey
    fun encrypt(input: String): Pair<ByteArray, ByteArray>
    fun decrypt(iv: ByteArray, encrypted: ByteArray): String
}