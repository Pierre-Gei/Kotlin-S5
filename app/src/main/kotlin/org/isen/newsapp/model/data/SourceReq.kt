package org.isen.newsapp.model.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class SourceReq(val status: String, val sources: List<Source>) {
    class Deserializer : ResponseDeserializable<SourceReq> {
        override fun deserialize(content: String): SourceReq? = Gson().fromJson(content, SourceReq::class.java)
    }
}

data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)