package org.isen.newsapp.model

import java.beans.PropertyChangeListener

interface INewsModel {
    companion object {
        const val NEWS_ALL = "news_all"
        const val NEWS_HEADLINES = "news_headlines"
        const val SOURCES = "sources"
        //a checker
    }
    fun register(datatype: String, listener: PropertyChangeListener)
    fun unregister(listener: PropertyChangeListener)
}