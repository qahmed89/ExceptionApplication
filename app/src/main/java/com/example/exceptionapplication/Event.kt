package com.example.exceptionapplication

sealed interface Event {
    object StartUp : Event
    object ThrownExecution : Event
    object HandleExpectation : Event
    object AccountButtonClicked : Event
    object ReloadButtonClicked : Event
    object StopReloadButtonClicked : Event



}