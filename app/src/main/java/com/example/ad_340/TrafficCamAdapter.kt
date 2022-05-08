package com.example.ad_340

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

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
        val description = rowView.findViewById(R.id.traffic_cam_description) as TextView

        // Get image element
        val image = rowView.findViewById(R.id.traffic_cam_image) as ImageView

        return rowView
    }
}