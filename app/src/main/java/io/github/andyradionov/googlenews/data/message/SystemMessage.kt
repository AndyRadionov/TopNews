package io.github.andyradionov.googlenews.data.message

data class SystemMessage(
    val text: String,
    val type: SystemMessageType = SystemMessageType.ALERT
)