package com.softaai.upstoxholding.holdings.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softaai.upstoxholding.data.remote.State
import com.softaai.upstoxholding.holdings.ui.theme.UpstoxHoldingTheme
import com.softaai.upstoxholding.holdings.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpstoxHoldingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }

                getHoldingsList()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        observeHoldings()
    }

    private fun getHoldingsList() = mViewModel.getHoldingsList()

    private fun observeHoldings() {
        mViewModel.holdingsLiveData.observe(this) { state ->
            when (state) {
                is State.Loading -> Toast.makeText(
                    applicationContext,
                    "Loading...",
                    Toast.LENGTH_SHORT
                ).show()
                is State.Success -> {
                    Toast.makeText(applicationContext, " " + state.data, Toast.LENGTH_SHORT).show()
                }
                is State.Error -> {
                    Toast.makeText(applicationContext, " " + state.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HoldingsExpandedList() {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Card(onClick = { expanded = !expanded }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "symbol",
                        modifier = Modifier.fillMaxWidth(0.50F),
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "LTP : ",
                        modifier = Modifier.fillMaxWidth(0.30F),
                        style = MaterialTheme.typography.subtitle2
                    )

                    Text(
                        text = "100",
                        modifier = Modifier.fillMaxWidth(0.30F),
                        style = MaterialTheme.typography.subtitle2
                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "SYMBOL",
                        modifier = Modifier.fillMaxWidth(0.50F),
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "LTP : ",
                        modifier = Modifier.fillMaxWidth(0.30F),
                        style = MaterialTheme.typography.h6
                    )

                    Text(
                        text = "100",
                        modifier = Modifier.fillMaxWidth(0.30F),
                        style = MaterialTheme.typography.h6
                    )
                }
            }

        }
        Divider()
        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Current Value : ",
                        modifier = Modifier.fillMaxWidth(0.40F),
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "1000",
                        modifier = Modifier.fillMaxWidth(0.30F),
                        style = MaterialTheme.typography.h6
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total Investment : ",
                        modifier = Modifier.fillMaxWidth(0.40F),
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "3000",
                        modifier = Modifier.fillMaxWidth(0.30F),
                        style = MaterialTheme.typography.h6
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Today Profit & Loss : ",
                        modifier = Modifier.fillMaxWidth(0.40F),
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "9000",
                        modifier = Modifier.fillMaxWidth(0.30F),
                        style = MaterialTheme.typography.h6
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Profit & Loss : ",
                        modifier = Modifier.fillMaxWidth(0.40F),
                        style = MaterialTheme.typography.subtitle1
                    )

                    Text(
                        text = "4000",
                        modifier = Modifier.fillMaxWidth(0.30F),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@OptIn(ExperimentalAnimationApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UpstoxHoldingTheme {
        //Greeting("Android")
        HoldingsExpandedList()
    }
}