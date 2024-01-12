package org.isen.newsapp.model.view.impl

import javafx.stage.Stage
import org.isen.newsapp.controller.MenuController
import org.isen.newsapp.controller.NewsController
import org.isen.newsapp.controller.SourcesController
import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.model.impl.DefaultNewsModel
import org.isen.newsapp.view.impl.MenuView
import org.junit.jupiter.api.BeforeEach
import java.awt.event.ActionEvent
import javax.swing.JComboBox
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MenuViewJUnitTest {
    //test the date check
    private val newsModel: INewsModel = DefaultNewsModel()
    private val menuController = MenuController(newsModel)
    private val sourcesController = SourcesController(newsModel)
    private val newsController = NewsController(newsModel)
    private val menuView = MenuView(menuController, sourcesController, newsController, "NewsApp")

    @BeforeEach
    fun setUp() {
        menuController.displayview()
    }

    @Test
    fun testDateCheck() {
        assertTrue { menuView.checkdateinput("2020-01-01") }
        assertFalse { menuView.checkdateinput("2020/01/32") }
    }

}