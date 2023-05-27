package com.example.citipoc.utils.cgwnav.custype


import com.google.gson.annotations.SerializedName

data class CustTypeItem(
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
    @SerializedName("TabItems")
    val tabItems: List<TabItem>
)