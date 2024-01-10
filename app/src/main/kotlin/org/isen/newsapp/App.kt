package org.isen.newsapp

import javafx.application.Application
import javafx.application.Application.launch
import javafx.stage.Stage
import org.isen.newsapp.controller.MenuController
import org.isen.newsapp.controller.NewsController
import org.isen.newsapp.controller.SourcesController
import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.model.impl.DefaultNewsModel
import org.isen.newsapp.view.impl.MenuView
import org.isen.newsapp.view.impl.WebView


    fun main() {
        val newsModel: INewsModel = DefaultNewsModel()
        val menuController = MenuController(newsModel)
        val sourcesController = SourcesController(newsModel)
        val newsController = NewsController(newsModel)
        val menuView = MenuView(menuController, sourcesController, newsController, "NewsApp")
        //WebView().display("https://www.google.com")
        menuController.displayview()
}

