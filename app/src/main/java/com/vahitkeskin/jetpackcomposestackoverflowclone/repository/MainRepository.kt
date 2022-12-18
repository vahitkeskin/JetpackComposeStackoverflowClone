package com.vahitkeskin.jetpackcomposestackoverflowclone.repository

import com.vahitkeskin.jetpackcomposestackoverflowclone.api.Service
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.HomeModel
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
class MainRepository @Inject constructor(
    private val service: Service
) {
    suspend operator fun invoke(): HomeModel {
        return service.getHome()
    }
}