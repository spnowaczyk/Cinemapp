package com.example.moviesandroidapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesandroidapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val moviesData = ArrayList<MoviesViewModel>() //array containing all avaliable movies
    private lateinit var adapter: MoviesAdapter
    private lateinit var movieRepository: MovieRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        movieRepository = MovieRepository(resources)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeFloatingActionButton()
        initializeMoviesView()
        initializeSearchBar()
    }
    private fun initializeFloatingActionButton() {
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, CinemasActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializeSearchBar() {
        binding.searchBarContainerText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                // Update the adapter with the filtered movies
                val filteredMoviesData = moviesData.filter { movie ->
                    movie.name.contains(s.toString(), ignoreCase = true)
                }
                adapter.updateData(filteredMoviesData)
            }
        })
    }

    private fun initializeMoviesView() {
        val moviesView = binding.moviesView
        moviesView.layoutManager = LinearLayoutManager(this)

        //fillMoviesWithSampleData(moviesData) //TODO: Fill with custom values

        adapter = MoviesAdapter(moviesData)
        adapter.onItemClick = {
            val intent = Intent(this, MovieDetailsActivity::class.java)

            intent.putExtra("movieName", it.name)
            intent.putExtra("movieDescription", it.description)
            intent.putExtra("movieImage", it.image)
            intent.putExtra("movieRating", it.rating)

            startActivity(intent)
        }

        moviesView.adapter = adapter
        fetchMoviesFromApi()
    }

    private fun fetchMoviesFromApi() {
        movieRepository.getMovies(callback = object : Callback<List<MoviesViewModel>> {
            override fun onResponse(call: Call<List<MoviesViewModel>>, response: Response<List<MoviesViewModel>>) {
                if (response.isSuccessful) {
                    val movies = response.body()
                    if (movies != null) {
                        Log.d("MainActivity", "Movies: $movies")
                        moviesData.clear()
                        moviesData.addAll(movies)
                        adapter.updateData(moviesData)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast
                        .LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<MoviesViewModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Connection error", Toast.LENGTH_LONG).show()
            }
        })
    }

//    private fun fillMoviesWithSampleData(moviesData: ArrayList<MoviesViewModel>) {
//        moviesData.add(
//            MoviesViewModel(
//                R.drawable.movie_sample_1,
//                "The Shawshank Redemption",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//            )
//        )
//        moviesData.add(
//            MoviesViewModel(
//                R.drawable.movie_sample_2,
//                "The Green Mile",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//            )
//        )
//        moviesData.add(
//            MoviesViewModel(
//                R.drawable.movie_sample_3,
//                "Intouchables",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//            )
//        )
//        moviesData.add(
//            MoviesViewModel(
//                R.drawable.movie_sample_4,
//                "The Godfather",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//            )
//        )
//        moviesData.add(
//            MoviesViewModel(
//                R.drawable.movie_sample_5,
//                "12 Angry Men",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//            )
//        )
//        moviesData.add(
//            MoviesViewModel(
//                R.drawable.movie_sample_6,
//                "Forrest Gump",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//            )
//        )
//        moviesData.add(
//            MoviesViewModel(
//                R.drawable.movie_sample_7,
//                "One Flew Over the Cuckoo's Nest",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//            )
//        )
//        moviesData.add(
//            MoviesViewModel(
//                R.drawable.movie_sample_8,
//                "The Godfather: Part II",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//            )
//        )
//        moviesData.add(
//            MoviesViewModel(
//                R.drawable.movie_sample_9,
//                "The Lord of the Rings: The Return of the King",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//            )
//        )
//        moviesData.add(
//            MoviesViewModel(
//                R.drawable.movie_sample_10,
//                "Schindler's List",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//            )
//        )
//    }
}