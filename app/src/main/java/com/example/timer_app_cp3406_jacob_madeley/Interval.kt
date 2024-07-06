package com.example.timer_app_cp3406_jacob_madeley

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Interval (
    val start: Long,
    val end: Long,
    val color: Int
) : Parcelable