package org.isen.newsapp.model.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class ErrRes(val status: String, val code: String, val message: String) {
    // Deserializer
    class Deserializer : ResponseDeserializable<ErrRes> {
        override fun deserialize(content: String): ErrRes {
            return try {
                Gson().fromJson(content, ErrRes::class.java)
            } catch (e: Exception) {
                // Instead of returning null, throw an exception
                throw DeserializationException("Error deserializing ErrRes: $content", e)
            }
        }
    }
}

// Custom exception for deserialization failures
class DeserializationException(message: String, cause: Throwable) : Exception(message, cause)

data class ErrResult(val err: ErrRes?, val err2: String?)