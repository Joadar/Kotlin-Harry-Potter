package io.smallant.wizard.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.smallant.wizard.utils.Event

fun <T> LiveData<Event<T>>.listenEvent(lifecycleOwner: LifecycleOwner, block: (T) -> Unit) {
    this.observe(lifecycleOwner, Observer { event ->
        event?.getContentIfNotHandled()?.let {
            block(it)
        }
    })
}