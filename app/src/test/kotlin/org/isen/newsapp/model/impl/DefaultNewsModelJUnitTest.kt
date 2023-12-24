package org.isen.newsapp.model.impl

import org.apache.logging.log4j.kotlin.logger
import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.model.data.ArticlesReq
import java.beans.PropertyChangeListener
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class DefaultNewsModelJUnitTest {
    companion object logging
    @Test
    fun findEverything() {
        var passObserver = false
        var data_result: Any? = null
        val model = DefaultNewsModel()

        val myObserver = PropertyChangeListener {
            passObserver = true
            data_result = it.newValue
        }
        model.register(INewsModel.NEWS_ALL, myObserver)
        model.fetchNews("q=bitcoin", "d085fa05e7ca462c8bb0e770ec30f41e", INewsModel.NEWS_ALL)
        logger().info("wait for data...")
        Thread.sleep(10000)

        assertTrue(passObserver)
        data_result?.let {
            assertEquals(ArticlesReq::class.java, it::class.java)
            assertEquals(100, (it as ArticlesReq).articles.size)
        } ?: fail("data_result is null")
    }
}