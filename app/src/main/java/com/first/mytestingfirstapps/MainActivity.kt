package com.first.mytestingfirstapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var text:EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById<View>(R.id.eidText) as EditText
        val textView = findViewById<View>(R.id.text) as TextView
        textView.setOnClickListener {
            val intent = Intent(this, MapsActivityCurrentPlace::class.java)
            intent.putExtra("FirstActivityKey", text?.text.toString())

            // startActivity(intent)
            startActivityForResult(intent,SECOND_ACTIVITY_REQUEST_CODE)
        }

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