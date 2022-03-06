package com.softaai.upstoxholding.holdings.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softaai.upstoxholding.data.model.Data
import com.softaai.upstoxholding.data.model.HoldingsApiResponse
import com.softaai.upstoxholding.data.remote.State
import com.softaai.upstoxholding.data.repository.HoldingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val holdingsRepository: HoldingsRepository) :
    ViewModel() {

    private val _holdingsLiveData = MutableLiveData<State<HoldingsApiResponse>>()

    val holdingsLiveData: LiveData<State<HoldingsApiResponse>> = _holdingsLiveData

    private val _holdings = MutableLiveData(listOf<Data>())
    val holdings: LiveData<List<Data>> = _holdings

    fun getHoldingsList() {
        viewModelScope.launch {
            holdingsRepository.getAllHoldings()
                .onStart { _holdingsLiveData.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _holdingsLiveData.value = state }
        }
    }

    fun setHoldings(list: List<Data>) {
        viewModelScope.launch {
            _holdings.value = list
        }
    }

    fun getIndividualItemCurrentValue(ltp: Double, quantity: Int): Double = ltp * quantity

    fun getIndividualItemInvestmentValue(avgPrice: String, quantity: Int): Int =
        avgPrice.toInt() - quantity

    fun getIndividualItemProfitAndLoss(currentValue: Double, investmentValue: Int): Double =
        currentValue - investmentValue

    fun getCurrentValue(list: List<Data>) : Double {
        var currentValue = 0.0
        for (holding in list) {
             currentValue += holding.ltp * holding.quantity
        }
        return currentValue
    }

    fun getTotalInvestment(list: List<Data>) : Int {
        var totalInvestment = 0
        for (holding in list) {
            totalInvestment += holding.avgPrice.toInt() - holding.quantity
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
}