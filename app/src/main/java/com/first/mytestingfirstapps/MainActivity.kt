package com.first.mytestingfirstapps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.first.mytestingfirstapps.cropImage.CropImageActivity
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private var text:EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById<View>(R.id.eidText) as EditText
        val textView = findViewById<View>(R.id.text) as TextView
        textView.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("FirstActivityKey", text?.text.toString())
            startActivityForResult(intent,SECOND_ACTIVITY_REQUEST_CODE)
        }

        val openMapButton = findViewById<Button>(R.id.openMapButton)

        openMapButton.setOnClickListener {
            val intent = Intent(this, MapsActivityCurrentPlace::class.java)
             startActivity(intent)
        }

        val cropImageActivity = findViewById<Button>(R.id.cropImageActivity)

        cropImageActivity.setOnClickListener {
            val intent = Intent(this, CropImageActivity::class.java)
             startActivity(intent)
        }
        val mapButton = findViewById<Button>(R.id.mapButton)

        mapButton.setOnClickListener {
            val uri = "http://maps.google.com/maps?saddr=dhaka&daddr=rajshahi"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent)
        }

        ///image library
        val imageView = findViewById(R.id.imageView) as ImageView
        ///image library
       // val url = "https://picsum.photos/250?image=9"
        val imageUri = "https://i.imgur.com/tGbaZCY.jpg"
        Picasso.get().load(imageUri).into(imageView)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            if(requestCode == SECOND_ACTIVITY_REQUEST_CODE){
                val returnString = data!!.getStringExtra(Intent.EXTRA_TEXT)
                text?.text = returnString?.toEditable()
            }
        }
    }

    companion object{
        private const val SECOND_ACTIVITY_REQUEST_CODE = 10
    }
}