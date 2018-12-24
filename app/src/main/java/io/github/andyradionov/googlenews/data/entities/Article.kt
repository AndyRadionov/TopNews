package io.github.andyradionov.googlenews.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "articles", primaryKeys = ["title", "publishedAt"])
data class Article(
        @SerializedName("publishedAt") val publishedAt: Date,
        @SerializedName("author") val author: String?,
        @SerializedName("urlToImage") val urlToImage: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("title") val title: String,
        @SerializedName("url") val url: String,
        @SerializedName("isFavourite") var isFavourite: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
            Date(parcel.readLong()),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(publishedAt.time)
        parcel.writeString(author)
        parcel.writeString(urlToImage)
        parcel.writeString(description)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeByte(if (isFavourite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}
