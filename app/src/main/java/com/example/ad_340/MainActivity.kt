package com.example.ad_340

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var modelList = ArrayList<Model>();

    var names = arrayOf(
        "Spring",
        "Summer",
        "Fall",
        "Winter"
    );

    var images = intArrayOf(
        1,
        2,
        3,
        4
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "Hello World");

        for (i in names.indices){
            modelList.add(Model(names[i], images[i]));
        }

        var customAdapter = CustomAdapter(modelList, this);

        gridView
    }

    class CustomAdapter(
        var itemModel: ArrayList<Model>,
        var context: Context
    ) : BaseAdapter(){

        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        override fun getCount(): Int {
            return itemModel.size
        }

        override fun getItem(position: Int): Any {
            return itemModel[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            var view = view;
            if (view == null){
                view = layoutInflater.inflate(R.layout.row_items,viewGroup, false)
            }

            var tvImageName = view?.findViewById<TextView>(R.id.imageName)
            var imageView = view?.findViewById<ImageView>(R.id.imageView)
            var tvImageButton = view?.findViewById<ImageButton>(R.id.imageButton)

            tvImageName?.text = itemModel[position].name;
            //imageView?.setImageResource(itemModel[position].image!!)

            return view!!;

        }
    }
}