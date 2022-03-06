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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val holdingsRepository: HoldingsRepository) :
    ViewModel() {

    private val _holdingsLiveData = MutableLiveData<State<HoldingsApiResponse>>()

    val holdingsLiveData: LiveData<State<HoldingsApiResponse>> = _holdingsLiveData


    private val _holdings = MutableLiveData(listOf<Data>())
    val holdings: LiveData<List<Data>> = _holdings

//    private val _expandedHoldingIdsList = MutableStateFlow(listOf<Int>())
//    val expandedHoldingIdsList: StateFlow<List<Int>> get() = _expandedHoldingIdsList



    fun getHoldingsList() {
        viewModelScope.launch {
            holdingsRepository.getAllHoldings()
                .onStart { _holdingsLiveData.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _holdingsLiveData.value = state }
        }
    }

    fun setHoldings(list: List<Data>){
        viewModelScope.launch {
            _holdings.value = list
        }
    }

//    fun onExpandClicked(holdingId: Int) {
//        _expandedHoldingIdsList.value = _expandedHoldingIdsList.value.toMutableList().also { list ->
//            if (list.contains(holdingId)) list.remove(holdingId) else list.add(holdingId)
//        }
//    }
}