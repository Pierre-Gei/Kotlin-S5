package org.isen.newsapp.view

import org.isen.newsapp.model.data.Article
import org.isen.newsapp.model.data.ArticlesResult
import org.isen.newsapp.model.data.Source
import org.isen.newsapp.model.data.SourcesResult
import java.beans.PropertyChangeListener

interface INewsView: PropertyChangeListener {
    fun displayNews(articles: ArticlesResult)
    fun displaySources(sources: SourcesResult)
    fun displayError(error: String)
    fun display()
    fun close()
}