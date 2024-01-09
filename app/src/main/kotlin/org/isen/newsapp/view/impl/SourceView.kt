package org.isen.newsapp.view.impl

import org.apache.logging.log4j.kotlin.logger
import org.isen.newsapp.controller.SourcesController
import org.isen.newsapp.model.data.*
import org.isen.newsapp.view.INewsView
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.beans.PropertyChangeEvent
import javax.swing.*
import javax.swing.border.EmptyBorder

class SourceView (val controller: SourcesController, title: String="News App"): INewsView, ActionListener {
    companion object logging

    private var sourcesList: JComboBox<Source> = JComboBox<Source>().apply {
        addActionListener(this@SourceView)
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
        this.controller.registerVeiwToSorces(this)
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
        contentPane.add(JLabel("News Sources"), BorderLayout.NORTH)
        sourcesList.border = EmptyBorder(10, 10, 10, 10)
        contentPane.add(sourcesList, BorderLayout.CENTER)
        return contentPane
    }

    override fun displayNews(articles: ArticlesResult) {
        TODO("Not yet implemented")
    }

    override fun displaySources(sources: SourcesResult) {
        TODO("Not yet implemented")
    }


    override fun displayError(error: Exception) {
        TODO("Not yet implemented")
    }

    override fun display() {
        frame.isVisible = true
    }

    override fun close() {
        this.controller.closeview()
    }

    override fun propertyChange(evt: PropertyChangeEvent?) {
        if (evt != null) {
            if (evt.newValue is SourceReq) {
                logger().info("NewsView: ${evt.newValue}")
                sourcesList.model = DefaultComboBoxModel<Source>((evt.newValue as SourceReq).sources.toTypedArray())
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