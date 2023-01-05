package com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * @authot: Vahit Keskin
 * creared on 5.01.2023
 */
class SecondsViewModel : ViewModel() {
    val seconds = (9 downTo 0)
        .asSequence()
        .asFlow()
        .map {
            if (it in 0..9) "0$it" else it
        }
        .onEach {
            delay(1000)
        }
}