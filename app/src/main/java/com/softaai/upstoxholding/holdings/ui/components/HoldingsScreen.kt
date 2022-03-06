package com.softaai.upstoxholding.holdings.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.softaai.upstoxholding.R
import com.softaai.upstoxholding.data.model.Data
import com.softaai.upstoxholding.data.model.HoldingsApiResponse
import com.softaai.upstoxholding.data.remote.State
import com.softaai.upstoxholding.holdings.viewmodel.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


@Composable
fun HoldingsScreen(
    mViewModel: MainViewModel
){

    val state = mViewModel.holdings.value

        LazyColumn(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            state?.let {

                itemsIndexed(
                    items = it,
                    itemContent = { _, data ->
                            HoldingItem(
                                data,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                    }
                )
            }
        }


}