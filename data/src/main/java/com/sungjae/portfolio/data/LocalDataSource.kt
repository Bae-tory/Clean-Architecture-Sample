package com.sungjae.portfolio.data


interface LocalDataSource {

    fun getString(key: String): String?
    fun getInt(key: String): Int
    fun getLong(key: String): Long
    fun getBoolean(key: String): Boolean

    fun putString(key: String, data: String)
    fun putInt(key: String, data: Int)
    fun putLong(key: String, data: Long)
    fun putBoolean(key: String, data: Boolean)

    fun clearData()

    /*   String between List parsing   */

//    fun getListData(key: String, onSuccess: (list: List<VipData>) -> Unit)
//    fun saveListData(key: String, list: List<VipData>)
}