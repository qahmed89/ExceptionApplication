package com.example.exceptionapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlin.coroutines.coroutineContext


class MainViewModel : ViewModel() {
    private var _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()
    private val handlerException = CoroutineExceptionHandler { x, throwable ->
        viewModelScope.launch {
            _state.value = _state.value.copy(data = "Throw")
            onEvent(Event.HandleExpectation)
            return@launch
        }
    }


    init {
        onEvent(Event.StartUp)
    }

    fun onEvent(event: Event) {
        viewModelScope.launch(handlerException) {
            when (event) {

                is Event.StartUp -> {
                    delay(500L)
                    _state.update {  it.copy(data = "Created", isloading = false)}
                    Log.i("Finished", "Created")
                }
                is Event.ThrownExecution -> {
                    throw Exception("Throw")

                }
                is Event.HandleExpectation -> {
                    _state.value = _state.value.copy(data = "${viewModelScope.isActive}")
                }
                is Event.AccountButtonClicked -> {
                    _state.value = _state.value.copy(isloading = true)

                }
                Event.ReloadButtonClicked -> {
                    _state.update {
                        it.copy(isloading = true, data = " Loading Now")

                    }
                    Log.i("Finished", "Created")

                }

                Event.StopReloadButtonClicked -> _state.update {
                    it.copy(isloading = false, data = " Stop Loading")
                }
                }
            }
        }


    }

    data class UiState(
        val isloading: Boolean = true,
        val data: String = "Hello",
        val isAccountButtonClicked: Boolean = false,
    )