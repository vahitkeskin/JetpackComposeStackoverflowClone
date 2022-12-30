package com.vahitkeskin.jetpackcomposestackoverflowclone.api

import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.HomeModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel.UsersModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
interface Service {
    //Home Screen end-point
    @GET("/2.3/questions?order=desc&sort=activity&site=stackoverflow&filter=!6Wfm_gSyj6iTS")//filter=!6Wfm_gSyj6iTS
    suspend fun getHome(): HomeModel

    //Questions
    //https://api.stackexchange.com/2.3/search/advanced?order=desc&sort=activity&q=Endpoint%20to%20access%20post%20detail%20on%20api.stackexchange%20site%20for%20Android&site=stackoverflow&filter=!nOedRLbBQj

    //Users
    //https://api.stackexchange.com/2.3/users?order=desc&sort=reputation&inname=vahit%20keskin&site=stackoverflow

    //User image: profile_image
    //Name: display_name
    //Location: location
    //Point: reputation_change_week
    //Tags: user_id -> 2887218 and https://api.stackexchange.com/2.3/users/2887218/tags?order=desc&sort=popular&site=stackoverflow

    // -> User Single https://api.stackexchange.com/2.3/users?order=desc&sort=reputation&inname=vahit%20keskin&site=stackoverflow
    // -> Users all https://api.stackexchange.com/2.3/users?order=desc&sort=reputation&site=stackoverflow

    @GET("/2.3/users?order=desc&sort=reputation&site=stackoverflow")
    suspend fun getUsers(
        @Query("inname") inname: String = "vahit keskin"
    ): UsersModel
}