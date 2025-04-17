package com.example.numberguessinggame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.numberguessinggame.databinding.ActivityGuessBinding

class GuessActivity : AppCompatActivity() {

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

        viewbinding.btnGuess.setOnClickListener {
            val intent = Intent(this@GuessActivity, ResultActivity::class.java)
            finish() // Close the current activity (remove it from the back stack)
            startActivity(intent)
        }
    }
}