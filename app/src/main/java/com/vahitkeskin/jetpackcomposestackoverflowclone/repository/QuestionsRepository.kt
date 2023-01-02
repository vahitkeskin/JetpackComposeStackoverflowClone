package com.vahitkeskin.jetpackcomposestackoverflowclone.repository

import com.vahitkeskin.jetpackcomposestackoverflowclone.api.Service
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel.QuestionsModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 2.01.2023
 */
class QuestionsRepository @Inject constructor(
    private val service: Service
) {
    suspend operator fun invoke(search: String): QuestionsModel {
        val hashMap: HashMap<String, String> = hashMapOf()
        hashMap[Contains.ORDER] = Contains.DESC
        hashMap[Contains.SORT] = Contains.ACTIVITY
        hashMap[Contains.INTITLE] = search
        hashMap[Contains.SITE] = Contains.STACKOVERFLOW
        hashMap[Contains.FILTER] = Contains.BODY_QUESTION
        return service.getQuestions(hashMap)
    }
}