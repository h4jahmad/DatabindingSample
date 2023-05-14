package com.example.databindingsample.common.util

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

typealias OnItemClicked<T> = (T) -> Unit

@BindingAdapter("errorText")
fun TextInputLayout.setErrorMessage(@StringRes messageResId: Int) {
    error = if (messageResId == -1) "" else context.getString(messageResId)
}

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String) {
    load(url) {
        crossfade(true)
    }
}
@BindingAdapter("circularLoadUrl")
fun ImageView.circularLoadUrl(url: String) {
    load(url) {
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}

fun TextInputLayout.clearError() {
    error = ""
}

fun View.showSnackbar(@StringRes textResId: Int, onDismissed: () -> Unit = {}) {
    Snackbar.make(this, textResId, Snackbar.LENGTH_SHORT).apply {
        addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                onDismissed()
            }
        })
        show()
    }
}

fun View.showSnackbar(message: String, onDismissed: () -> Unit = {}) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply {
        addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                onDismissed()
            }
        })
        show()
    }
}

fun Resources.pxToDp(px: Int): Int = (px / displayMetrics.density).toInt()


