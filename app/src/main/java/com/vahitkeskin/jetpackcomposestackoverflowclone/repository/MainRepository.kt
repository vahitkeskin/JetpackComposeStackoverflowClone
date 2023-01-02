package com.vahitkeskin.jetpackcomposestackoverflowclone.repository

import com.vahitkeskin.jetpackcomposestackoverflowclone.api.Service
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.HomeModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
class MainRepository @Inject constructor(
    private val service: Service
) {
    suspend operator fun invoke(): HomeModel {
        val hashMap: HashMap<String, String> = hashMapOf()
        hashMap[Contains.FILTER] = Contains.BODY_HOME
        hashMap[Contains.SITE] = Contains.STACKOVERFLOW
        hashMap[Contains.SORT] = Contains.ACTIVITY
        hashMap[Contains.ORDER] = Contains.DESC
        return service.getHome(hashMap)
    }
}