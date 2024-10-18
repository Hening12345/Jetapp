package com.example.jetapp.ui.screen.favorite

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetapp.R
import com.example.jetapp.data.source.Resource
import com.example.jetapp.data.source.local.entity.ProductEntity
import com.example.jetapp.ui.components.CardFavorite
import com.example.jetapp.ui.screen.main.MainViewModel
import com.example.jetapp.ui.theme.JetappTheme
import com.example.jetapp.ui.theme.plusJakarta
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    mainViewModel: MainViewModel = koinViewModel(),
) {
    val favoriteProducts by mainViewModel.favorite.observeAsState(Resource.Loading(true))

    LaunchedEffect(Unit) {
        mainViewModel.getFavoriteProducts()
    }

    Scaffold(
        modifier = Modifier
    ) {
        FavoriteScreenContent(
            modifier = Modifier.padding(it),
            favoriteProducts = favoriteProducts,
            mainViewModel = mainViewModel
        )
    }
}

@Composable
fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    favoriteProducts: Resource<List<ProductEntity>>?,
    mainViewModel: MainViewModel,
) {
    val activity = (LocalContext.current as? Activity)
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { activity?.onBackPressed() },
                modifier = modifier.padding(top = 5.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
            Text(
                text = "Keranjang",
                fontFamily = plusJakarta,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
                letterSpacing = (-3 * 20 / 100).sp,
                fontSize = 20.sp,
                modifier = modifier.padding(start = 15.dp)
            )
        }
        Divider(color = Color.Gray, thickness = 1.dp, modifier = modifier.padding(top = 10.dp))

        when (favoriteProducts) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = modifier.padding(16.dp))
            }

            is Resource.Success -> {
                LazyColumn(
                    modifier = modifier.fillMaxHeight()
                ) {
                    items(favoriteProducts.data) { product ->
                        CardFavorite(
                            product = product,
                            removeFavorite = {
                                mainViewModel.removeFavorite(product, false)
                            }
                        )
                    }
                }
            }

            is Resource.Error -> {
                Text(
                    text = "Error: ${favoriteProducts.message}",
                    color = Color.Red,
                    modifier = modifier.padding(16.dp)
                )
            }

            else -> {}
        }
    }
}

