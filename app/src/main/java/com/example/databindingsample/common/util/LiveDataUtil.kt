package com.example.databindingsample.common.util

import androidx.lifecycle.LiveData

val <T>LiveData<T>.safeValue: T
    get() = this.value ?: throw IllegalStateException("value was null.")