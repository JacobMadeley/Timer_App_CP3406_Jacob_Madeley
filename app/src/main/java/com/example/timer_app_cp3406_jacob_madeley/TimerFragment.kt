package com.example.timer_app_cp3406_jacob_madeley

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.timer_app_cp3406_jacob_madeley.databinding.FragmentTimerBinding


class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding
    private val viewModel: TimerViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val intervals = it.getParcelableArrayList<Interval>("intervals")
            if (intervals != null) {
                viewModel.setIntervals(intervals)
            }
        }

        viewModel.timerText.observe(viewLifecycleOwner, Observer { time ->
            binding.textViewTimer.text = time
        })

        viewModel.backgroundColor.observe(viewLifecycleOwner, Observer { color ->
            binding.root.setBackgroundColor(color)
        })
    }
}