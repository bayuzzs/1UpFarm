package com.example.oneupfarm.data.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenProvider: () -> String?,
    private val onUnauthorized: () -> Unit
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider()
        val request = chain.request().newBuilder()
        if (token != null) {
            request.addHeader("Authorization", "Bearer $token")
        }

        val response = chain.proceed(request.build())
//        if (response.code() == 401) {
//            // Trigger unauthorized action
//            onUnauthorized()
//        }
        return response
    }
}


//class AuthInterceptor(
//    private val tokenProvider: () -> String?,
//    private val refreshTokenProvider: () -> String?,
//    private val onTokenUpdated: (String) -> Unit,
//    private val authApi: AuthApi
//) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val originalRequest = chain.request()
//        val requestBuilder = originalRequest.newBuilder()
//
//        // Tambahkan header Authorization dengan access token
//        tokenProvider()?.let { token ->
//            requestBuilder.addHeader("Authorization", "Bearer $token")
//        }
//
//        val response = chain.proceed(requestBuilder.build())
//
//        if (response.code == 401) {
//            synchronized(this) { // Hindari beberapa thread refresh token bareng
//                // Coba refresh token
//                val refreshToken = refreshTokenProvider()
//                if (refreshToken != null) {
//                    val newToken = try {
//                        val refreshResponse = authApi.refreshAccessToken("Bearer $refreshToken").execute()
//                        if (refreshResponse.isSuccessful) {
//                            refreshResponse.body()?.accessToken
//                        } else null
//                    } catch (e: Exception) {
//                        null
//                    }
//
//                    if (newToken != null) {
//                        // Simpan token baru dan ulangi request
//                        onTokenUpdated(newToken)
//                        return chain.proceed(
//                            originalRequest.newBuilder()
//                                .header("Authorization", "Bearer $newToken")
//                                .build()
//                        )
//                    }
//                }
//            }
//        }
//
//        return response
//    }
//}
