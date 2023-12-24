package org.isen.newsapp.model.data

import com.github.kittinunf.fuel.httpGet
import kotlin.test.Test


class ArticlesReqJUnitTest {
    @Test
    fun testReqStatus() {
       val (request, response, result) ="https://newsapi.org/v2/top-headlines?country=fr&apiKey=d085fa05e7ca462c8bb0e770ec30f41e".httpGet().responseObject(ArticlesReq.Deserializer())
        val (articles, err) = result
        assert(err == null)
        if (articles != null) {
            assert(articles.status == "ok")
        }else{
            assert(false)
        }
    }
    @Test
    fun testReqTotalResults() {
        val (request, response, result) ="https://newsapi.org/v2/top-headlines?country=fr&apiKey=d085fa05e7ca462c8bb0e770ec30f41e".httpGet().responseObject(ArticlesReq.Deserializer())
        val (articles, err) = result
        assert(err == null)
        if (articles != null) {
            assert(articles.totalResults > 0)
        }else{
            assert(false)
        }
    }
    @Test
    fun testReqArticlesSource() {
        val (request, response, result) ="https://newsapi.org/v2/top-headlines?country=fr&apiKey=d085fa05e7ca462c8bb0e770ec30f41e".httpGet().responseObject(ArticlesReq.Deserializer())
        val (articles, err) = result
        assert(err == null)
        if (articles != null) {
            assert(articles.articles[0].source.id == "google-news")
        }else{
            assert(false)
        }
    }

}