package com.example.citipoc.utils.cgwnav


import com.google.gson.annotations.SerializedName

data class Navigation(
    @SerializedName("Navigation Behavior")
    val navigationBehavior: List<NavigationBehavior>
)