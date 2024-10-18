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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jetapp.R
import com.example.jetapp.data.source.local.entity.ProductEntity
import com.example.jetapp.ui.theme.JetappTheme
import com.example.jetapp.ui.theme.plusJakarta
import com.example.jetapp.utils.DataMapper.capitalizeWords

@Composable
fun CardFavorite(
    modifier: Modifier = Modifier,
    product: ProductEntity,
    removeFavorite: (ProductEntity) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = modifier
                .padding(start = 15.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Spacer(modifier = modifier.width(20.dp))
            Column(
                modifier = modifier
                    .padding(vertical = 10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = product.title.capitalizeWords(),
                    color = Color.Gray,
                    fontFamily = plusJakarta,
                    letterSpacing = (-3 * 20 / 100).sp,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    maxLines = 1,
                    modifier = modifier.padding(end = 15.dp)
                )
                Text(
                    text = "$${product.price}",
                    color = colorResource(R.color.black),
                    fontFamily = plusJakarta,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = (-3 * 20 / 100).sp,
                    modifier = modifier.padding(vertical = 10.dp)
                )
                Text(
                    text = product.category.capitalizeWords(),
                    color = Color.Gray,
                    fontFamily = plusJakarta,
                    letterSpacing = (-3 * 20 / 100).sp,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
            IconButton(
                onClick = { removeFavorite(product) },
                modifier = modifier
                    .padding(top = 5.dp)
                    .align(Alignment.Bottom)
            ) {
                Icon(Icons.Outlined.Delete, contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardFavoritePreview() {
    JetappTheme {
        CardFavorite(
            product = ProductEntity(
                id = 1,
                title = "Skeleton Evory Shirt",
                category = "Men's Clothing",
                image = "https://via.placeholder.com/150",
                price = 900.00,
                isFavorite = true
            ),
            removeFavorite = {}
        )
    }
}
