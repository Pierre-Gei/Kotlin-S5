/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package org.isen.newsapp

import org.isen.newsapp.controller.MenuController
import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.model.impl.DefaultNewsModel
import org.isen.newsapp.view.impl.MenuView

fun main() {
    val newsModel : INewsModel = DefaultNewsModel()
    val menuController = MenuController(newsModel)
    val MenuView = MenuView(menuController, "News App")

    menuController.displayview()
    //newsController1.displayview()
    //newsController2.displayview()
    //newsController1.getnewsall("q=Airbus", "d085fa05e7ca462c8bb0e770ec30f41e")
    //newsController2.getnewsheadlines("country=fr", "d085fa05e7ca462c8bb0e770ec30f41e").getSources("country=fr", "d085fa05e7ca462c8bb0e770ec30f41e")
}