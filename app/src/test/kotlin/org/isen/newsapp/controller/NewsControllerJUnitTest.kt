package org.isen.newsapp.controller

import org.isen.newsapp.controller.NewsController
import org.isen.newsapp.model.impl.DefaultNewsModel
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NewsControllerJUnitTest {

    private val newsController = NewsController(DefaultNewsModel())

    @Test
    fun `test checkRequestEverything returns false when all parameters are empty`() {
        val result = newsController.checkRequestEverything("", "", "", "", "")
        assertFalse(result)
    }

    @Test
    fun `test checkRequestEverything returns false when keyword is empty`() {
        val result = newsController.checkRequestEverything("", "from", "to", "sort", "language")
        assertFalse(result)
    }

    @Test
    fun `test checkRequestEverything returns true when at least keyword is provided`() {
        val result = newsController.checkRequestEverything("keyword", "", "", "", "")
        assertTrue(result)
    }

    @Test
    fun `test checkRequestHeadlines returns false when all parameters are empty`() {
        val result = newsController.checkRequestHeadlines("", "", "", "")
        assertFalse(result)
    }

    @Test
    fun `test checkRequestHeadlines returns true when at least one parameter is provided`() {
        val result = newsController.checkRequestHeadlines("keyword", "country", "category", "language")
        assertTrue(result)
    }

}
