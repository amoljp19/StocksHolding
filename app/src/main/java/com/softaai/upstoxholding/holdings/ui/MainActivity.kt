package com.softaai.upstoxholding.holdings.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.softaai.upstoxholding.data.remote.State
import com.softaai.upstoxholding.holdings.ui.components.HoldingsScreen
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
                    //Greeting("Android")
                    HoldingsScreen(mViewModel)
                }

                getHoldingsList()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        observeHoldingsLiveData()
    }

    private fun getHoldingsList() = mViewModel.getHoldingsList()

    private fun observeHoldingsLiveData() {

        mViewModel.holdingsLiveData.observe(this) { state ->
            when (state) {
                is State.Loading -> Toast.makeText(
                    applicationContext,
                    "Loading...",
                    Toast.LENGTH_SHORT
                ).show()
                is State.Success -> {
                    //Toast.makeText(applicationContext, " " + state.data, Toast.LENGTH_SHORT).show()
                    mViewModel.setHoldings(state.data.listData)
                    setContent {
                        HoldingsScreen(mViewModel = mViewModel)
                    }
                }
                is State.Error -> {
                    Toast.makeText(applicationContext, " " + state.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UpstoxHoldingTheme {
        Greeting("Android")
    }
}