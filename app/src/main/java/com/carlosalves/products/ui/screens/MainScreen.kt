package com.carlosalves.products.ui.screens

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosalves.products.BuildConfig
import com.carlosalves.products.ProductsActivity
import com.carlosalves.products.R

@Composable
fun MainScreen() {
    val activity = LocalActivity.current as ProductsActivity
    val viewModel = activity.viewModel

    val isForm = BuildConfig.FORM_MODE_ENABLED

    val isDbEmpty by viewModel.dbIsEmpty.collectAsState()
    viewModel.checkForProductsInDatabase()

    LaunchedEffect(isDbEmpty) {
        if (isDbEmpty) {
            viewModel.fetchProducts()
        } else {
            viewModel.loadProducts()
        }
    }

    viewModel.checkForProductsInDatabase()
    viewModel.loadProducts()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {

        Image(
            painter = painterResource(id = R.drawable.via_verde_logo),
            contentDescription = stringResource(R.string.product_image),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .heightIn(min = 180.dp, max = 300.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            if (!isForm) {
                Button(
                    onClick = {
                        if (isDbEmpty) {
                            Toast.makeText(activity, activity.baseContext.getText(R.string.list_unavailable), Toast.LENGTH_LONG).show()
                        } else {
                            activity.navigator.navigateToProductsList()
                        }
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .padding(horizontal = 20.dp),
                ) {
                    Text(text = stringResource(R.string.browse_products),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp)
                }
            }
            if (isForm) {
                Button(
                    onClick = { activity.navigator.navigateToForm() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .padding(horizontal = 20.dp),
                ) {
                    Text(text = stringResource(R.string.fill_form),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp)
                }
            }
            Button(
                onClick = { activity.finishAffinity() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 40.dp),
            ) {
                Text(text = stringResource(R.string.exit),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp)
            }
        }
    }
}