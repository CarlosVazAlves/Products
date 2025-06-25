package com.carlosalves.products.ui.screens

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.carlosalves.products.ProductsActivity
import com.carlosalves.products.R
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen() {
    val activity = LocalActivity.current as ProductsActivity

    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var promotionalCode by remember { mutableStateOf("") }
    var deliveryDate by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val ratingList = listOf(
        R.string.bad,
        R.string.satisfactory,
        R.string.good,
        R.string.very_good,
        R.string.excellent
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(8.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text(stringResource(R.string.name)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            OutlinedTextField(
                value = number,
                onValueChange = { number = it.filter { c -> c.isDigit() } },
                label = { Text(stringResource(R.string.number)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = promotionalCode,
                onValueChange = {
                    promotionalCode = it.uppercase().filter { character -> character.isLetter() || character == '-' }
                },
                label = { Text(stringResource(R.string.promotional_code)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii)
            )

            OutlinedTextField(
                value = deliveryDate,
                onValueChange = { deliveryDate = it },
                label = { Text(stringResource(R.string.delivery_date)) },
                placeholder = { Text(stringResource(R.string.date_format)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = rating,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(R.string.rating)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    ratingList.forEach { resId ->
                        val ratingString = stringResource(resId)
                        DropdownMenuItem(
                            text = { Text(ratingString) },
                            onClick = {
                                rating = ratingString
                                expanded = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    val messageId = validateInputs(userName, email, number, promotionalCode, deliveryDate, rating)
                    Toast.makeText(activity, activity.baseContext.getText(messageId), Toast.LENGTH_LONG).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(stringResource(R.string.submit))
            }
        }

        Button(
            onClick = { activity.navigator.navigateBack() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.back))
        }
    }
}

fun validateInputs(userName: String, email: String, number: String, promotionalCode: String, deliveryDate: String, rating: String): Int {
    if (!validUserName(userName)) return R.string.invalid_name
    if (!validEmail(email)) return R.string.invalid_email
    if (!validNumber(number)) return R.string.invalid_number
    if (!validPromotionalCode(promotionalCode)) return R.string.invalid_promotional_code
    if (!validDeliveryDate(deliveryDate)) return R.string.invalid_date
    if (!validRating(rating)) return R.string.invalid_rating
    return R.string.correct_data
}

fun validUserName(userName: String): Boolean {
    return userName.isNotBlank()
}

fun validEmail(email: String): Boolean {
    if (email.isBlank()) return false
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(emailRegex)
}

fun validNumber(number: String): Boolean {
    if (number.isBlank()) return false
    return number.toIntOrNull() != null
}

fun validPromotionalCode(promotionalCode: String): Boolean {
    if (promotionalCode.isBlank()) return false
    return (promotionalCode.length >= 3 && promotionalCode.length <= 7)
}

fun validDeliveryDate(deliveryDate: String): Boolean {
    if (deliveryDate.isBlank()) return false
    val date = parseDate(deliveryDate)
    return if (date != null) {
        (date.dayOfWeek != DayOfWeek.MONDAY) && date.isBefore(LocalDate.now())
    } else {
        false
    }
}

fun validRating(rating: String): Boolean {
    return rating.isNotBlank()
}

fun parseDate(dateString: String): LocalDate? {
    val pattern = "dd/MM/yyyy"
    return try {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        LocalDate.parse(dateString, formatter)
    } catch (exception: DateTimeParseException) {
        null
    }
}