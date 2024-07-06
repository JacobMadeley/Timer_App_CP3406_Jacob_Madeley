package com.example.timer_app_cp3406_jacob_madeley

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.timer_app_cp3406_jacob_madeley.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onApplyButtonClicked.observe(viewLifecycleOwner, Observer { intervals ->
            val bundle = Bundle().apply {
                putParcelableArrayList("intervals", ArrayList(intervals))
            }
            findNavController().navigate(R.id.action_settingsFragment_to_timerFragment, bundle)
        })
    }
}