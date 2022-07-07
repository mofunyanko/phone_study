package com.example.realtimedatabasepractice

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val firstName: String? = "",
    val lastName: String? = "",
    val email: String? = "",
    val usrName: String? = "")
