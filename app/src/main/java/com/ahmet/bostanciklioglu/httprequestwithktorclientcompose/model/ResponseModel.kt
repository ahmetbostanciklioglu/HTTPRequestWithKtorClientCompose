package com.ahmet.bostanciklioglu.httprequestwithktorclientcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val userId: Int,
    val id : Int,
    val title: String,
    val body: String
)
