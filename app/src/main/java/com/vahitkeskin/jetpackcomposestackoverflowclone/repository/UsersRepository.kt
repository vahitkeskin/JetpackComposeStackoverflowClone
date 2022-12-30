package com.vahitkeskin.jetpackcomposestackoverflowclone.repository

import com.vahitkeskin.jetpackcomposestackoverflowclone.api.Service
import com.vahitkeskin.jetpackcomposestackoverflowclone.model.usersmodel.UsersModel
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 30.12.2022
 */
class UsersRepository @Inject constructor(
    private val service: Service
) {
    suspend operator fun invoke(string: String): UsersModel {
        return service.getUsers(string)
    }
}