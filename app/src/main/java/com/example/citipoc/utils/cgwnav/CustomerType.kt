package com.example.citipoc.utils.cgwnav


import com.google.gson.annotations.SerializedName

data class CustomerType(
    @SerializedName("AG/Account")
    val aGAccount: String,
    @SerializedName("Cat Type")
    val catType: String,
    @SerializedName("Customer Type")
    val customerType: String,
    @SerializedName("Go To Screen")
    val goToScreen: String,
    @SerializedName("TabItemsItem")
    val tabList : ArrayList<TabItemsItem>

)