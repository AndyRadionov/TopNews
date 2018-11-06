package io.github.andyradionov.googlenews.utils

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler

/**
 * @author Andrey Radionov
 */

class RxComposers(private val subscribeScheduler: Scheduler,
                  private val observeScheduler: Scheduler) {

    fun getCompletableComposer(): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observeScheduler)
        }
    }

    fun <T> getFlowableComposer(): FlowableTransformer<T, T> {
        return FlowableTransformer { flowable ->
            flowable
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observeScheduler)
        }
    }

    fun <T> getObservableComposer(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observeScheduler)
        }
    }
}
