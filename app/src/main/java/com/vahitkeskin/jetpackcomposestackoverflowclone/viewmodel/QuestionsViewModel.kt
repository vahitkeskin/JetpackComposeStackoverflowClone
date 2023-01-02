package com.vahitkeskin.jetpackcomposestackoverflowclone.viewmodel

import androidx.lifecycle.ViewModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel.QuestionsModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.repository.QuestionsRepository
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 2.01.2023
 */
@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val questionsRepository: QuestionsRepository
) : ViewModel() {

    private fun getQuestionModelResponse() = flow {
        emit(State.LoadingState)
        try {
            delay(1000)
            emit(State.DataState(questionsRepository))
        } catch (e: Exception) {
            emit(State.ErrorState(e))
        }
    }

    suspend fun home(search: String) : QuestionsModel {
        getQuestionModelResponse()
        return questionsRepository.invoke(search)
    }
}