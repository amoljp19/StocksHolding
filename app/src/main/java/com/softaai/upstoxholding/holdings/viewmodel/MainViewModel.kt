package com.softaai.upstoxholding.holdings.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softaai.upstoxholding.data.model.HoldingsApiResponse
import com.softaai.upstoxholding.data.remote.State
import com.softaai.upstoxholding.data.repository.HoldingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val holdingsRepository: HoldingsRepository) :
    ViewModel() {

    private val _holdingsLiveData = MutableLiveData<State<HoldingsApiResponse>>()

    val holdingsLiveData: LiveData<State<HoldingsApiResponse>> = _holdingsLiveData



    fun getHoldingsList() {
        viewModelScope.launch {
            holdingsRepository.getAllHoldings()
                .onStart { _holdingsLiveData.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _holdingsLiveData.value = state }
        }
    }
}