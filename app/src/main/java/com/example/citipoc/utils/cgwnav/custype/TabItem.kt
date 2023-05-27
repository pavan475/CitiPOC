package com.example.citipoc.utils.cgwnav.custype


import com.google.gson.annotations.SerializedName

data class TabItem(
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
    val transactions: String)