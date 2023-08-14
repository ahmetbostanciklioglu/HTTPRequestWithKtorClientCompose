package com.ahmet.bostanciklioglu.httprequestwithktorclientcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestModel(
    val userId: Int,
    val id : Int,
    val title: String,
    val body: String
)