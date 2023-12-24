package org.isen.newsapp.model.data

import com.github.kittinunf.fuel.httpGet
import org.junit.jupiter.api.Test

class SourceReqJUnitTest {
    @Test
    fun testSourceReq() {
        val (request, response, result) ="https://newsapi.org/v2/top-headlines/sources?apiKey=d085fa05e7ca462c8bb0e770ec30f41e".httpGet().responseObject(SourceReq.Deserializer())
        val (sources, err) = result
        assert(err == null)
        if (sources != null) {
            assert(sources.status == "ok")
        }
    }
    @Test
    fun testSourceReqSources() {
        val (request, response, result) ="https://newsapi.org/v2/top-headlines/sources?apiKey=d085fa05e7ca462c8bb0e770ec30f41e".httpGet().responseObject(SourceReq.Deserializer())
        val (sources, err) = result
        assert(err == null)
        if (sources != null) {
            assert(sources.sources[0].id == "abc-news")
        }
    }
    @Test
    fun testSourceReqCountry() {
        val (request, response, result) ="https://newsapi.org/v2/top-headlines/sources?country=fr&apiKey=d085fa05e7ca462c8bb0e770ec30f41e".httpGet().responseObject(SourceReq.Deserializer())
        val (sources, err) = result
        assert(err == null)
        if (sources != null) {
            assert(sources.sources[0].country == "fr")
        }
    }
}