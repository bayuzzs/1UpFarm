package com.example.oneupfarm.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.oneupfarm.data.api.RetrofitClient
import retrofit2.Retrofit


//@Composable
//fun rememberRetrofit(context: Context): Retrofit {
//    return remember { RetrofitClient.create(context) }
//}

@Composable
fun staticRetrofit(): Retrofit {
    return remember { RetrofitClient.createStatic() }
}
