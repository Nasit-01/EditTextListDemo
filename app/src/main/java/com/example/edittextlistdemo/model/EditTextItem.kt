package com.example.edittextlistdemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EditTextItem(
    var value : String = "",
    var shouldShowError : Boolean = false
) :Parcelable
