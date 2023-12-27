package org.isen.newsapp.view.impl

import org.apache.logging.log4j.kotlin.logger
import org.isen.newsapp.controller.NewsController
import org.isen.newsapp.model.data.Article
import org.isen.newsapp.model.data.ArticlesReq
import org.isen.newsapp.view.INewsView
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.beans.PropertyChangeEvent
import javax.swing.*
import javax.swing.border.EmptyBorder


class NewsEverythingView (val controller: NewsController, title: String="News App"): INewsView, ActionListener{

    companion object logging

    private var newsList:JComboBox<Article> = JComboBox<Article>().apply {
        addActionListener(this@NewsEverythingView)
    }

    private val frame : JFrame
    init {
        frame = JFrame().apply {
            isVisible = false
            contentPane = makeGUI()
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            this.title = title
            this.preferredSize = Dimension(800, 600)
            this.pack()
        }
        this.controller.registerviewtoall(this)
    }

    private fun makeGUI(): JPanel {
        val contentPane = JPanel()
        contentPane.layout = BorderLayout()
        contentPane.add(createNewsComboBox(), BorderLayout.NORTH)
        return contentPane
    }

    private fun createNewsComboBox(): JPanel {
        val contentPane = JPanel()
        contentPane.layout = BorderLayout()
        contentPane.add(JLabel("News Evrything"), BorderLayout.NORTH)
        newsList.border = EmptyBorder(10, 10, 10, 10)
        contentPane.add(newsList, BorderLayout.CENTER)
        return contentPane
    }
    override fun display() {
        frame.isVisible = true
    }

    override fun close() {
        this.controller.closeview()
    }

    override fun propertyChange(evt: PropertyChangeEvent?) {
        if (evt != null) {
            if (evt.newValue is ArticlesReq) {
                logger().info("NewsView: ${evt.newValue}")
                newsList.model = DefaultComboBoxModel<Article>((evt.newValue as ArticlesReq).articles.toTypedArray())
            }
        } else {
            logger().error("evt is null")
        }
    }

    override fun actionPerformed(e: ActionEvent?) {
        if (e != null) {
            if (e.source is JComboBox<*>) {
                logger().info("actionPerformed: ${e.actionCommand}")
            }
        }
    }
}