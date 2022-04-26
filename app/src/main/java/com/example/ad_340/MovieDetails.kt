package com.example.ad_340

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val extras = getIntent().extras

        // get the Intent that started this activity and extract the string
        val header = extras?.getString("EXTRA_TITLE")
        val director = extras?.getString("EXTRA_TEXT")
        val description = extras?.getString("EXTRA_MESSAGE")

        //capture the layout's TextView and set the string as its text
        val movieHeaderTextView = findViewById<TextView>(R.id.movieHeaderTextView).apply {
            text = header
        }

        val movieDirectorTextView = findViewById<TextView>(R.id.movieDirectorTextView).apply {
            text = director
        }

        val movieDescriptionTextView = findViewById<TextView>(R.id.movieDescriptionTextView).apply {
            text = description
        }
    }
}