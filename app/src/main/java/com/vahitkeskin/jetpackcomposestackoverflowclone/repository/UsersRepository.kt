package com.vahitkeskin.jetpackcomposestackoverflowclone.repository

import com.vahitkeskin.jetpackcomposestackoverflowclone.api.Service
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel.UsersModel
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 30.12.2022
 */
class UsersRepository @Inject constructor(
    private val service: Service
) {
    suspend operator fun invoke(search: String): UsersModel {
        val hashMap: HashMap<String, String> = hashMapOf()
        hashMap[Contains.SITE] = Contains.STACKOVERFLOW
        if (search.isNotEmpty() && search.isNotBlank()) {
            //Search was made on the Users screen.
            hashMap[Contains.INNAME] = search
        }
        hashMap[Contains.SORT] = Contains.REPUTATION
        hashMap[Contains.ORDER] = Contains.DESC
        return service.getUsers(hashMap)
    }
}