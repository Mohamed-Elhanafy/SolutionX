package com.example.solutionx.android.helpers.security



import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KeystoreUtilsAndroidTest {

    @Test
    fun testEncryptionAndDecryption() {
        val originalText = "solutionx"

        val (iv, encrypted) = KeystoreUtils.encrypt(originalText)
        val decryptedText = KeystoreUtils.decrypt(iv, encrypted)

        assertEquals(originalText, decryptedText)
    }

    @Test
    fun testEncryptWithEmptyString() {
        val originalText = ""

        val (iv, encrypted) = KeystoreUtils.encrypt(originalText)
        val decryptedText = KeystoreUtils.decrypt(iv, encrypted)

        assertEquals(originalText, decryptedText)
    }

    @Test
    fun testGetSecretKey() {
        val secretKey = KeystoreUtils.getSecretKey()
        // Verify that the secret key is not null
        assertNotNull(secretKey)
    }

}