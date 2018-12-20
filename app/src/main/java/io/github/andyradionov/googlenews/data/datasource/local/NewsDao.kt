package io.github.andyradionov.googlenews.data.datasource.local

import android.arch.persistence.room.*
import io.github.andyradionov.googlenews.data.entities.Article
import io.reactivex.Flowable

/**
 * @author Andrey Radionov
 */
@Dao
interface NewsDao {

    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    fun getFavouriteNews() : Flowable<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavourites(article: Article)

    @Delete
    fun removeFromFavouritesById(article: Article)
}
