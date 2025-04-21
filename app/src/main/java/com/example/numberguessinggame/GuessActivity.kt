package com.example.numberguessinggame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.numberguessinggame.databinding.ActivityGuessBinding

class GuessActivity : AppCompatActivity() {

    private var remainingAttempts = 5
    private var secretNumber = 0

    private lateinit var viewbinding: ActivityGuessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewbinding = ActivityGuessBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        secretNumber = (1..100).random()
        Log.e("Secret Number", "Secret Number: $secretNumber")

        viewbinding.btnGuess.setOnClickListener {
//            val intent = Intent(this@GuessActivity, ResultActivity::class.java)
//            finish() // Close the current activity (remove it from the back stack)
//            startActivity(intent)

            remainingAttempts--

            val userGuess = viewbinding.editTextInput.text.toString().toInt()
            if (userGuess == secretNumber) {
                val intent = Intent(this@GuessActivity, ResultActivity::class.java)
                intent.putExtra("result", true)
                finish() // Close the current activity (remove it from the back stack)
                startActivity(intent)
                return@setOnClickListener // Exit the function (if you don't put this,
            // it will run the rest of the code and if remainingAttempts is 0, the result will be false)
            } else if (userGuess < secretNumber) {
                viewbinding.textViewHint.text = "Go higher"
                viewbinding.textView2.text = "Remaining attempts: $remainingAttempts"
            } else {
                viewbinding.textViewHint.text = "Go lower"
                viewbinding.textView2.text = "Remaining attempts: $remainingAttempts"
            }

            if (remainingAttempts == 0) {
                val intent = Intent(this@GuessActivity, ResultActivity::class.java)
                intent.putExtra("result", false)
                finish() // Close the current activity (remove it from the back stack)
                startActivity(intent)
            }
            viewbinding.editTextInput.text.clear()
        }
    }
}