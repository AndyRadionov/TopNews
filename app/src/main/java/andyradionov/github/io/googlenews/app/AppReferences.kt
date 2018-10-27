package andyradionov.github.io.googlenews.app

/**
 * @author Andrey Radionov
 */

const val COUNTRY_CODE = "ru"
const val PAGE_SIZE = 20
const val SORT_BY = "publishedAt"
const val TOP_NEWS_REQUEST = "top-headlines?country=$COUNTRY_CODE&pageSize=$PAGE_SIZE"
const val SEARCH_NEWS_REQUEST = "everything?sortBy=$SORT_BY&pageSize=$PAGE_SIZE"
