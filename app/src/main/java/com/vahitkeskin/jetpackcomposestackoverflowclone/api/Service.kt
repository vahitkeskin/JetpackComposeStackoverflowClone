package com.vahitkeskin.jetpackcomposestackoverflowclone.api

import com.vahitkeskin.jetpackcomposestackoverflowclone.model.homemodel.HomeModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.questionsmodel.QuestionsModel
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
    //https://api.stackexchange.com/2.3/search?order=desc&sort=activity&intitle=Representing%20a%20language-specific%20color%20code%20piece%20of%20code%20written%20in%20Jetpack%20Compose&site=stackoverflow&filter=!nOedRLbBQj
    @GET("/2.3/search")
    suspend fun getQuestions(@QueryMap questionsMap: HashMap<String, String>): QuestionsModel

}