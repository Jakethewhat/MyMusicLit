package com.example.musiclstr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private val songList: List<Song>) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val albumImageView: ImageView = view.findViewById(R.id.albumImageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val artistTextView: TextView = view.findViewById(R.id.artistTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        // Inflate your song item layout here
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songList[position]
        holder.titleTextView.text = song.title
        holder.artistTextView.text = song.artist

        // Optional: Load album image if available, using a library like Glide or Picasso
        // Glide.with(holder.albumImageView.context).load(song.albumImageUrl).into(holder.albumImageView)
    }

    override fun getItemCount(): Int {
        return songList.size
    }
}
