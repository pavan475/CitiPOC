package com.example.citipoc.utils.cgwnav


import com.google.gson.annotations.SerializedName

data class NavigationBehavior(
    @SerializedName("AG/Account")
    val aGAccount: String,
    @SerializedName("Account Details")
    val accountDetails: String,
    @SerializedName("Accounts Overview")
    val accountsOverview: String,
    @SerializedName("Back Arrow Navigation")
    val backArrowNavigation: String,
    @SerializedName("Cat Type")
    val catType: String,
    @SerializedName("Customer Type")
    val customerType: String,
    @SerializedName("Display item")
    val displayItem: String,
    @SerializedName("Documents")
    val documents: String,
    @SerializedName("__EMPTY")
    val eMPTY: String,
    @SerializedName("Go To Screen")
    val goToScreen: String,
    @SerializedName("Holdings")
    val holdings: String,
    @SerializedName("Performance")
    val performance: String,
    @SerializedName("Screen ")
    val screen: String,
    @SerializedName("Transactions")
    val transactions: String
)