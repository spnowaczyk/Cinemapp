package com.example.moviesandroidapp.ui.screening

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesandroidapp.data.repository.ScreeningRepository
import com.example.moviesandroidapp.ui.movie.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScreeningActivity: AppCompatActivity()
{
    private lateinit var screeningRepository: ScreeningRepository
    private val screeningData = ArrayList<ScreeningViewModel>() // <-- tutaj są seanse dla danego kina i filmu

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        fetchScreeningsFromApi(2,1) // <-- tutaj dać id kina i filmu żeby zobaczyć seanse tego filmu w tym kinie
    }

    private fun fetchScreeningsFromApi(cinema_id: Int, movie_id: Int)
    {
        screeningRepository = ScreeningRepository(resources)
        screeningRepository.getScreenings(cinema_id, movie_id, object : Callback<List<ScreeningViewModel>>
        {
            override fun onResponse(
                call: Call<List<ScreeningViewModel>>,
                response: Response<List<ScreeningViewModel>>
            ) {
                if (response.isSuccessful) {
                    val screenings = response.body()
                    if (screenings != null) {
                        screeningData.clear()
                        screeningData.addAll(screenings)
                        Log.d("ScreeningDetails", "Screenings: $screeningData")
                    }
                } else {
                    Log.e("ScreeningDetails", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ScreeningViewModel>>, t: Throwable) {
                Log.e("ScreeningDetails", "Connection error", t)
            }
        })
    }
}
