package com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.home.HomeModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.repository.MainRepository
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _homeModel = MutableLiveData<HomeModel>()
    val homeModel: LiveData<HomeModel> = _homeModel

    init {
        home()
    }

    private fun getHomeModelResponse() = flow {
        emit(State.LoadingState)
        try {
            delay(1000)
            emit(State.DataState(mainRepository))
        } catch (e: Exception) {
            emit(State.ErrorState(e))
        }
    }

    private fun home() {
        viewModelScope.launch {
            getHomeModelResponse()
            println("2. Hello selected home model in items: ${mainRepository.invoke().items.size}")
            _homeModel.value = mainRepository.invoke()
        }
    }
}