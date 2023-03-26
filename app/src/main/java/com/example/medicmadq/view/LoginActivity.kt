package com.example.medicmadq.view

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.medicmadq.ui.theme.MedicMADQTheme
import com.example.medicmadq.R
import com.example.medicmadq.ui.components.AppTextField
import com.example.medicmadq.ui.theme.textColor
import com.example.medicmadq.viewmodel.LoginViewModel

/*
Описание: Класс экрана входа в аккаунт
Дата создания: 26.03.2023 13:25
Автор: Георгий Хасанов
 */
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicMADQTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenContent()
                }
            }
        }
    }

    /*
    Описание: Контент экрана входа в аккаунт
    Дата создания: 26.03.2023 13:25
    Автор: Георгий Хасанов
     */
    @Composable
    fun ScreenContent() {
        val mContext = LocalContext.current
        val shared = this.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        var emailText by rememberSaveable { mutableStateOf("") }

        var enabled by rememberSaveable { mutableStateOf(false) }

        var isAlertVisible by rememberSaveable { mutableStateOf(false) }
        var isLoading by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_hello),
                    contentDescription = "",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.widthIn(16.dp))
                Text(
                    text = "Добро пожаловать!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Войдите, чтобы пользоваться функциями приложения",
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(61.dp))
            Text(
                text = "Вход по E-mail",
                fontSize = 14.sp,
                color = textColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            AppTextField(
                value =emailText,
                onValueChange = {
                    emailText = it

                    enabled = emailText.isNotEmpty()
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        if (isAlertVisible) {
            AlertDialog(
                onDismissRequest = { isAlertVisible = false },
                title = {
                    Text(text = "Ошибка!")
                },
                text = {
                    Text(text = viewModel.errorMessage.value.toString())
                },
                buttons = {
                    TextButton(onClick = { isAlertVisible = false }) {
                        Text(text = "OK")
                    }
                }
            )
        }
        if (isLoading) {
            Dialog(onDismissRequest = { isLoading = false }) {
                CircularProgressIndicator()
            }
        }

    }
}