@file:Suppress("DEPRECATION")

package com.example.musiclstr

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login) // Ensure this points to the correct layout

        // Optionally, you can implement a splash screen delay using Handler
        Handler().postDelayed({
            // Launch SignInActivity after a delay
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish() // Finish MainActivity to prevent going back to it
        }, 3000)
    }
}
