package com.example.medicmadq.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicmadq.ui.theme.primaryColor
import com.example.medicmadq.ui.theme.primaryDisabledColor

/*
Описание: Кнопка приложения
Дата создания: 26.03.2023 14:02
Автор: Георгий Хасанов
 */
@Composable
fun AppButton(
    modifier: Modifier,
    text: String,
    color: Color = Color.White,
    fontSize: TextUnit = 17.sp,
    fontWeight: FontWeight = FontWeight.W600,
    colors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = primaryColor, disabledBackgroundColor = primaryDisabledColor),
    contentPadding: PaddingValues = PaddingValues(16.dp),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = colors,
        contentPadding = contentPadding,
        elevation = ButtonDefaults.elevation(0.dp),
        onClick = onClick,
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = color
        )
    }
}