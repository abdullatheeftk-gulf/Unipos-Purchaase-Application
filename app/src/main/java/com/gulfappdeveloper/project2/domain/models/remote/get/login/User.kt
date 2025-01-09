package com.gulfappdeveloper.project2.domain.models.remote.get.login

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val passWord: String,
    val userId: Int,
    val userName: String
)
