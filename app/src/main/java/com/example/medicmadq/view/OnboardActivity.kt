package com.example.medicmadq.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicmadq.R
import com.example.medicmadq.ui.components.OnboardComponent
import com.example.medicmadq.ui.theme.MedicMADQTheme
import com.example.medicmadq.ui.theme.secondaryColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collect

/*
Описание: Класс приветственного экрана
Дата создания: 26.03.2023 13:25
Автор: Георгий Хасанов
 */
class OnboardActivity : ComponentActivity() {
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
    Описание: Контент приветственного экрана
    Дата создания: 26.03.2023 13:25
    Автор: Георгий Хасанов
     */
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun ScreenContent() {
        val mContext = LocalContext.current
        val shared = this.getSharedPreferences("shared", Context.MODE_PRIVATE)

        var buttonText by rememberSaveable() { mutableStateOf("Пропустить") }

        val onboardImages = listOf(
            R.drawable.onboard_image_1,
            R.drawable.onboard_image_2,
            R.drawable.onboard_image_3
        )

        val pagerState = rememberPagerState()
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect() {
                buttonText = if (pagerState.currentPage != 2) {
                    "Пропустить"
                } else {
                    "Завершить"
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 30.dp)
            ) {
                Text(
                    text = buttonText,
                    color = secondaryColor,
                    fontWeight = FontWeight.W600,
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        with(shared.edit()) {
                            putBoolean("isFirstEnter", false)
                            apply()
                        }

                        val intent = Intent(mContext, LoginActivity::class.java)
                        startActivity(intent)
                    }
                )
                Image(
                    painter = painterResource(id = R.drawable.onboard_logo),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            HorizontalPager(
                count = 3,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) {
                when (it) {
                    0 -> {
                        OnboardComponent(
                            title = "Анализы",
                            description = "Экспресс сбор и получение проб"
                        )
                    }
                    1 -> {
                        OnboardComponent(
                            title = "Уведомления",
                            description = "Вы быстро узнаете о результатах"
                        )
                    }
                    2 -> {
                        OnboardComponent(
                            title = "Мониторинг",
                            description = "Наши врачи всегда наблюдают за вашими показателями здоровья"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (i in 0..2) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(13.dp)
                            .clip(CircleShape)
                            .background(if (pagerState.currentPage != i) Color.White else secondaryColor)
                            .border(1.dp, secondaryColor, CircleShape)
                    )
                }
            }

            Image(
                painter = painterResource(id = onboardImages[pagerState.currentPage]),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(270.dp)
                    .fillMaxSize()
                    .padding(bottom = 85.dp)
            )
        }
    }
}