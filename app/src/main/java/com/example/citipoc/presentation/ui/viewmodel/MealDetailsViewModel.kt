package com.example.citipoc.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.citipoc.domain.model.MealDetails
import com.example.citipoc.utils.Resource
import com.example.citipoc.domain.usecases.mealsdetails.GetMealsDetailsUseCase
import com.example.citipoc.presentation.ui.base.BaseViewModel
import com.example.citipoc.presentation.ui.view.mealdetails.MealDetailsState
import io.reactivex.functions.Consumer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MealDetailsViewModel @Inject constructor(private val mealDetailsUseCase: GetMealsDetailsUseCase) :
   BaseViewModel (){

    private val _mealDetails = MutableStateFlow<MealDetailsState>(MealDetailsState())
    val mealDetails: StateFlow<MealDetailsState> = _mealDetails

    private val result = MutableLiveData<Resource<List<MealDetails>>>()

    val mealDetailLivedata :  LiveData<Resource<List<MealDetails>>> = result


    //Rx Kotlin
    fun getMealDetailsRx(id: String) {
        addDisposable(mealDetailsUseCase.getMealsDetailsRx(id)
            .subscribe({
                    result.value = it
            }, Consumer {
                Log.e(MealDetailsViewModel::class::simpleName.toString(), "Error Meals details")
            })
        )
    }

    //Coroutine
    fun getMealDetails(id: String) {
        mealDetailsUseCase.getMealDetails(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealDetails.value = MealDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealDetails.value = MealDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _mealDetails.value = MealDetailsState(data = it.data?.get(0))
                }
            }
        }.launchIn(viewModelScope)
    }
}