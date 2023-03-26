package com.example.medicmadq.common

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

/*
Описание: Интерфейс класса свзяи с сервером
Дата создания: 26.03.2023 14:08
Автор: Георгий Хасанов
 */
interface ApiService {

    /*
    Описание: Функция отправки кода на почту
    Дата создания: 26.03.2023 14:12
    Автор: Георгий Хасанов
     */
    @Headers(
        "accept: application/json"
    )
    @POST("sendCode")
    suspend fun sendCode(@Header("email") email: String): Response<JsonObject>

    /*
    Описание: Функция проверки кода из почты
    Дата создания: 26.03.2023 14:12
    Автор: Георгий Хасанов
     */
    @Headers(
        "accept: application/json"
    )
    @POST("signin")
    suspend fun checkCode(@Header("email") email: String, @Header("code") code: String): Response<JsonObject>

    companion object {
        var apiService: ApiService? = null

        /*
        Описание: Метод создания класса связи с сервером
        Дата создания: 26.03.2023 14:10
        Автор: Георгий Хасанов
         */
        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://medic.madskill.ru/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }

            return apiService!!
        }
    }
}