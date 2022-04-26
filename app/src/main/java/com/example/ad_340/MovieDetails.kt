package com.example.ad_340

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        // get the Intent that started this activity and extract the string
        val header = intent.getStringExtra(EXTRA_MESSAGE)
        val description = intent.getStringExtra(EXTRA_TEXT)

        //capture the layout's TextView and set the string as its text
        val headerText = findViewById<TextView>(R.id.movieHeaderTextView).apply {
            text = header
        }

        val descriptionText = findViewById<TextView>(R.id.movieDescriptionTextView).apply {
            text = description
        }
    }
}