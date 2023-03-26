package com.example.medicmadq.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicmadq.common.ApiService
import kotlinx.coroutines.launch

/*
Описание: Класс логики экранов входа в аккаунт
Дата создания: 26.03.2023 14:13
Автор: Георгий Хасанов
*/
class LoginViewModel: ViewModel() {
    val responseCode = MutableLiveData<Int>()
    val token = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()

    /*
    Описание: Метод отправки кода на почту
    Дата создания: 26.03.2023 14:15
    Автор: Георгий Хасанов
    */
    fun sendCode(email: String) {
        responseCode.value = null
        token.value = null
        errorMessage.value = null

        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance()

                val json = apiService.sendCode(email)

                if (json.code() == 200) {
                    responseCode.value = 200
                } else {
                    responseCode.value = json.code()
                    errorMessage.value = json.code().toString()
                }
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }

    /*
    Описание: Метод проверки кода из почты
    Дата создания: 26.03.2023 14:16
    Автор: Георгий Хасанов
    */
    fun checkCode(email: String, code: String) {
        responseCode.value = null
        token.value = null
        errorMessage.value = null

        viewModelScope.launch {
            try {
                val apiService = ApiService.getInstance()

                val json = apiService.checkCode(email, code)

                if (json.code() == 200) {
                    token.value = json.body()!!.get("token").toString()
                } else {
                    errorMessage.value = json.code().toString()
                }
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }
}