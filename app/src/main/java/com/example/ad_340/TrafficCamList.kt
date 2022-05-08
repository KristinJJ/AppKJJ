package com.example.ad_340

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class TrafficCamList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traffic_cam_list)

        val textView3 = findViewById<TextView>(R.id.textView3);
        val cameraList: ArrayList<TrafficCam> = ArrayList()

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2"

        // Request a JSON response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONObject> { response ->
                try {
                    val features = response.getJSONArray("Features")

                    textView3.text = "Response is: ${features.length()}"
                    for (cam in features.length() downTo 1) {
                        val point = features.getJSONObject(cam)
                        val pointCoords = point.getJSONArray("PointCoordinate")

                        // points may have more than one camera
                        val camera = point.getJSONArray("Cameras").getJSONObject(0)
                        val c = TrafficCam(
                            camera.getString("Description"),
                            camera.getString("ImageUrl"),
                            camera.getString("Type"),
                            doubleArrayOf(pointCoords.getDouble(0), pointCoords.getDouble(1))
                        )
                        cameraList.add(c)
                    }
                } catch (e: JSONException) {

                }
            },
            Response.ErrorListener { textView3.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }

}