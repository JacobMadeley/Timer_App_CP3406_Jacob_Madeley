package com.example.timer_app_cp3406_jacob_madeley

import android.graphics.Color

class SettingsViewModel: ViewModel() {

    val intervals = MutableLiveData<String>("4")
    private val _onApplyButtonClicked = MutableLiveData<List<Interval>>()
    val onApplyButtonClicked: LiveData<List<Interval>> = _onApplyButtonClicked

    val colorAdapter = ColorAdapter()

    fun applySetting(){
        val intervalCount = intervals.value?.toIntOrNull() ?: 4
        val intervalsList = mutableListOf<Interval>()

        val totalDuration = 10 * 60 * 1000 / intervalCount
        val colors = colorAdapter.getColors()

        for (i in 0 until intervalCount) {
            intervalsList.add(
                Interval(
                    start = i * totalDuration,
                    end = (i + 1) * totalDuration,
                    color = colors.getOrElse(i) { Color.BLUE }
                )
            )
        }
        _onApplyButtonClicked.value = intervalsList
    }
}