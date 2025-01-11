package com.example.moviesandroidapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesandroidapp.databinding.ActivityCinemasBinding

class CinemasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCinemasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCinemasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeCinemasView()

    }

    private fun initializeCinemasView() {
        val cinemasView = binding.cinemasView
        cinemasView.layoutManager = LinearLayoutManager(this)

        val cinemasData = ArrayList<CinemasViewModel>()
        fillCinemasWithSampleData(cinemasData) //TODO: Fill with custom values

        cinemasView.adapter = CinemasAdapter(cinemasData)
    }

    private fun fillCinemasWithSampleData(cinemasData: ArrayList<CinemasViewModel>) {
        for (i in 1..20) {
            val cinemaName = "Sample Cinema $i"
            val distance = (100..2000).random()
            cinemasData.add(CinemasViewModel(cinemaName, distance))
        }
    }
}