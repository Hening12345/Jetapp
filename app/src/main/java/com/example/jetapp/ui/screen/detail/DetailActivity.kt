package com.example.jetapp.ui.screen.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetapp.ui.theme.JetappTheme


class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent?.getIntExtra("id", 0)
        setContent {
           JetappTheme {
               DetailScreen(productId = id!!)
           }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailActivityPreview() {
    JetappTheme {
        DetailActivity()
    }
}


