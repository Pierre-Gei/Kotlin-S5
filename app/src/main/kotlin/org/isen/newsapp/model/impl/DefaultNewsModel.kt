package org.isen.newsapp.model.impl

import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.fuel.httpGet
import kotlinx.coroutines.*
import org.apache.logging.log4j.kotlin.logger
import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.model.data.*
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates

class DefaultNewsModel : INewsModel {
    companion object logging

    private val pcs = PropertyChangeSupport(this)

    private var news: ArticlesReq? by Delegates.observable(null) {property, oldValue, newValue ->
        logger().info("news changed from $oldValue to $newValue")
        pcs.firePropertyChange(INewsModel.NEWS_HEADLINES, oldValue, newValue)
        pcs.firePropertyChange(INewsModel.NEWS_ALL, oldValue, newValue)
    }

    private var sources: SourceReq? by Delegates.observable(null) {property, oldValue, newValue ->
        logger().info("sources changed from $oldValue to $newValue")
        pcs.firePropertyChange(INewsModel.SOURCES, oldValue, newValue)
    }

    private var err: String? by Delegates.observable(null) {property, oldValue, newValue ->
        logger().info("err changed from $oldValue to $newValue")
        pcs.firePropertyChange(INewsModel.ERR, oldValue, newValue)
    }

    private suspend fun fetchEverything(querry_args: String, API_KEY: String): ArticlesResult {
        if (querry_args == "") {
            logger().error("querry_args is empty")
            return ArticlesResult(null, "querry_args is empty")
        }else if (API_KEY == "") {
            logger().error("API_KEY is empty")
            return ArticlesResult(null, "API_KEY is empty")
        }
        val (request, response, result) = "https://newsapi.org/v2/everything?$querry_args&apiKey=$API_KEY".httpGet().responseObject(ArticlesReq.Deserializer())
        logger().info("Status code: ${response.statusCode}")
        if(response.contentLength == 0.toLong()){
            return ArticlesResult(null, "Check internet connexion")
        } else if (response.statusCode != 200) {
            val (_, _, result1) = "https://newsapi.org/v2/everything?$querry_args&apiKey=$API_KEY".httpGet().responseObject(ErrRes.Deserializer())
            // Handle the case where deserialization fails
            result1.component2()?.let { deserializationError ->
                return ArticlesResult(null, "${deserializationError.localizedMessage}")
            }

            result1.component1()?.let { err ->
                return ArticlesResult(null, err.status + " : " + err.code + " " + err.message)
            }
        }
        else {
            result.let { (articles, err) ->
                return ArticlesResult(articles, "")
            }
        }
        return ArticlesResult(null, "error")
    }

    private suspend fun fetchHeadlines(querry_args: String, API_KEY: String) : ArticlesResult{
        if (querry_args == "") {
            logger().error("querry_args is empty")
            return ArticlesResult(null, "querry_args is empty")
        }else if (API_KEY == "") {
            logger().error("API_KEY is empty")
            return ArticlesResult(null, "API_KEY is empty")
        }
        val (request, response, result) = "https://newsapi.org/v2/top-headlines?$querry_args&apiKey=$API_KEY".httpGet().responseObject(ArticlesReq.Deserializer())
        logger().info("Status code: ${response.statusCode}")

        if(response.contentLength == 0.toLong()){
            return ArticlesResult(null, "Check internet connexion")
        } else if (response.statusCode != 200) {
            val(_, _, result1) = "https://newsapi.org/v2/top-headlines?$querry_args&apiKey=$API_KEY".httpGet().responseObject(ErrRes.Deserializer())
            // Handle the case where deserialization fails
            result1.component2()?.let { deserializationError ->
                return ArticlesResult(null, "${deserializationError.localizedMessage}")
            }

            result1.component1()?.let { err ->
                return ArticlesResult(null, err.status + " : " + err.code + " " + err.message)
            }
        }else{
            result.let { (articles, err) ->
                return ArticlesResult(articles, "")
            }
        }
        return ArticlesResult(null, "error")
    }

    public override fun fetchNews(querry_args: String, API_KEY: String, type: String): ArticlesResult {
        return runBlocking {
            if (type == INewsModel.NEWS_ALL) {
                fetchEverything(querry_args, API_KEY)
            } else if (type == INewsModel.NEWS_HEADLINES) {
                fetchHeadlines(querry_args, API_KEY)
            } else {
                logger().error("type is not valid")
                ArticlesResult(null, "type is not valid")
            }
        }
    }

    public override fun fetchSources(querry_args: String, API_KEY: String): SourcesResult {
        if (querry_args == "") {
            logger().error("querry_args is empty")
            return SourcesResult(null, "querry_args is empty")
        }else if (API_KEY == "") {
            logger().error("API_KEY is empty")
            return SourcesResult(null, "API_KEY is empty")
        }
        val (request, response, result) = "https://newsapi.org/v2/top-headlines/sources?$querry_args&apiKey=$API_KEY".httpGet().responseObject(SourceReq.Deserializer())
        logger().info("Status code: ${response.statusCode}")
        if(response.contentLength == 0.toLong()){
            return SourcesResult(null, "Check internet connexion")
        } else if (response.statusCode != 200) {
            val (_, _, result1) = "https://newsapi.org/v2/sources?$querry_args&apiKey=$API_KEY".httpGet()
                .responseObject(ErrRes.Deserializer())
            // Handle the case where deserialization fails
            result1.component2()?.let { deserializationError ->
                return SourcesResult(null, "${deserializationError.localizedMessage}")
            }

            result1.component1()?.let { err ->
                return SourcesResult(null, err.status + " : " + err.code + " " + err.message)
            }
        }
        else{
            result.let { (sources, err) ->
                return SourcesResult(sources, "")
            }
        }
        return SourcesResult(null, "error")
    }

    override fun register(datatype: String, listener: PropertyChangeListener) {
        logger().info("registering listener")
        pcs.addPropertyChangeListener(datatype, listener)
    }

    override fun unregister(listener: PropertyChangeListener) {
        logger().info("unregistering listener")
        pcs.removePropertyChangeListener(listener)
    }
}