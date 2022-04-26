package com.example.ad_340

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_MESSAGE = "com.example.ad_340.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "Starting MainActivity");

    }

    /** called when the user taps the Send button **/
    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    /** called when the user taps the Movie List button **/
    fun viewMovies(view: View) {
        val message = "MovieList"
        val intent = Intent(this, MovieList::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

}