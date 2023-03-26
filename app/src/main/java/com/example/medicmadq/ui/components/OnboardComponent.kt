package com.example.medicmadq.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicmadq.ui.theme.descriptionColor
import com.example.medicmadq.ui.theme.titleColor

/*
Описание: Компонент приветственного
Дата создания: 26.03.2023 13:35
Автор: Георгий Хасанов
 */
@Composable
fun OnboardComponent(
    title: String,
    description: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 220.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = titleColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(29.dp))
        Text(
            text = description,
            color = descriptionColor,
            textAlign = TextAlign.Center
        )
    }
}