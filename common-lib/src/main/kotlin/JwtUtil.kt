import java.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object JwtUtil {
    fun createToken(subject: String, secret: String): String {
        val header = Base64.getUrlEncoder().withoutPadding()
            .encodeToString("{""alg"":""HS256"",""typ"":""JWT""}".toByteArray())
        val payload = Base64.getUrlEncoder().withoutPadding()
            .encodeToString("{""sub"":""$subject""}".toByteArray())
        val signature = hmacSha256("$header.$payload", secret)
        val sigEncoded = Base64.getUrlEncoder().withoutPadding().encodeToString(signature)
        return "$header.$payload.$sigEncoded"
    }

    fun verifyToken(token: String, secret: String): Boolean {
        val parts = token.split('.')
        if (parts.size != 3) return false
        val expected = Base64.getUrlEncoder().withoutPadding()
            .encodeToString(hmacSha256("${parts[0]}.${parts[1]}", secret))
        return expected == parts[2]
    }

    private fun hmacSha256(data: String, secret: String): ByteArray {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(secret.toByteArray(), "HmacSHA256"))
        return mac.doFinal(data.toByteArray())
    }
}
