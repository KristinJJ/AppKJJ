package com.example.ad_340

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class TrafficCamAdapter(private val context: Context, private val dataSource: ArrayList<TrafficCam>) : BaseAdapter() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.activity_traffic_cam_row_item, parent, false)

        // Get description element
        val descriptionTextView = rowView.findViewById(R.id.traffic_cam_description) as TextView

        // Get image element
        val imageImageView = rowView.findViewById(R.id.traffic_cam_image) as ImageView

        val trafficCam = getItem(position) as TrafficCam

        descriptionTextView.text = trafficCam.description
        Picasso.with(context).load(trafficCam.image).placeholder(R.mipmap.ic_launcher).into(imageImageView)

        return rowView
    }
}