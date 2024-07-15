package com.example.common.mapper

import com.example.common.model.exception.RouteException
import com.example.route.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ExceptionMapper {
    fun map(exception: Throwable): RouteException {
        return when (exception) {
            is HttpException -> {
                when (val code = exception.code()) {
                    401 -> RouteException.Client.Unauthorized
                    422 -> {
                        val errorBody = exception.response()?.errorBody()?.string()
                        val type = object : TypeToken<Map<String, Any>>() {}.type
                        val errorMap: Map<String, Any> = Gson().fromJson(errorBody, type)
                        val rawErrors =
                            errorMap["errors"] as? Map<String, ArrayList<String>> ?: emptyMap()
                        val errors = rawErrors.mapValues { it.value.joinToString() }
                        val message = errorMap["message"] as? String ?: "Unknown validation error"
                        RouteException.Client.ResponseValidation(errors, message)
                    }

                    in 400..499 -> RouteException.Client.Unhandled(code, exception.message())
                    in 500..599 -> RouteException.Server.InternalServerError(
                        code,
                        exception.message()
                    )

                    else -> RouteException.Unknown("HTTP Error: $code")
                }
            }

            is IOException -> {
                when (exception) {
                    is UnknownHostException ->
                        RouteException.Network.Unhandled(
                            R.string.network_unavailable,
                            message = "Network Error: ${exception.message}"
                        )

                    is SocketTimeoutException ->
                        RouteException.Network.Retrial(
                            R.string.timeout_error,
                            message = "Network Error: ${exception.message}"
                        )

                    else ->
                        RouteException.Network.Unhandled(
                            R.string.unsupported_type,
                            message = "Network Error: ${exception.message}"
                        )
                }
            }

            is RouteException.Local.RequestValidation -> {
                RouteException.Local.RequestValidation(
                    exception.clazz,
                    exception.message,
                    exception.errors
                )
            }

            else -> RouteException.Unknown("An unknown error occurred: ${exception.message}")
        }
    }
}