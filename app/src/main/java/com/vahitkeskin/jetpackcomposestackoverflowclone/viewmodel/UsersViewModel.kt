package com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel

import androidx.lifecycle.ViewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel.UsersModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.repository.UsersRepository
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 30.12.2022
 */
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private fun getHomeModelResponse() = flow {
        emit(State.LoadingState)
        try {
            delay(1000)
            emit(State.DataState(usersRepository))
        } catch (e: Exception) {
            emit(State.ErrorState(e))
        }
    }

    suspend fun home(search: String) : UsersModel {
        getHomeModelResponse()
        return usersRepository.invoke(search)
    }
}