package com.example.jetapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetapp.R
import com.example.jetapp.ui.theme.JetappTheme
import com.example.jetapp.ui.theme.plusJakarta

@Composable
fun Banner(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.brown)
        )
    ) {
        Row(
            modifier = modifier
                .padding(start = 15.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier
                    .padding(vertical = 15.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Get your special\nsale up to 50%",
                    color = Color.Black,
                    fontFamily = plusJakarta,
                    letterSpacing = (-3 * 20 / 100).sp,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Enjoy, before it runs out",
                    color = colorResource(R.color.dark_brown),
                    fontFamily = plusJakarta,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    letterSpacing = (-3 * 20 / 100).sp,
                    modifier = modifier.padding(top = 5.dp)
                )
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    modifier = modifier.padding(top = 15.dp)
                ) {
                    Text(
                        text = "Shop Now",
                        fontFamily = plusJakarta,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.illustration),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .align(Alignment.Bottom)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BannerPreview() {
    JetappTheme {
        Banner()
    }
}

