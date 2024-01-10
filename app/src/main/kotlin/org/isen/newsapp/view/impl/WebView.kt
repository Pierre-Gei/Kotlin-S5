package org.isen.newsapp.view.impl

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.web.WebView
import javafx.stage.Stage
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener

class WebView : Application() {
    val url: String? = null

    override fun start(primaryStage: Stage) {
        val webView = javafx.scene.web.WebView()
        val scene = Scene(webView, 800.0, 600.0)
        primaryStage.title = "My JavaFX App"
        primaryStage.scene = scene

        // Vérifiez si des paramètres de programme sont fournis
        parameters.raw.firstOrNull()?.let { url ->
            // Si une URL est fournie, chargez-la dans le WebView
            webView.engine.load(url)
        }

        primaryStage.show()
    }

    fun display(url: String) {
        Platform.runLater {
            val webView = WebView()
            webView.engine.load(url)

            // Create a new stage and set the WebView as its content
            val stage = Stage()
            stage.scene = Scene(webView, 800.0, 600.0)
            stage.title = "Web Page"
            stage.show()
        }
    }

    init {
    }

    override fun stop() {
        // stop the model

    }

    //implements
}
