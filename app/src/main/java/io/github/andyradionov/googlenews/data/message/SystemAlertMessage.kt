package io.github.andyradionov.googlenews.data.message

/**
 * @author Andrey Radionov
 */
class SystemAlertMessage(val text: String, type: SystemMessageType) :
        SystemMessage(type)
