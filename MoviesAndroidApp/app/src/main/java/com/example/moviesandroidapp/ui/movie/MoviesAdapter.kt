package com.example.moviesandroidapp.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesandroidapp.R

class MoviesAdapter(private var mList: List<MoviesViewModel>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var onItemClick: ((MoviesViewModel) -> Unit)? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_row, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        Glide.with(holder.itemView.context)
            .load(itemsViewModel.image) // URL of the image
            .into(holder.imageView) // ImageView where the image will be loaded

        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemsViewModel.name

        holder.imageView.setOnClickListener{
            onItemClick?.invoke(itemsViewModel)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateData(filteredMoviesData: List<MoviesViewModel>) {
        mList = filteredMoviesData
        notifyDataSetChanged()
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = this.itemView.findViewById(R.id.movieImage)
        val textView: TextView = this.itemView.findViewById(R.id.topLabel)
    }
}