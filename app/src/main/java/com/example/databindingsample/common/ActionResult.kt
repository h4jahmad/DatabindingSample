package com.example.databindingsample.common

sealed interface ActionResult<out T> {
    data class Success<out T>(val data: T): ActionResult<T>
    data class Error(val e: Exception): ActionResult<Nothing>
}