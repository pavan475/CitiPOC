package com.example.citipoc.utils.cgwnav


import com.google.gson.annotations.SerializedName

data class TabItemsItem(
    @SerializedName("Account Details")
    val accountDetails: String,
    @SerializedName("Accounts Overview")
    val accountsOverview: String,
    @SerializedName("Documents")
    val documents: String,
    @SerializedName("Holdings")
    val holdings: String,
    @SerializedName("Performance")
    val performance: String,
    @SerializedName("Transactions")
    val transactions: String
)