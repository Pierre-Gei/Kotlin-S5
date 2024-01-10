package org.isen.newsapp.view.impl

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.web.WebView
import javafx.stage.Stage

class WebView : Application() {

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
        // Chargez l'URL dans le WebView
        launch(url)
    }

    fun close() {
        // Implémentez cette fonction pour fermer la fenêtre
        // Exemple : primaryStage.close()
    }
}
