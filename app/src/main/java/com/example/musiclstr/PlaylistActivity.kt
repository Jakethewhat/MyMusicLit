// PlaylistActivity.kt
package com.example.musiclstr

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PlaylistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palylist) // Your layout file for the playlist

        // Get references to the views
        val createPlaylistButton = findViewById<Button>(R.id.createPlaylistButton)
        val playlistNameEditText = findViewById<EditText>(R.id.playlistNameEditText)

        // Set click listener for the button
        createPlaylistButton.setOnClickListener {
            // Get the playlist name entered by the user
            val playlistName = playlistNameEditText.text.toString()

            // Validate if the playlist name is not empty
            if (playlistName.isNotEmpty()) {
                // Handle playlist creation (e.g., save to database or display a message)
                createNewPlaylist(playlistName)
            } else {
                // Show a message if the playlist name is empty
                Toast.makeText(this, "Please enter a playlist name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to handle the playlist creation logic
    private fun createNewPlaylist(playlistName: String) {
        // Here you would typically save the playlist name to a database or perform another action
        // For demonstration, we'll just display a Toast message
        Toast.makeText(this, "Playlist '$playlistName' created!", Toast.LENGTH_SHORT).show()

        // Optionally, clear the EditText after creating the playlist
        val playlistNameEditText = findViewById<EditText>(R.id.playlistNameEditText)
        playlistNameEditText.text.clear()
    }
}
