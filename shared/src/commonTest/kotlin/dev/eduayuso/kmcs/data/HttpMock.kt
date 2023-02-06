package dev.eduayuso.kmcs.data

import dev.eduayuso.kmcs.AppConstants
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object HttpMock {

    val engine = MockEngine { request ->

        handleRequest(request)
    }

    private fun MockRequestHandleScope.handleRequest(request: HttpRequestData): HttpResponseData {

        val api = AppConstants.Apis.DummyApi

        val responseContent = with(request.url.encodedPath) {
            when {
                contains("${api.posts}/1") -> MockedResponses.postJsonResponse
                contains(api.posts) -> MockedResponses.postListJsonResponse
                contains(api.users) -> MockedResponses.userListJsonResponse
                else -> null
            }.apply {
                Json.encodeToString(this)
            }
        }

        return if (responseContent == null) {
            errorResponse()
        } else {
            respond(
                content = responseContent,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
    }

    private fun MockRequestHandleScope.errorResponse(): HttpResponseData {

        return respond(
            content = "",
            status = HttpStatusCode.BadRequest,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }
}