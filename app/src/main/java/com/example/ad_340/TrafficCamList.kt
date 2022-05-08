package com.example.ad_340

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class TrafficCamList : AppCompatActivity() {
    var mListView: ListView? = null
    var listAdapter: TrafficCamAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traffic_cam_list)

        mListView = findViewById<ListView>(R.id.trafficCamList)

        val cameraListArray: ArrayList<TrafficCam> = ArrayList()


            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2"

            // Request a JSON response from the provided URL.
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener<JSONObject> { response ->
                    Log.d("TrafficCamListActivity", "something here");
                    try {
                        val features = response.getJSONArray("Features")
                        Log.d("TrafficCamListActivity", features.length().toString());
                        for (i in 1 until features.length()) {
                            val point = features.getJSONObject(i)
                            val pointCoords = point.getJSONArray("PointCoordinate")

                            // points may have more than one camera
                            val camera = point.getJSONArray("Cameras").getJSONObject(0)
                            Log.d("TrafficCamListActivity",camera.getString("Description"))
                            Log.d("TrafficCamListActivity",camera.getString("ImageUrl"))
                            Log.d("TrafficCamListActivity",camera.getString("Type"))
                            val c = TrafficCam(
                                camera.getString("Description"),
                                camera.getString("ImageUrl"),
                                camera.getString("Type"),
                                doubleArrayOf(pointCoords.getDouble(0), pointCoords.getDouble(1))
                            )
                            cameraListArray.add(c)
                        }
                        // return results to caller
                        //updateResults(cameraListArray)
                    } catch (e: JSONException) {
                        Log.d("TrafficCamListActivity", "that didn't work")
                    }
                },
                Response.ErrorListener { error -> Log.d("JSON", "Error: " + error.message) })

            // Add the request to the RequestQueue.
            queue.add(jsonObjectRequest)

            // access the listView from xml file
            val arrayAdapter = TrafficCamAdapter(this, cameraListArray)
            mListView?.setAdapter(arrayAdapter)

        }


}