import org.isen.newsapp.controller.SourcesController
import org.isen.newsapp.model.impl.DefaultNewsModel
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class SourcesControllerJUnitTest {

    private val sourcesController = SourcesController(DefaultNewsModel())

    @Test
    fun `test checkRequest always returns true`() {
        val result = sourcesController.checkRequest("country", "category", "language")
        assertTrue(result)
    }
}
