package com.example.musiclstr

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var signUpRedirectText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SignInActivity", "onCreate: Initializing SignInActivity")
        setContentView(R.layout.activity_login) // Ensure the correct layout file

        // Initialize views
        usernameEditText = findViewById(R.id.editTextText) // Update with correct ID
        passwordEditText = findViewById(R.id.editTextText2) // Update with correct ID
        signInButton = findViewById(R.id.signInButton)
        signUpRedirectText = findViewById(R.id.signUpRedirectText)

        // Initialize toggle button for password visibility
        val togglePasswordButton: ImageView = findViewById(R.id.togglePasswordButton)
        var isPasswordVisible = false

        // Set click listener for toggle button
        togglePasswordButton.setOnClickListener {
            if (isPasswordVisible) {
                // Hide password
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                togglePasswordButton.setImageResource(R.drawable.eye_close) // Use appropriate drawable
            } else {
                // Show password
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                togglePasswordButton.setImageResource(R.drawable.eye_open) // Use appropriate drawable
            }
            isPasswordVisible = !isPasswordVisible
            passwordEditText.setSelection(passwordEditText.text.length) // Keep cursor at the end
        }

        // Set click listener for the sign-in button
        signInButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginUser(username, password) // Call loginUser function
        }

        // Set click listener for sign-up redirect text
        signUpRedirectText.setOnClickListener {
            // Intent to redirect to sign-up activity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(username: String, password: String) {
        // Validate inputs
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            return
        }

        // Create login request
        val loginRequest = LoginRequest(username, password)

        // Call the login API
        RetrofitClient.instance.login(loginRequest).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let {
                        Toast.makeText(this@SignInActivity, it.message, Toast.LENGTH_SHORT).show()
                        // Redirect to main activity after successful login
                        val intent = Intent(this@SignInActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Close SignInActivity
                    }
                } else {
                    Toast.makeText(this@SignInActivity, "Login failed: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@SignInActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
