package org.isen.newsapp.model.impl

import com.github.kittinunf.fuel.httpGet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.logging.log4j.kotlin.logger
import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.model.data.ArticlesReq
import org.isen.newsapp.view.INewsView
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates

class DefaultNewsModel : INewsModel {
    companion object logging

    private val pcs = PropertyChangeSupport(this)

    private var news: ArticlesReq? by Delegates.observable(null) {property, oldValue, newValue ->
        logger().info("news changed from $oldValue to $newValue")
        TODO("implement notification to observer")
    }

    private suspend fun fetchEverything(querry_args: String, API_KEY: String) {
        if (querry_args == "") {
            logger().error("querry_args is empty")
            return
        }else if (API_KEY == "") {
            logger().error("API_KEY is empty")
            return
        }
        val (request, response, result) = "https://newsapi.org/v2/everything?$querry_args&apiKey=$API_KEY".httpGet().responseObject(ArticlesReq.Deserializer())
        logger().info("Status code: ${response.statusCode}")
        result.let { (articles, err) ->
            news = articles
        }
    }

    private suspend fun fetchHeadlines(querry_args: String, API_KEY: String) {
        if (querry_args == "") {
            logger().error("querry_args is empty")
            return
        }else if (API_KEY == "") {
            logger().error("API_KEY is empty")
            return
        }
        val (request, response, result) = "https://newsapi.org/v2/top-headlines?$querry_args&apiKey=$API_KEY".httpGet().responseObject(ArticlesReq.Deserializer())
        logger().info("Status code: ${response.statusCode}")
        result.let { (articles, err) ->
            news = articles
        }
    }

    public fun fetchNews(querry_args: String, API_KEY: String, type: String) {
        if (type == INewsModel.NEWS_ALL) {
            GlobalScope.launch {
                fetchEverything(querry_args, API_KEY)
            }
        }else if (type == INewsModel.NEWS_HEADLINES) {
            GlobalScope.launch {
                fetchHeadlines(querry_args, API_KEY)
            }
        }else{
            logger().error("type is not valid")
        }
    }
    override fun register(datatype: String, listener: INewsView) {
        TODO()
    }

    override fun unregister(listener: PropertyChangeListener) {
        TODO()
    }
}