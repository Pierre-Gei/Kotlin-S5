package org.isen.newsapp.controller

import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.model.data.ArticlesResult
import org.isen.newsapp.view.INewsView

class NewsController (val model: INewsModel){
    private val views = mutableListOf<INewsView>()

    fun getnewsall(querry_args: String, API_KEY: String) : ArticlesResult {
        return this.model.fetchNews(querry_args, API_KEY, INewsModel.NEWS_ALL)
    }
    fun getnewsheadlines(querry_args: String, API_KEY: String): ArticlesResult {
        return this.model.fetchNews(querry_args , API_KEY, INewsModel.NEWS_HEADLINES)
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

    fun checkRequestEverything(keyword: String, from: String, to: String, sort: String, language: String): Boolean {
        if (keyword == "" && from == "" && to == "" && sort == "" && language == "") {
            return false
        }else if (keyword == "") {
            return false
        }else{
            return true
        }
    }
    fun checkRequestHeadlines(keyword: String,country: String, category: String, language:String): Boolean {
        if (keyword == "" && country == "" && category == "" && language == "") {
            return false
        }else{
            return true
        }
    }
    fun checkdateinput(date: String): Boolean{
        if(date == "yyyy-mm-dd") return true
        if (date == "") return true
        val regex = Regex(pattern = """\d{4}-\d{2}-\d{2}""")
        return regex.matches(date)
    }
}