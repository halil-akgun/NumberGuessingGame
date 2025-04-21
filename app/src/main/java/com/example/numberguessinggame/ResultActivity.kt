package com.example.numberguessinggame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.numberguessinggame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var viewbinding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewbinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val result = intent.getBooleanExtra("result", false)
        if (result) {
            viewbinding.imageViewResult.setImageResource(R.drawable.happy)
            viewbinding.textViewResult.text = "YOU WON!"
        } else {
            viewbinding.imageViewResult.setImageResource(R.drawable.sad)
            viewbinding.textViewResult.text = "YOU LOST!"
        }

        viewbinding.btnRestart.setOnClickListener {
            val intent = Intent(this@ResultActivity, GuessActivity::class.java)
            finish() // Close the current activity (remove it from the back stack)
            startActivity(intent)
        }
    }
}