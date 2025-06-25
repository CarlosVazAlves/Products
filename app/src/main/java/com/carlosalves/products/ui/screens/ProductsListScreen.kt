package com.carlosalves.products.ui.screens

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosalves.products.ProductsActivity
import com.carlosalves.products.R
import com.carlosalves.products.repository.ProductItem
import java.text.Normalizer

@Composable
fun ProductsListScreen() {
    val activity = LocalActivity.current as ProductsActivity
    var inputText by remember { mutableStateOf("") }

    val filteredProductsList = filterProductList(activity.viewModel.getProductsList(), inputText)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 15.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 25.sp
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
            ) {
                items(filteredProductsList) { item ->
                    ProductCard(item = item) {
                        activity.navigator.navigateToProductDetail(item.id)
                    }
                }
            }

            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                label = { Text(stringResource(R.string.filter_by)) },
                singleLine = true
            )

            Button(
                onClick = { activity.navigator.navigateBack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
            ) {
                Text(
                    text = stringResource(R.string.back),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}

fun filterProductList(productList: List<ProductItem>, filters: String): List<ProductItem> {
    if (filters.isBlank()) return productList

    var filteredList = productList
    val filtersSplit = filters.split(" ")
    for (filter in filtersSplit) {
        val normalizedFilter = normalizeText(filter)
        filteredList = filteredList.filter {
            normalizeText(it.title).contains(normalizedFilter) ||
                    normalizeText(it.description).contains(normalizedFilter)
        }
    }
    return filteredList.toList()
}

fun normalizeText(originalText: String): String {
    return Normalizer.normalize(originalText, Normalizer.Form.NFD).lowercase()
}

@Composable
fun ProductCard(item: ProductItem, navigateToItem: () -> Unit) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable { navigateToItem() },
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)) {

            Text(
                text = item.title,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = String.format("%s: ${item.rating}", stringResource(R.string.rating)),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                painter = painterResource(id = getIconId(item.rating)),
                contentDescription = stringResource(R.string.rating_icon),
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

fun getIconId(rating: Double): Int {
    return if (rating < 3)
        R.drawable.bad
    else if (rating >= 3 && rating < 4) {
        R.drawable.neutral
    }
    else {
        R.drawable.good
    }
}
