package org.isen.newsapp.model

import java.beans.PropertyChangeListener

interface INewsModel {
    companion object {
        const val NEWS_ALL = "news_all"
        const val NEWS_HEADLINES = "news_headlines"
        const val SOURCES = "sources"
        const val MENU = "menu"
        //a checker
    }
    fun fetchNews(querry_args: String, API_KEY: String, type: String)
    fun fetchSources(querry_args: String, API_KEY: String)
    fun register(datatype: String, listener: PropertyChangeListener)
    fun unregister(listener: PropertyChangeListener)
}