package com.vahitkeskin.jetpackcomposestackoverflowclone.api

import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.HomeModel
import retrofit2.http.GET

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
interface Service {
    //Home Screen end-point
    @GET("/2.3/questions?order=desc&sort=activity&site=stackoverflow")
    suspend fun getHome(): HomeModel
}