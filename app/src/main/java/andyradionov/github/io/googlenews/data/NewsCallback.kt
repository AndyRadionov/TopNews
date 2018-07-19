package andyradionov.github.io.googlenews.data

/**
 * @author Andrey Radionov
 */
interface NewsCallback {
    fun onSuccessLoading(articles: List<Article>)

    fun onErrorLoading()
}