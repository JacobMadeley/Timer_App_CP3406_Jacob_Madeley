package com.example.timer_app_cp3406_jacob_madeley

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timer_app_cp3406_jacob_madeley.databinding.ItemColorBinding

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    private val colors = mutableListOf<Int>()
    private val colorChoices = listOf(
        android.graphics.Color.BLUE,
        android.graphics.Color.YELLOW,
        android.graphics.Color.ORANGE,
        android.graphics.Color.RED,
        android.graphics.Color.GREEN,
        android.graphics.Color.CYAN,
        android.graphics.Color.MAGENTA,
        android.graphics.Color.GRAY,
        android.graphics.Color.WHITE,
        android.graphics.Color.LTGRAY
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.ColorViewHolder {
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int = colors.size

    fun getColors(): List<Int> = colors

    fun setIntervals(count: Int) {
        colors.clear()
        for (i in 0 until count) {
            colors.add(colorChoices.getOrElse(i) { android.graphics.Color.BLUE })
        }
        notifyDataSetChanged()
    }

    inner class ColorViewHolder(private val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(color: Int) {
            binding.color
            binding.executePendingBindings()
        }
    }
}