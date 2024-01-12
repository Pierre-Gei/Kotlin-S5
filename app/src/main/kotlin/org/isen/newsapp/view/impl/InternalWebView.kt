import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.web.WebView
import javafx.stage.Stage
import org.apache.logging.log4j.kotlin.logger

class InternalWebView : Application() {
    private val internalWebViewInstances: MutableList<InternalWebView> = mutableListOf()
    private lateinit var webView: WebView

    override fun start(primaryStage: Stage) {
        logger().info("start webview")
        webView = WebView()
        val scene = Scene(webView, 800.0, 600.0)
        primaryStage.title = "WebViewer"
        primaryStage.scene = scene
        parameters.raw.firstOrNull()?.let { url ->
            Platform.runLater {
                webView.engine.load(url)
            }
        }
        internalWebViewInstances.add(this)
        primaryStage.show()
    }

    fun display(url: String) {
        Platform.runLater {
            logger().info("display webview with url $url")
            val internalWebView = InternalWebView()
            val stage = Stage()
            val newWebView = WebView()
            newWebView.engine.load(url)
            stage.scene = Scene(newWebView, 800.0, 600.0)
            stage.title = "Web Page"
            stage.show()
            internalWebViewInstances.add(internalWebView)
        }
    }
    init {
        Platform.setImplicitExit(false)
    }
    override fun stop() {
        logger().info("stop webview")
        internalWebViewInstances.remove(this)
    }
}
