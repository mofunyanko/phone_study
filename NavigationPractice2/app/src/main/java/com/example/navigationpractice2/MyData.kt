package com.example.navigationpractice2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MyData (
    val content: String,
    val value: Int,
    val message: String
): Parcelable