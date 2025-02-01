package com.example.moviesandroidapp.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesandroidapp.R
import com.example.moviesandroidapp.databinding.ActivityMovieDetailsBinding
import com.example.moviesandroidapp.ui.cinema.CinemasActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val webView = findViewById<WebView>(R.id.WebView)
        val video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/izIycj3j4Ow?si=3E8A6IgT1DktDVpT\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        webView.loadData(video, "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()

        val movieId = intent.getIntExtra("movieId", -1)
        val movieName = intent.getStringExtra("movieName")
        val movieDescription = intent.getStringExtra("movieDescription")
        val movieImage = intent.getStringExtra("movieImage")
        val movieImageWidth = intent.getIntExtra("movieImageWidth", 0)
        val movieImageLength = intent.getIntExtra("movieImageLength", 0)
        val movieRating = intent.getIntExtra("movieRating", 0)
        val movieReleaseDate = intent.getStringExtra("movieReleaseDate")
        val movieDuration = intent.getStringExtra("movieDuration")
        val movieAgeRestrictions = intent.getIntExtra("movieAgeRestrictions", 0)


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