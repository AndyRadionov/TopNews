package andyradionov.github.io.googlenews.news

import andyradionov.github.io.googlenews.data.Article
import andyradionov.github.io.googlenews.data.NewsApi
import andyradionov.github.io.googlenews.data.NewsRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit


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
    @Spy @InjectMocks lateinit var newsRepositoryMock: NewsRepository
    private lateinit var presenter: NewsPresenter

    @Before
    fun setup() {
        presenter = NewsPresenter(newsRepositoryMock)
        presenter.attachView(viewMock)

        Mockito.doAnswer({presenter.onSuccessLoading(list) })
                .`when`(newsRepositoryMock).fetchNews(CORRECT_QUERY, presenter)

        Mockito.doAnswer({presenter.onErrorLoading() })
                .`when`(newsRepositoryMock).fetchNews(INCORRECT_QUERY, presenter)
    }

    @Test
    fun testGetTopNews() {
        presenter.getTopNews()

        Mockito.verify(newsRepositoryMock, Mockito.times(1)).fetchNews(CORRECT_QUERY, presenter)
        Mockito.verify(viewMock, Mockito.times(1)).showNews(list)
    }

    @Test
    fun testIncorrectSearch() {
        presenter.searchNews(INCORRECT_QUERY)

        Mockito.verify(newsRepositoryMock, Mockito.times(1)).fetchNews(INCORRECT_QUERY, presenter)
        Mockito.verify(viewMock, Mockito.times(1)).showError()
    }

}