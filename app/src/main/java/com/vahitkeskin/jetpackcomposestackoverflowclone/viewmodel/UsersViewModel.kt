package com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel.UsersModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.repository.UsersRepository
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 30.12.2022
 */
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _usersModel = MutableLiveData<UsersModel>()
    val usersModel: LiveData<UsersModel> = _usersModel

    init {
        home()
    }

    private fun getHomeModelResponse() = flow {
        emit(State.LoadingState)
        try {
            delay(1000)
            emit(State.DataState(usersRepository))
        } catch (e: Exception) {
            emit(State.ErrorState(e))
        }
    }

    private fun home() {
        viewModelScope.launch {
            getHomeModelResponse()
            _usersModel.value = usersRepository.invoke()
        }
    }
}