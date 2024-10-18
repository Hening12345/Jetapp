package com.example.jetapp.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetapp.data.source.Resource
import com.example.jetapp.domain.model.ProductItem
import com.example.jetapp.ui.components.Banner
import com.example.jetapp.ui.components.Header
import com.example.jetapp.ui.components.ItemProduct
import com.example.jetapp.ui.theme.JetappTheme
import com.example.jetapp.ui.theme.plusJakarta
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel()
) {
    val productListState by mainViewModel.list.observeAsState()
    Scaffold(
        modifier = Modifier
    ) {
        MainScreenContent(
            modifier = Modifier.padding(it),
            product = productListState,
            onRefresh = { mainViewModel.getListProduct() },
        )
    }
    LaunchedEffect(Unit) {
        mainViewModel.getListProduct()
    }
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    product: Resource<List<ProductItem>>?,
    onRefresh: () -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxHeight(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Header()
        }
        item(span = { GridItemSpan(2) }) {
            Banner()
        }
        item(span = { GridItemSpan(2) }) {
            Text(
                modifier = Modifier.padding(start = 25.dp, top = 25.dp),
                text = "List Product",
                fontFamily = plusJakarta,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        when (product) {
            is Resource.Loading -> {
                item(span = { GridItemSpan(2) }) {
                    CircularProgressIndicator(modifier = Modifier.fillMaxWidth().wrapContentSize())
                }
            }
            is Resource.Success -> {
                items(product.data) { productItem ->
                    ItemProduct(
                        product = productItem,)
                }
            }
            is Resource.Error -> {
                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = "Error: ${product.message}",
                        color = Color.Red,
                        modifier = Modifier.fillMaxWidth().wrapContentSize()
                    )
                }
            }

            else -> {}
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    JetappTheme {
        MainScreen()
    }
}
