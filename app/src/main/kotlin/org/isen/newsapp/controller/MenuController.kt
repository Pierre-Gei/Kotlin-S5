package org.isen.newsapp.controller

import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.view.INewsView

class MenuController (val model: INewsModel){
    private val views = mutableListOf<INewsView>()
    fun registerViewToMenu(v: INewsView) {
        if(!this.views.contains(v)) this.views.add(v)
        this.model.register(INewsModel.MENU, v)
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