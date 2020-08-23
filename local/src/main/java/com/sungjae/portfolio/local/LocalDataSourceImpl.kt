package com.sungjae.portfolio.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.sungjae.portfolio.data.LocalDataSource

class LocalDataSourceImpl(private val pref: SharedPreferences) : LocalDataSource {

    override fun getString(key: String) = pref.getString(key, null)

    override fun getInt(key: String) = pref.getInt(key, -1)

    override fun getLong(key: String) = pref.getLong(key, -1L)

    override fun getBoolean(key: String) = pref.getBoolean(key, false)

    override fun putString(key: String, data: String) {
        pref.edit {
            putString(key, data)
        }
    }

    override fun putInt(key: String, data: Int) {
        pref.edit {
            putInt(key, data)
        }
    }

    override fun putLong(key: String, data: Long) {
        pref.edit {
            putLong(key, data)
        }
    }

    override fun putBoolean(key: String, data: Boolean) {
        pref.edit {
            putBoolean(key, data)
        }
    }

    override fun clearData() {
        pref.edit {
            clear()
        }
    }

//    override fun getListData(key: String, onSuccess: (list: List<VipData>) -> Unit) {
//        val jsonListData = pref.getString(key, null) ?: return
//        val listData = convertToGson<VipData>(jsonListData)
//`
//        onSuccess(listData.toList())
//    }
//
//    override fun saveListData(key: String, list: List<VipData>) {
//        val jsonListData = convertToJson(list)
//        editor.putString(key, jsonListData).apply()
//    }
}