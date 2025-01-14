package com.example.moviesandroidapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesandroidapp.databinding.ActivityMainBinding
import com.example.moviesandroidapp.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_details)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCinemaList.setOnClickListener{
            val intent = Intent(this, CinemasActivity::class.java)
            startActivity(intent)
        }

        val movieName = intent.getStringExtra("movieName")
        val movieDescription = intent.getStringExtra("movieDescription")
        val movieImage = intent.getStringExtra("movieImage")
        val movieRating = intent.getStringExtra("movieRating")

        val t1: TextView = findViewById(R.id.detailMovieName)
        t1.text = movieName

        val t2: TextView = findViewById(R.id.detailMovieDescription)
        t2.text = movieDescription


        val img1: ImageView = findViewById(R.id.movieImage)
        Glide.with(this)
            .load(movieImage)
            .into(img1)

    }
}