package com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.HomeDetailViewState
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.Item
import com.vahitkeskin.jetpackcomposestackoverflowclone.screens.IViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 28.12.2022
 */
@HiltViewModel
class HomeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeDetailViewState, HomeDetailViewEvent>() {

    init {
        savedStateHandle.get<String>("homeDetail")?.let {
            setState { currentState.copy(isLoading = false, data = Item.create(it)) }
        } ?: kotlin.run {
            setEvent(HomeDetailViewEvent.SnackBarError("Something went wrong"))
        }
    }

    override fun createInitialState() = HomeDetailViewState()
    override fun onTriggerEvent(event: HomeDetailViewEvent) {}
}

sealed class HomeDetailViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : HomeDetailViewEvent()
}