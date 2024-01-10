package org.isen.newsapp.model

import org.isen.newsapp.model.data.ArticlesResult
import org.isen.newsapp.model.data.SourcesResult
import java.beans.PropertyChangeListener

interface INewsModel {
    companion object {
        const val NEWS_ALL = "news_all"
        const val NEWS_HEADLINES = "news_headlines"
        const val SOURCES = "sources"
        const val MENU = "menu"
        const val ERR = "err"
        const val WEB = "web"
        //a checker
    }
    fun fetchNews(querry_args: String, API_KEY: String, type: String) : ArticlesResult
    fun fetchSources(querry_args: String, API_KEY: String) : SourcesResult
    fun register(datatype: String, listener: PropertyChangeListener)
    fun unregister(listener: PropertyChangeListener)
}