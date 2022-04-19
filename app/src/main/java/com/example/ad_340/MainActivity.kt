package com.example.ad_340

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var modelList = ArrayList<Model>();

    var names = arrayOf(
        "Spring",
        "Summer",
        "Fall",
        "Winter"
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "Hello World");

        for (i in names.indices){
            modelList.add(Model(names[i]));
        }

        var customAdapter = CustomAdapter(modelList, this);

        var gridView = findViewById(R.id.gridView) as GridView;

        gridView.adapter = customAdapter;

        var loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener { makeToast(this) }
    }

    private fun makeToast(mainActivity: MainActivity) {
        Toast.makeText(baseContext, "Hello Toast!", Toast.LENGTH_SHORT).show();
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

            var toastButton = view?.findViewById<Button>(R.id.toastButton)

            toastButton?.text = itemModel[position].name;
            toastButton?.setOnClickListener(){
                //Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT).show();
            }
            //imageView?.setImageResource(itemModel[position].image!!)

            return view!!;

        }
    }
}