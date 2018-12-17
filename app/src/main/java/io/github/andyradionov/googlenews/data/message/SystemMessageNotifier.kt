package io.github.andyradionov.googlenews.data.message

import com.jakewharton.rxrelay2.PublishRelay
import io.github.andyradionov.googlenews.data.entities.Article
import io.reactivex.Observable

class SystemMessageNotifier {
    private val notifierRelay = PublishRelay.create<SystemMessage>()

    val notifier: Observable<SystemMessage> = notifierRelay.hide()
    fun send(text: String) =
            notifierRelay.accept(SystemAlertMessage(text, SystemMessageType.ALERT))
    fun send(article: Article) =
            notifierRelay.accept(SystemArticleMessage(article, SystemMessageType.BOTTOM))
}