package com.example.ad_340

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

const val EXTRA_MESSAGE = "com.example.ad_340.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "Starting MainActivity");

        val textView = findViewById<TextView>(R.id.textView2);

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONObject> { response ->
                // Display the first 500 characters of the response string.
                val value = response.getJSONArray("Features")
                textView.text = "Response is: ${value.length()}"
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

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

    /** called when the user taps the Traffic Cam List button **/
    fun viewTrafficCams(view: View) {
        val message = "Traffic Cam List"
        val intent = Intent(this, TrafficCamList::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}