package andyradionov.github.io.googlenews.news

import andyradionov.github.io.googlenews.data.Article
import andyradionov.github.io.googlenews.data.NewsApi
import andyradionov.github.io.googlenews.data.NewsStore
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import java.util.*
import kotlin.collections.ArrayList


/**
 * @author Andrey Radionov
 */
class NewsPresenterTest {
    companion object {
        const val CORRECT_QUERY = ""
        const val INCORRECT_QUERY = "incorrect_query"
        val list = arrayListOf(Article())
    }

    @get:Rule var mockitoRule = MockitoJUnit.rule()
    @Mock lateinit var newsApiMock: NewsApi
    @Mock lateinit var viewMock: NewsContract.View
    @Spy @InjectMocks lateinit var newsStoreMock: NewsStore
    private lateinit var presenter: NewsPresenter

    @Before
    fun setup() {
        presenter = NewsPresenter(newsStoreMock)
        presenter.attachView(viewMock)

        Mockito.doAnswer({presenter.onSuccessLoading(list) })
                .`when`(newsStoreMock).fetchNews(CORRECT_QUERY, presenter)

        Mockito.doAnswer({presenter.onErrorLoading() })
                .`when`(newsStoreMock).fetchNews(INCORRECT_QUERY, presenter)
    }

    @Test
    fun testGetTopNews() {
        presenter.getTopNews()

        Mockito.verify(newsStoreMock, Mockito.times(1)).fetchNews(CORRECT_QUERY, presenter)
        Mockito.verify(viewMock, Mockito.times(1)).showNews(list)
    }

    @Test
    fun testIncorrectSearch() {
        presenter.searchNews(INCORRECT_QUERY)

        Mockito.verify(newsStoreMock, Mockito.times(1)).fetchNews(INCORRECT_QUERY, presenter)
        Mockito.verify(viewMock, Mockito.times(1)).showError()
    }

}