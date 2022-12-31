package com.vahitkeskin.jetpackcomposestackoverflowclone.api

import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.HomeModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel.UsersModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
interface Service {

    @GET("/2.3/questions")
    suspend fun getHome(@QueryMap homeMap: HashMap<String, String>): HomeModel

    @GET("/2.3/users")
    suspend fun getUsers(@QueryMap usersMap: HashMap<String, String>): UsersModel

    //Questions
    //https://api.stackexchange.com/2.3/search/advanced?order=desc&sort=activity&q=Endpoint%20to%20access%20post%20detail%20on%20api.stackexchange%20site%20for%20Android&site=stackoverflow&filter=!nOedRLbBQj

    //https://johncodeos.com/how-to-make-post-get-put-and-delete-requests-with-retrofit-using-kotlin/
}