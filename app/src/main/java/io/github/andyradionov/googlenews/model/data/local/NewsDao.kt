package io.github.andyradionov.googlenews.model.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.github.andyradionov.googlenews.entities.Article
import io.reactivex.Flowable

/**
 * @author Andrey Radionov
 */
@Dao
interface NewsDao {

    @Query("SELECT * FROM articles ORDER BY articleId DESC")
    fun getFavouriteNews() : Flowable<List<Article>>

    @Insert
    fun addToFavourites(article: Article)

    @Query("DELETE FROM articles WHERE articleId=:articleId")
    fun removeFromFavouritesById(articleId: Int)
}