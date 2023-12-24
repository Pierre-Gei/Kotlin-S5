package org.isen.newsapp.view

import org.isen.newsapp.model.INewsModel

class NewsController (val model: INewsModel){
    private val views = mutableListOf<INewsView>()
    fun registerview(datatype: String="", v: INewsView) {
        views.add(v)
        if (datatype.isEmpty()) {
            model.register(INewsModel.NEWS_ALL, v)
            model.register(INewsModel.NEWS_HEADLINES, v)
            model.register(INewsModel.SOURCES, v)
        } else {
            model.register(datatype,v)
        }
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