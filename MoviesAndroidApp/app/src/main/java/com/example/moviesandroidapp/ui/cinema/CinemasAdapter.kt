package com.example.moviesandroidapp.ui.cinema

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesandroidapp.R

class CinemasAdapter(private val mList: List<CinemasViewModel>) : RecyclerView.Adapter<CinemasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cinemas_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cinema = mList[position]

        holder.name.text = cinema.name
        holder.distance.text = "${cinema.distance}m"

        holder.itemView.setOnClickListener {
            openGoogleMaps(holder.itemView, cinema)
        }
    }

    private fun openGoogleMaps(view: View, cinema: CinemasViewModel) {
        try {
            val latitude = cinema.latitude
            val longitude = cinema.longitude

            if (latitude != null && longitude != null) {
                val url = "https://www.google.com/maps/dir/?api=1&destination=$latitude,$longitude&travelmode=walking&dir_action=navigate"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.cinemaName)
        val distance: TextView = itemView.findViewById(R.id.distanceValue)
    }
}