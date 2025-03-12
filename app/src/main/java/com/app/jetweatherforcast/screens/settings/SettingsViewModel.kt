package com.app.jetweatherforcast.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jetweatherforcast.model.Favourite
import com.app.jetweatherforcast.model.Unit
import com.app.jetweatherforcast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository : WeatherDbRepository
) :ViewModel() {

    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())

    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged()
                .collect{
                        listOfUnits -> if (listOfUnits.isNullOrEmpty()){
                    Log.d("WeatherUnits","EMPTY Units")
                }
                else{
                    _unitList.value = listOfUnits
                    Log.d("UnitList","${unitList.value}")
                }
                }
        }
    }

    fun insertUnit(unit: Unit) = viewModelScope.launch {
        repository.insertUnits(unit)
    }

    fun deleteUnit(unit: Unit) = viewModelScope.launch {
        repository.deleteUnit(unit)
    }

    fun deleteAllUnit() = viewModelScope.launch {
        repository.deleteAllUnit()
    }

    fun updateUnit(unit: Unit) = viewModelScope.launch {
        repository.updateUnits(unit)
    }

}