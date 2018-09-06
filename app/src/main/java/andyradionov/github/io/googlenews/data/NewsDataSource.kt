package andyradionov.github.io.googlenews.data

import android.arch.paging.PageKeyedDataSource
import andyradionov.github.io.googlenews.data.entities.Article


class NewsDataSource : PageKeyedDataSource<Int, Article>() {
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {

    }
}