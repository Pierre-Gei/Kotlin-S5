package org.isen.newsapp.model.impl

import com.github.kittinunf.fuel.httpGet
import kotlinx.coroutines.*
import org.apache.logging.log4j.kotlin.logger
import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.model.data.ArticlesReq
import org.isen.newsapp.model.data.ArticlesResult
import org.isen.newsapp.model.data.SourceReq
import org.isen.newsapp.model.data.SourcesResult
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

    private suspend fun fetchEverything(querry_args: String, API_KEY: String): ArticlesResult {
        if (querry_args == "") {
            logger().error("querry_args is empty")
            return ArticlesResult(null, IllegalArgumentException("querry_args is empty"))
        }else if (API_KEY == "") {
            logger().error("API_KEY is empty")
            return ArticlesResult(null, IllegalArgumentException("API_KEY is empty"))
        }
        val (request, response, result) = "https://newsapi.org/v2/everything?$querry_args&apiKey=$API_KEY".httpGet().responseObject(ArticlesReq.Deserializer())
        logger().info("Status code: ${response.statusCode}")
        result.let { (articles, err) ->
            return ArticlesResult(articles, err)
        }
    }

    private suspend fun fetchHeadlines(querry_args: String, API_KEY: String) : ArticlesResult{
        if (querry_args == "") {
            logger().error("querry_args is empty")
            return ArticlesResult(null, IllegalArgumentException("querry_args is empty"))
        }else if (API_KEY == "") {
            logger().error("API_KEY is empty")
            return ArticlesResult(null, IllegalArgumentException("API_KEY is empty"))
        }
        val (request, response, result) = "https://newsapi.org/v2/top-headlines?$querry_args&apiKey=$API_KEY".httpGet().responseObject(ArticlesReq.Deserializer())
        logger().info("Status code: ${response.statusCode}")
        result.let { (articles, err) ->
            return ArticlesResult(articles, err)
        }
    }

    public override fun fetchNews(querry_args: String, API_KEY: String, type: String): ArticlesResult {
        return runBlocking {
            if (type == INewsModel.NEWS_ALL) {
                fetchEverything(querry_args, API_KEY)
            } else if (type == INewsModel.NEWS_HEADLINES) {
                fetchHeadlines(querry_args, API_KEY)
            } else {
                logger().error("type is not valid")
                ArticlesResult(null, IllegalArgumentException("type is not valid"))
            }
        }
    }

    public override fun fetchSources(querry_args: String, API_KEY: String): SourcesResult {
        if (querry_args == "") {
            logger().error("querry_args is empty")
            return SourcesResult(null, IllegalArgumentException("querry_args is empty"))
        }else if (API_KEY == "") {
            logger().error("API_KEY is empty")
            return SourcesResult(null, IllegalArgumentException("API_KEY is empty"))
        }
        val (request, response, result) = "https://newsapi.org/v2/top-headlines/sources?$querry_args&apiKey=$API_KEY".httpGet().responseObject(SourceReq.Deserializer())
        logger().info("Status code: ${response.statusCode}")
        result.let { (sources, err) ->
            this.sources = sources
            return SourcesResult(sources, err)
        }
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