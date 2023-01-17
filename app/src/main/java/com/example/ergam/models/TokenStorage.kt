package com.example.ergam.models

data class TokensModel(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String
)