package dev.eduayuso.kmcs.data.source.remote

import dev.eduayuso.kmcs.AppConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.*

open class ApiClient(

    val httpClient: HttpClient,
    val baseUrl: String

) {

    suspend inline fun <reified T> get(endpoint: String): T? =

        httpClient.get("$baseUrl/$endpoint") {
            headers {
                append(
                    name = AppConstants.Http.Headers.appId,
                    value = AppConstants.Apis.DummyApi.appId
                )
            }
        }.body()

    suspend inline fun <reified IN : Any, reified OUT> post(endpoint: String, body: IN): OUT? =

        httpClient.post {
            url("$baseUrl/$endpoint")
            contentType(ContentType.Application.Json)
            headers {
                append(
                    name = AppConstants.Http.Headers.appId,
                    value = AppConstants.Apis.DummyApi.appId
                )
            }
            setBody(body)
        }.body()
}

