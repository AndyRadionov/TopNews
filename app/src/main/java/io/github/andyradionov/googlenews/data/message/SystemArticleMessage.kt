package io.github.andyradionov.googlenews.data.message

import io.github.andyradionov.googlenews.data.entities.Article

/**
 * @author Andrey Radionov
 */
class SystemArticleMessage(val article: Article, type: SystemMessageType) :
        SystemMessage(type)
