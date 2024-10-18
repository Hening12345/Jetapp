package com.example.jetapp.ui.components

import android.content.Intent
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetapp.R
import com.example.jetapp.ui.screen.favorite.FavoriteActivity
import com.example.jetapp.ui.theme.JetappTheme
import com.example.jetapp.ui.theme.plusJakarta

@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 25.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.menu),
            contentDescription = null,
            modifier = modifier
                .padding(10.dp)
                .size(25.dp)
        )

        Text(
            text = "Hot Summer",
            fontFamily = plusJakarta,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 18.sp
        )


        IconButton(onClick = { context.startActivity(Intent(context, FavoriteActivity::class.java)) }) {
            Icon(Icons.Outlined.ShoppingCart, contentDescription = null, modifier = modifier.size(25.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    JetappTheme {
        Header()
    }
}