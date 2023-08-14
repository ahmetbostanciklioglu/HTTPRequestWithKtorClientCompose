package com.ahmet.bostanciklioglu.httprequestwithktorclientcompose.network

import com.ahmet.bostanciklioglu.httprequestwithktorclientcompose.model.RequestModel
import com.ahmet.bostanciklioglu.httprequestwithktorclientcompose.model.ResponseModel
import com.ahmet.bostanciklioglu.httprequestwithktorclientcompose.network.ApiRoutes
import com.ahmet.bostanciklioglu.httprequestwithktorclientcompose.network.ApiService
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url


class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {
    override suspend fun getProducts(): List<ResponseModel> {
        return try {
            client.get {
                url(ApiRoutes.PRODUCTS)
            }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createProducts(productRequest: RequestModel): ResponseModel? {
        return try {
            client.post<ResponseModel> {
                url(ApiRoutes.PRODUCTS)
                body = productRequest
            }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error: ${ex.response.status.description}")
            null
        }
    }
}