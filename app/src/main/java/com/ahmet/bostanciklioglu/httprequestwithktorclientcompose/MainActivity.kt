package com.ahmet.bostanciklioglu.httprequestwithktorclientcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmet.bostanciklioglu.httprequestwithktorclientcompose.data.remote.ApiService
import com.ahmet.bostanciklioglu.httprequestwithktorclientcompose.data.remote.dto.ResponseModel
import com.ahmet.bostanciklioglu.httprequestwithktorclientcompose.ui.theme.HTTPRequestWithKtorClientComposeTheme

class MainActivity : ComponentActivity() {

    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HTTPRequestWithKtorClientComposeTheme {
                val products = produceState(
                    initialValue = emptyList<ResponseModel>(),
                    producer = {
                        value = apiService.getProducts()
                    }
                )
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn {
                        items(products.value) {

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                elevation = CardDefaults.cardElevation(4.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(text = it.title, fontSize = 20.sp, color = Color.Red, textAlign = TextAlign.Start)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = it.body, fontSize = 14.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

