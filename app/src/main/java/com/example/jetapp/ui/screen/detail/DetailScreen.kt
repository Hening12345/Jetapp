package com.example.jetapp.ui.screen.detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jetapp.R
import com.example.jetapp.data.source.Resource
import com.example.jetapp.data.source.local.entity.ProductEntity
import com.example.jetapp.domain.model.ProductItem
import com.example.jetapp.ui.screen.main.MainViewModel
import com.example.jetapp.ui.theme.plusJakarta
import com.example.jetapp.utils.DataMapper.capitalizeWords
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    mainViewModel: MainViewModel = koinViewModel(),
    productId: Int
) {
    val productDetail by mainViewModel.detail.observeAsState()
    val favoriteProducts by mainViewModel.favorite.observeAsState(Resource.Loading(true))

    LaunchedEffect(productId) {
        mainViewModel.getDetailProduct(productId)
    }

    Scaffold(
        modifier = Modifier
    ) {
        DetailScreenContent(
            modifier = Modifier.padding(it),
            productDetail = productDetail,
            mainViewModel = mainViewModel,
        )
    }
}

@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    productDetail: Resource<ProductItem>?,
    mainViewModel: MainViewModel,
) {
    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        val context = LocalContext.current
        productDetail?.let {
            when (it) {
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {
                    Image(
                        painter = rememberAsyncImagePainter(it.data.image),
                        contentDescription = null,
                        modifier = modifier
                            .height(250.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "$${it.data.price}",
                        fontFamily = plusJakarta,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.blue),
                        letterSpacing = (-3 * 20 / 100).sp,
                        fontSize = 20.sp,
                        modifier = modifier.padding(top = 15.dp, start = 15.dp)
                    )

                    Text(
                        text = it.data.title!!.capitalizeWords(),
                        fontFamily = plusJakarta,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.black),
                        letterSpacing = (-3 * 20 / 100).sp,
                        fontSize = 20.sp,
                        modifier = modifier.padding(15.dp)
                    )

                    Text(
                        text = it.data.category!!.capitalizeWords(),
                        fontFamily = plusJakarta,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        modifier = modifier.padding(start = 15.dp),
                        letterSpacing = (-3 * 20 / 100).sp,
                        fontSize = 16.sp
                    )

                    Text(
                        text = it.data.description!!.capitalizeWords(),
                        fontFamily = plusJakarta,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = modifier.padding(top = 30.dp, start = 15.dp, end = 25.dp),
                        letterSpacing = (-3 * 20 / 100).sp,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            modifier = modifier
                                .weight(1f)
                                .padding(bottom = 16.dp, start = 15.dp),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, colorResource(R.color.blue)),
                        ) {
                            Text(
                                text = "Beli",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.blue),
                                letterSpacing = (-3 * 20 / 100).sp,
                                fontSize = 16.sp,
                                modifier = modifier.padding(vertical = 5.dp, horizontal = 20.dp)
                            )
                        }

                        Button(
                            onClick = {
                                val entity = ProductEntity(
                                    title = it.data.title,
                                    category = it.data.category,
                                    image = it.data.image,
                                    price = it.data.price as Double?,
                                    isFavorite = true
                                )
                                mainViewModel.addToFavorite(entity, true)
                                Toast.makeText(context, "Items Added To Chart!", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.blue)
                            ),
                            modifier = modifier
                                .weight(1f)
                                .padding(bottom = 16.dp, end = 15.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "Keranjang",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.white),
                                letterSpacing = (-3 * 20 / 100).sp,
                                fontSize = 16.sp,
                                modifier = modifier.padding(vertical = 5.dp, horizontal = 20.dp)
                            )
                        }
                    }
                }

                is Resource.Error -> {
                    Text(
                        text = "Error: ${it.message}",
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}
