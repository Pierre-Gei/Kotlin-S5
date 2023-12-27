package org.isen.newsapp.controller

import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.view.INewsView

class NewsController (val model: INewsModel){
    private val views = mutableListOf<INewsView>()
    fun getnewsall(querry_args: String, API_KEY: String) {
        this.model.fetchNews(querry_args , API_KEY, INewsModel.NEWS_ALL)
    }
    fun getnewsheadlines(querry_args: String, API_KEY: String) {
        this.model.fetchNews(querry_args , API_KEY, INewsModel.NEWS_HEADLINES)
    }

    fun registerviewtoall(v: INewsView) {
        if(!this.views.contains(v)) this.views.add(v)
        this.model.register(INewsModel.NEWS_ALL, v)
    }
    fun registerviewtoheadlines(v: INewsView) {
        if(!this.views.contains(v)) this.views.add(v)
        this.model.register(INewsModel.NEWS_HEADLINES, v)
    }
    fun displayview() {
        views.forEach {
            it.display()
        }
    }
    fun closeview() {
        views.forEach {
            it.close()
        }
    }
}