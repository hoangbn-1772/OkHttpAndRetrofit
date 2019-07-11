package com.sun.okhttp_retrofit.data.datasource

import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import com.sun.okhttp_retrofit.data.model.DataWrapper

class ApiObserver<T>(@NonNull private val changeListener: ChangeListener<T>) : Observer<DataWrapper<T>> {

    override fun onChanged(t: DataWrapper<T>?) {
        t?.let {
            t.exception?.let {
                changeListener.onException(it)
            } ?: it.data?.let { it1 -> changeListener.onSuccess(it1) }
        }
    }

    interface ChangeListener<T> {
        fun onSuccess(dataWrapper: T)

        fun onException(exception: Exception)
    }
}
