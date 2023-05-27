package com.example.citipoc.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.citipoc.domain.model.Meal
import com.example.citipoc.domain.model.MealDetails
import com.example.citipoc.domain.usecases.meallist.SearchMealsUseCase
import com.example.citipoc.domain.usecases.meallist.SearchMealsUseCaseImpl
import com.example.citipoc.utils.Resource
import com.example.citipoc.presentation.ui.base.BaseViewModel
import com.example.citipoc.presentation.ui.view.mealsearch.MealSearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import io.reactivex.functions.Consumer

class MealSearchViewModel @Inject constructor(
    private val mealSearchMealsUseCase: SearchMealsUseCase
) : BaseViewModel() {
    private val _mealSearchList = MutableStateFlow<MealSearchState>(MealSearchState())
    val mealSearchList: StateFlow<MealSearchState> = _mealSearchList

    private val result = MutableLiveData<MealSearchState>(MealSearchState())
    val mealSearchLivedata: LiveData<MealSearchState> = result


    //corotouine call
    fun getSearchMeals(s: String) {
        mealSearchMealsUseCase.getMealSearch(s).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealSearchList.value = MealSearchState(isLoading = true)
                }
                is Resource.Success -> {
                    _mealSearchList.value = MealSearchState(data = it.data)
                }
                is Resource.Error -> {
                    _mealSearchList.value = MealSearchState(error = it.message ?: "")
                }
                else -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    //Rx kotlin
    fun getMealSearchRx(id: String) {
        result.value = MealSearchState(true)

        addDisposable(
            mealSearchMealsUseCase.getMealSearchRx(id)
                .subscribe({
                    when (it) {
                        is Resource.Loading -> {
                            result.value = MealSearchState(true)
                        }
                        is Resource.Success -> {
                            result.value = MealSearchState(data = it.data)
                        }
                        is Resource.Error -> {
                            result.value = MealSearchState(error = it.message ?: "")
                        }
                        else -> {
                        }
                    }
                }, Consumer {
                    // result.value = Resource.Error(message = it.localizedMessage ?: "An Unknown error occurred")
                    Log.e(MealSearchViewModel::class::simpleName.toString(), it.localizedMessage)
                })
        )
    }
}