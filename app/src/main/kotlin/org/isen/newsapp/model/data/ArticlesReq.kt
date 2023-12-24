package org.isen.newsapp.model.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class ArticlesReq(val status: String, val totalResults: Int, val articles: List<Article>) {
    class Deserializer : ResponseDeserializable<ArticlesReq> {
        override fun deserialize(content: String): ArticlesReq? = Gson().fromJson(content, ArticlesReq::class.java)
    }
}

data class Articles(val articles: List<Article>)

data class Article(
    val source: SourceArt,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class SourceArt(
    val id: String,
    val name: String
)