package com.example.jetapp.ui.screen.favorite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class FavoriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FavoriteScreen()
        }
    }
}
