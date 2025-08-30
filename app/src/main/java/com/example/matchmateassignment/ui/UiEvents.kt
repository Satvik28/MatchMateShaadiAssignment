package com.example.matchmateassignment.ui

sealed class UiEvents<out T> {
    data class Success<T>(val data: T) : UiEvents<T>()
    object Loading : UiEvents<Nothing>()
    data class Error(val message: String) : UiEvents<Nothing>()
}