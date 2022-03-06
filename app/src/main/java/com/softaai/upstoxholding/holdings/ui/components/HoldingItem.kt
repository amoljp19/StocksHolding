package com.softaai.upstoxholding.holdings.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.softaai.upstoxholding.data.model.Data
import com.softaai.upstoxholding.holdings.Utils

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HoldingItem(
    data: Data,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Card() {
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
                            text = data.symbol,
                            modifier = Modifier.fillMaxWidth(0.50F),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle1
                        )

                        Text(
                            text = "LTP : ",
                            modifier = Modifier.fillMaxWidth(0.20F),
                            style = MaterialTheme.typography.subtitle2
                        )

                        Text(
                            text = "\u20B9" + data.ltp,
                            modifier = Modifier.fillMaxWidth(0.50F),
                            fontWeight = FontWeight.Bold,
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
                            text = "" + data.quantity,
                            modifier = Modifier.fillMaxWidth(0.50F),
                            style = MaterialTheme.typography.subtitle1
                        )

                        Text(
                            text = "P/L : ",
                            modifier = Modifier.fillMaxWidth(0.20F),
                            style = MaterialTheme.typography.subtitle2
                        )

                        Text(
                            text = "\u20B9" + Utils.roundOffDecimal(
                                getIndividualItemProfitAndLoss(
                                    data = data
                                )
                            ),
                            modifier = Modifier.fillMaxWidth(0.60F),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                }

            }
        }
    }
}

fun getIndividualItemCurrentValue(ltp: Double, quantity: Int): Double = ltp * quantity

fun getIndividualItemInvestmentValue(avgPrice: String, quantity: Int): Double =
    avgPrice.toDouble() - quantity

fun getIndividualItemProfitAndLoss(data: Data): Double =
    getIndividualItemCurrentValue(data.ltp, data.quantity) -
            getIndividualItemInvestmentValue(data.avgPrice, data.quantity)


