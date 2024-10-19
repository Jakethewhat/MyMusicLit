package com.example.musiclstr

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_signup) // Ensure the correct layout file

        // Initialize views
        usernameEditText = findViewById(R.id.editTextText) // Update with the correct ID
        passwordEditText = findViewById(R.id.editTextText2) // Update with the correct ID
        signUpButton = findViewById(R.id.signUpButton)

        // Set click listener for the sign-up button
        signUpButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            registerUser(username, password) // Call registerUser function
        }
    }

    private fun registerUser(username: String, password: String) {
        // Validate inputs
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            return
        }

        // Create register request
        val registerRequest = RegisterRequest(username, password)

        // Call the register API
        RetrofitClient.instance.register(registerRequest).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let {
                        Toast.makeText(this@SignUpActivity, it.message, Toast.LENGTH_SHORT).show()
                        finish() // Close SignUpActivity on successful registration
                    }
                } else {
                    Toast.makeText(this@SignUpActivity, "Registration failed: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
