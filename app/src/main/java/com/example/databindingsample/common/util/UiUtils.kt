package com.example.databindingsample.common.util

import android.view.View
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorText")
fun TextInputLayout.setErrorMessage(@StringRes messageResId: Int) {
    error = if (messageResId == -1) "" else context.getString(messageResId)
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










