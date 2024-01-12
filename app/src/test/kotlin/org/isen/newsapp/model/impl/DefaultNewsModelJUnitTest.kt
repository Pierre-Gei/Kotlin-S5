import kotlinx.coroutines.runBlocking
import org.isen.newsapp.model.INewsModel
import org.isen.newsapp.model.impl.DefaultNewsModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DefaultNewsModelTest {

    private val apiKey = "d085fa05e7ca462c8bb0e770ec30f41e"
    private val validQueryArgs = "q=bitcoin"
    private val invalidQueryArgs = ""

    @Test
    fun `fetchEverything with valid arguments should return valid ArticlesResult`() = runBlocking {
        val model = DefaultNewsModel()
        val result = model.fetchEverything(validQueryArgs, apiKey)
        assertEquals("", result.err)
        assert(result.articles != null)
    }

    @Test
    fun `fetchEverything with invalid query arguments should return error in ArticlesResult`() = runBlocking {
        val model = DefaultNewsModel()
        val result = model.fetchEverything(invalidQueryArgs, apiKey)
        result.err?.let { assert(it.isNotBlank()) }
        assert(result.articles == null)
    }

    @Test
    fun `fetchHeadlines with valid arguments should return valid ArticlesResult`() = runBlocking {
        val model = DefaultNewsModel()
        val result = model.fetchHeadlines(validQueryArgs, apiKey)
        assertEquals("", result.err)
        assert(result.articles != null)
    }

    @Test
    fun `fetchHeadlines with invalid query arguments should return error in ArticlesResult`() = runBlocking {
        val model = DefaultNewsModel()
        val result = model.fetchHeadlines(invalidQueryArgs, apiKey)
        result.err?.let { assert(it.isNotBlank()) }
        assert(result.articles == null)
    }

    @Test
    fun `fetchSources with valid arguments should return valid SourcesResult`() {
        val model = DefaultNewsModel()
        val result = model.fetchSources(validQueryArgs, apiKey)
        assertEquals("", result.err)
        assert(result.sources != null)
    }

    @Test
    fun `fetchSources with invalid query arguments should return error in SourcesResult`() {
        val model = DefaultNewsModel()
        val result = model.fetchSources(invalidQueryArgs, apiKey)
        result.err?.let { assert(it.isNotBlank()) }
        assert(result.sources == null)
    }

    @Test
    fun `fetchSources with invalid API key should throw IllegalArgumentException`() {
        val model = DefaultNewsModel()
        //respond ArticleResult(null, "apiKey is empty") when fetchSources is called with invalid API key (empty)
        kotlin.test.assertEquals("API_KEY is empty", model.fetchSources(validQueryArgs, "").err)
    }

    @Test
    fun `fetchSources with invalid query arguments should throw IllegalArgumentException`() {
        val model = DefaultNewsModel()
        //respond ArticleResult(null, "querry_args is empty") when fetchSources is called with invalid query arguments (empty)
        kotlin.test.assertEquals("querry_args is empty", model.fetchSources("", apiKey).err)
    }

    @Test
    fun `fetchNews with invalid type should throw IllegalArgumentException`() {
        val model = DefaultNewsModel()
        //respond ArticleResult(null, "type is not valid") when fetchNews is called with invalid type
        assertThrows<IllegalArgumentException> { model.fetchNews(validQueryArgs, apiKey, "invalidType") }
    }
}