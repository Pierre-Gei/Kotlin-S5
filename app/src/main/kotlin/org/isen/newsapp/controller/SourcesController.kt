package org.isen.newsapp.controller

import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.view.INewsView

class SourcesController (val model: INewsModel){
    private val views = mutableListOf<INewsView>()
    fun getSources(querry_args: String, API_KEY: String) {
        this.model.fetchSources(querry_args , API_KEY)
    }

    fun registerVeiwToSorces(v: INewsView) {
        if(!this.views.contains(v)) this.views.add(v)
        this.model.register(INewsModel.SOURCES, v)
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

    fun checkRequest(country: String, category: String, language: String): Boolean {
        return true
    }
}