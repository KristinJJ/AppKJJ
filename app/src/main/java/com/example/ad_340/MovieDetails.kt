package com.example.ad_340

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val extras = getIntent().extras

        // get the Intent that started this activity and extract the string
        val header = extras?.getString("EXTRA_TITLE")
        val director = extras?.getString("EXTRA_TEXT")
        val url = extras?.getString("EXTRA_URL")
        val description = extras?.getString("EXTRA_MESSAGE")


        //val imageUri = "https://i.imgur.com/tGbaZCY.jpg"
        val movieImageView: ImageView = findViewById<View>(R.id.movieImageView) as ImageView
        Picasso.with(this).load(url).into(movieImageView)

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