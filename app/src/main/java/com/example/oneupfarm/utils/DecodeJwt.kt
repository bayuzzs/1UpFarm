package com.example.oneupfarm.utils

import android.util.Base64
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.reflect.TypeToken

//fun decodeJwt(jwt: String): Map<String, Any>? {
//    return try {
//        val parts = jwt.split(".")
//        val payload = parts[1]
//        val decodedBytes = Base64.decode(payload, Base64.URL_SAFE)
//        val json = String(decodedBytes)
//        Gson().fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
//    } catch (e: Exception) {
//        e.printStackTrace()
//        null
//    }
//
//}

fun decodeJwt(jwt: String): Map<String, Any>? {
    return try {
        val parts = jwt.split(".")
        val payload = parts[1]
        val decodedBytes = Base64.decode(payload, Base64.URL_SAFE)
        val json = String(decodedBytes)

        val gson = GsonBuilder()
            .registerTypeAdapter(
                Any::class.java,
                JsonDeserializer { jsonElement, _, _ ->
                    if (jsonElement.isJsonPrimitive) {
                        val prim = jsonElement.asJsonPrimitive
                        when {
                            prim.isNumber -> {
                                // Deteksi angka apakah integer atau double
                                val number = prim.asDouble
                                if (number % 1 == 0.0) number.toInt() else number
                            }
                            prim.isBoolean -> prim.asBoolean
                            prim.isString -> prim.asString
                            else -> null
                        }
                    } else jsonElement
                }
            )
            .create()

        gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
