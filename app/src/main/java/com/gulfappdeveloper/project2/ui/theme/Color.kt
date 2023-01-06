package com.gulfappdeveloper.project2.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF0097A7)
val LightGreen = Color(0xFF62B43A)
val LightYellowGreen = Color(0xFFB4B42F)
val LightPink = Color(0xFFBB4C71)
val LightBlue = Color(0xFF3558C1)
val Orange = Color(0xFFF57C00)

val Colors.Color1
    @Composable
    get() = if (isLight) Teal200 else Teal200

val Colors.Color2
    @Composable
    get() = if (isLight) LightGreen else LightGreen

val Colors.Color3
    @Composable
    get() = if (isLight) LightYellowGreen else LightYellowGreen

val Colors.Color4
    @Composable
    get() = if (isLight) LightPink else LightPink

val Colors.Color5
    @Composable
    get() = if (isLight) LightBlue else LightBlue

val Colors.OrangeColor
    @Composable
    get() = if (isLight) Orange else Orange

