package com.sungjae.portfolio.domain.entity.request

import com.google.gson.annotations.SerializedName


data class Request(
    @SerializedName("DeviceId")
    val deviceId: String? = null,
    @SerializedName("DeviceTok")
    val deviceToken: String? = null,
    @SerializedName("VipIdx")
    val vipIdx: String? = null,
    @SerializedName("PageId")
    val pageId: String? = null,
    @SerializedName("Height")
    val height: String? = null,
    @SerializedName("Weight")
    val weight: String? = null,
    @SerializedName("SelectedAllergies")
    val selectedAllergies: ArrayList<Int>? = null,
    @SerializedName("DietTimeType")
    val dietTimeType: String? = null,
    @SerializedName("SelectedDietSetIdx")
    val selectedDietSetIdx: String? = null,
    @SerializedName("MenuIdx")
    val menuIdx: String? = null,
    @SerializedName("Intake")
    val intake: String? = null,
    @SerializedName("FoodCd")
    val foodCd: String? = null,
    @SerializedName("DiaryCd")
    val dietCd: String? = null
)