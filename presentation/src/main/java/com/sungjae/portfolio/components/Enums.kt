package com.sungjae.portfolio.components

import android.os.Parcelable
import androidx.annotation.StringRes
import com.sungjae.portfolio.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Tabs(@StringRes val id: Int) : Parcelable {
    BLOG(R.string.tab_blog),
    NEWS(R.string.tab_news),
    MOVIE(R.string.tab_movie),
    BOOK(R.string.tab_book);
}
