package com.softaai.upstoxholding.holdings.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.softaai.upstoxholding.data.model.Data

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HoldingItem(
    data: Data,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier){
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
                              text = "\u20B9" +data.avgPrice,
                              modifier = Modifier.fillMaxWidth(0.50F),
                              fontWeight = FontWeight.Bold,
                              style = MaterialTheme.typography.subtitle2
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
                          .background(Color.White)
                          .padding(16.dp)
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
                              text = "\u20B9" +"1000",
                              modifier = Modifier.fillMaxWidth(0.50F),
                              style = MaterialTheme.typography.h6
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
                              text = "\u20B9" +"3000",
                              modifier = Modifier.fillMaxWidth(0.50F),
                              style = MaterialTheme.typography.h6
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
                              text = "\u20B9" +"9000",
                              modifier = Modifier.fillMaxWidth(0.50F),
                              style = MaterialTheme.typography.h6
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
                              text = "\u20B9" +"4000",
                              modifier = Modifier.fillMaxWidth(0.50F),
                              style = MaterialTheme.typography.h6
                          )
                      }
                  }
              }
          }
      }
}