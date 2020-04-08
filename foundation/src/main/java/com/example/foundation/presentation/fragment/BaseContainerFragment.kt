package com.example.foundation.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

abstract class BaseContainerFragment : InjectionFragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutResourceId, null).also {
            Timber.v("onCreateView ${javaClass.simpleName}")
        }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }
}
