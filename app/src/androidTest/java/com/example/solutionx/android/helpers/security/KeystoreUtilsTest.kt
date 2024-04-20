package com.example.solutionx.android.helpers.security



import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

/* test cases
* 1. testEncryptionAndDecryption
* 2. testEncryptWithEmptyString
* 3. testEncryptWithSpecialCharacters
* 4. testEncryptWithNumbers
* 5. testEncryptionWithWrongIv
* 6. testGetSecretKey
* 7. test encryption with large text
* 8. test encryption with different languages
*
* */

@RunWith(AndroidJUnit4::class)
class KeystoreUtilsAndroidTest {

    @Test
    fun encryptAndDecryptWithNormalText_shouldEncryptAndDecryptSuccessfully() {
        val originalText = "solutionx"

        val (iv, encrypted) = KeystoreUtils.encrypt(originalText)
        val decryptedText = KeystoreUtils.decrypt(iv, encrypted)

        assertEquals(originalText, decryptedText)
    }

    @Test
    fun encryptAndDecryptWithEmptyString_shouldEncryptAndDecryptSuccessfully() {
        val originalText = ""

        val (iv, encrypted) = KeystoreUtils.encrypt(originalText)
        val decryptedText = KeystoreUtils.decrypt(iv, encrypted)

        assertEquals(originalText, decryptedText)
    }

    @Test
    fun encryptAndDecryptWithSpecialCharacters_shouldEncryptAndDecryptSuccessfully() {
        val originalText = "solutionx@#"

        val (iv, encrypted) = KeystoreUtils.encrypt(originalText)
        val decryptedText = KeystoreUtils.decrypt(iv, encrypted)

        assertEquals(originalText, decryptedText)
    }

    @Test
    fun encryptAndDecryptWithNumbers_shouldEncryptAndDecryptSuccessfully() {
        val originalText = "123456"

        val (iv, encrypted) = KeystoreUtils.encrypt(originalText)
        val decryptedText = KeystoreUtils.decrypt(iv, encrypted)

        assertEquals(originalText, decryptedText)
    }

    @Test(expected = javax.crypto.AEADBadTagException::class)
    fun encryptAndDecryptWithWrongIV_shouldThrowAEADBadTagException() {
        val originalText = "solutionx"

        val (iv, encrypted) = KeystoreUtils.encrypt(originalText)
        val wrongIv = ByteArray(12)
        val decryptedText = KeystoreUtils.decrypt(wrongIv, encrypted)

    }

    @Test
    fun encryptAndDecryptWithLargeText_shouldEncryptAndDecryptSuccessfully() {
        val originalText = "solutionx".repeat(1000)

        val (iv, encrypted) = KeystoreUtils.encrypt(originalText)
        val decryptedText = KeystoreUtils.decrypt(iv, encrypted)

        assertEquals(originalText, decryptedText)
    }

    @Test
    fun encryptAndDecryptWithDifferentLanguages_shouldEncryptAndDecryptSuccessfully() {
        val originalText = "عربي"

        val (iv, encrypted) = KeystoreUtils.encrypt(originalText)
        val decryptedText = KeystoreUtils.decrypt(iv, encrypted)

        assertEquals(originalText, decryptedText)
    }

    @Test
    fun getSecretKey_shouldNotBeNull() {
        val secretKey = KeystoreUtils.getSecretKey()
        // Verify that the secret key is not null
        assertNotNull(secretKey)
    }


}