package com.example.jetapp.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jetapp.R
import com.example.jetapp.domain.model.ProductItem
import com.example.jetapp.ui.screen.detail.DetailActivity
import com.example.jetapp.ui.screen.detail.DetailScreen
import com.example.jetapp.ui.theme.JetappTheme
import com.example.jetapp.ui.theme.plusJakarta
import com.example.jetapp.utils.DataMapper.capitalizeWords

@Composable
fun ItemProduct(
    product: ProductItem,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .width(220.dp)
            .padding(15.dp)
            .clickable {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("id", product.id)
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white)
        )
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.image),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = modifier.padding(start = 10.dp, bottom = 10.dp, top = 10.dp)
        ) {
            Text(
                text = product.title!!.capitalizeWords(),
                fontFamily = plusJakarta,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                letterSpacing = (-3 * 20 / 100).sp,
                fontSize = 14.sp
            )
            Text(
                text = product.category!!.capitalizeWords(),
                fontFamily = plusJakarta,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = modifier.padding(top = 5.dp),
                letterSpacing = (-3 * 20 / 100).sp,
                fontSize = 12.sp
            )
            Text(
                text = "$${product.price}",
                fontFamily = plusJakarta,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.blue),
                modifier = modifier.padding(top = 20.dp),
                letterSpacing = (-3 * 20 / 100).sp,
                fontSize = 14.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ItemProductReview() {
    JetappTheme {
        val dummyProduct = ProductItem(
            title = "Skeleton Evory Shirt",
            category = "Men's Clothing",
            price = "$352",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg" // Replace with your image URL
        )
        ItemProduct(product = dummyProduct)
    }
}