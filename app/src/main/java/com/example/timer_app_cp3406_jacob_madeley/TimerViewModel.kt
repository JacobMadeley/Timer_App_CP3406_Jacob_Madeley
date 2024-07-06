package com.example.timer_app_cp3406_jacob_madeley

import android.graphics.Color
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    private val _timerText = MutableLiveData<String>("10:00")
    val timerText: LiveData<String> = _timerText

    private val _backgroundColor = MutableLiveData<Int>(Color.WHITE)
    val backgroundColor: LiveData<Int> = _backgroundColor

    private val _intervalsRemaining = MutableLiveData<Int>(4)
    val intervalsRemaining: LiveData<Int> = _intervalsRemaining

    private var intervals: List<Interval> = listOf(
        Interval(0, 2 * 60 * 1000, Color.BLUE),
        Interval(2 * 60 * 1000, 5 * 60 * 1000, Color.YELLOW),
        Interval(5 * 60 * 1000, 7 * 60 * 1000, Color.ORANGE),
        Interval(7 * 60 * 1000, 10 * 60 * 1000, Color.RED)
    )

    private lateinit var countDownTimer: CountDownTimer
    private var totalTimeInMillis: Long = 10 * 60 * 1000
    private var remainingTimeInMillis: Long = totalTimeInMillis
    private var isTimerRunning = false
    private var currentIntervalIndex = 0

    fun startTimer() {
        if (!isTimerRunning){
            countDownTimer = object : CountDownTimer(remainingTimeInMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    remainingTimeInMillis = millisUntilFinished
                    val minutes = (millisUntilFinished / 1000) / 60
                    val seconds = (millisUntilFinished / 1000) % 60
                    _timerText.value = String.format("%02d:%02d", minutes, seconds)
                    checkIntervalChange(totalTimeInMillis - millisUntilFinished)
                }

                override fun onFinish() {
                    _timerText.value = "00:00"
                    _intervalsRemaining.value = 0
                    isTimerRunning = false
                }
            }
            countDownTimer.start()
            isTimerRunning = true
        }
    }

    fun pauseTimer() {
        if (isTimerRunning) {
            countDownTimer.cancel()
            isTimerRunning = false
        }
    }

    fun resetTimer() {
        pauseTimer()
        remainingTimeInMillis = totalTimeInMillis
        _timerText.value = "10:00"
        _backgroundColor.value = Color.WHITE
        currentIntervalIndex = 0
        _intervalsRemaining.value = intervals.size
    }

    fun setIntervals(intervals: List<Interval>) {
        intervals = newIntervals
        _intervalsRemaining.value = intervals.size
        totalTimeInMillis = intervals.last().end
        remainingTimeInMillis = totalTimeInMillis
        _timerText.value = String.format("%02d:%02d", totalTimeInMillis / 1000 / 60, totalTimeInMillis / 1000 % 60)
    }

    private fun checkIntervalChange(elapsedTime: Long) {
        val intervalIndex = intervals.indexOfFirst { elapsedTime in it.start until it.end }
        if (intervalIndex != -1 && intervalIndex != currentIntervalIndex) {
            currentIntervalIndex = intervalIndex
            _backgroundColor.value = intervals[currentIntervalIndex].color
            _intervalsRemaining.value = intervals.size - currentIntervalIndex
        }

    }

}