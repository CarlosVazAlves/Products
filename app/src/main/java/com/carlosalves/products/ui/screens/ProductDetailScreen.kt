package com.carlosalves.products.ui.screens

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.carlosalves.products.ProductsActivity
import com.carlosalves.products.R

@Composable
fun ProductDetailScreen(productId: Int) {
    val activity = LocalActivity.current as ProductsActivity
    val product = activity.viewModel.getProduct(productId)

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(product.imageURL)
            .crossfade(true)
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .build()
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painter,
                contentDescription = stringResource(R.string.product_image),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 180.dp, max = 300.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(
                text = product.title,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "€${"%.2f".format(product.price)}",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Black

            )

            Text(
                text = String.format("%s: -${product.discountPercentage}%%", stringResource(R.string.discount)),
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 16.sp,
            )

            Text(
                text = String.format("%s: ${product.stock}", stringResource(R.string.stock)),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 10.dp),
                color = if (product.stock > 0) Color.DarkGray else Color.Red
            )

            Text(
                text = String.format("%s: ${product.rating}", stringResource(R.string.rating)),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(80.dp))
        }

        Button(
            onClick = { activity.navigator.navigateBack() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(30.dp)
                .navigationBarsPadding()
        ) {
            Text(stringResource(R.string.back))
        }
    }
}