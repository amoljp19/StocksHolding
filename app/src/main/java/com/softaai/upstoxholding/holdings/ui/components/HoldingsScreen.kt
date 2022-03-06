package com.softaai.upstoxholding.holdings.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.softaai.upstoxholding.R
import com.softaai.upstoxholding.data.model.Data
import com.softaai.upstoxholding.data.model.HoldingsApiResponse
import com.softaai.upstoxholding.data.remote.State
import com.softaai.upstoxholding.holdings.Utils
import com.softaai.upstoxholding.holdings.viewmodel.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


@Composable
fun HoldingsScreen(
    mViewModel: MainViewModel
) {

    val state = mViewModel.holdings.value

    LazyColumn(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        state?.let {
//                itemsIndexed(
//                    items = it,
//                    itemContent = { _, data ->
//                            HoldingItem(
//                                data,
//                                modifier = Modifier
//                                    .fillMaxSize()
//                            )
//                    }
//                )

            val list = it
            items(list.size) { index ->
                HoldingItem(
                    data = list[index],
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            item {
                Divider()
                ProfileSummeryFooter(
                    list,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileSummeryFooter(
    list : List<Data>,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Card(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
                onClick = { expanded = !expanded }) {
                Text(
                    text = "Click here to see detail summery !",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp, vertical = 50.dp),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )
            }
            AnimatedVisibility(
                visible = expanded,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Current Value : ",
                            modifier = Modifier.fillMaxWidth(0.50F),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle1
                        )

                        Text(
                            text = "\u20B9" + Utils.roundOffDecimal(getCurrentValue(list = list)),
                            modifier = Modifier.fillMaxWidth(0.50F),
                            style = MaterialTheme.typography.subtitle2
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total Investment : ",
                            modifier = Modifier.fillMaxWidth(0.50F),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle1
                        )

                        Text(
                            text = "\u20B9" + Utils.roundOffDecimal(getTotalInvestment(list = list)),
                            modifier = Modifier.fillMaxWidth(0.50F),
                            style = MaterialTheme.typography.subtitle2
                        )
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Today Profit & Loss : ",
                            modifier = Modifier.fillMaxWidth(0.50F),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle1
                        )

                        Text(
                            text = "\u20B9" + Utils.roundOffDecimal(getTodaysProfitAndLoss(list = list)),
                            modifier = Modifier.fillMaxWidth(0.50F),
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                    Divider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Profit & Loss : ",
                            modifier = Modifier.fillMaxWidth(0.50F),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle1
                        )

                        Text(
                            text = "\u20B9" + Utils.roundOffDecimal(getTotalProfitAndLoss(list = list)),
                            modifier = Modifier.fillMaxWidth(0.50F),
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                }
            }
        }
    }
}

fun getCurrentValue(list: List<Data>) : Double {
    var currentValue = 0.0
    for (holding in list) {
        currentValue += holding.ltp * holding.quantity
    }
    return currentValue
}

fun getTotalInvestment(list: List<Data>) : Double {
    var totalInvestment = 0.0
    for (holding in list) {
        totalInvestment += holding.avgPrice.toDouble() - holding.quantity
    }
    return totalInvestment
}

fun getTotalProfitAndLoss(list: List<Data>) : Double = getCurrentValue(list) - getTotalInvestment(list)

fun getTodaysProfitAndLoss(list: List<Data>) : Double {
    var todaysProfitAndLoss = 0.0

    for(holding in list){
        todaysProfitAndLoss += (holding.close - holding.ltp) * holding.quantity
    }

    return todaysProfitAndLoss
}
