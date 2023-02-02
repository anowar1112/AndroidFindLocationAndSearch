package com.first.mytestingfirstapps

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.first.mytestingfirstapps.broadcastReceiver.MyReceiver
import com.first.mytestingfirstapps.chatGPT.ChatGPTActivity
import com.first.mytestingfirstapps.chatGPT.ChatGPTTwoAPI
import com.first.mytestingfirstapps.viewModel.NameViewModel

class SecondActivity : AppCompatActivity() {

    private val nameViewModel: NameViewModel by viewModels()

    lateinit var receiver: MyReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        receiver = MyReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            // registering the receiver
            // it parameter which is passed in  registerReceiver() function
            // is the intent filter that we have just created
            registerReceiver(receiver, it)
        }

        IntentFilter(Intent.ACTION_BATTERY_LOW).also {
            // registering the receiver
            // it parameter which is passed in  registerReceiver() function
            // is the intent filter that we have just created
            registerReceiver(receiver, it)
        }

        //observeViewModel()

        val text = findViewById<View>(R.id.eidText2) as EditText
        val backButtonForPreviousActivity = findViewById<View>(R.id.text2) as TextView
        val goToOtherApps = findViewById<View>(R.id.text3) as TextView
        val chatGPT = findViewById<View>(R.id.chatGPT) as Button
        chatGPT.setOnClickListener{
            val intent = Intent(this, ChatGPTTwoAPI::class.java)
            startActivity(intent)
        }

        // receive the value by getStringExtra() method and
        // key must be same which by first activity
        // receive the value by getStringExtra() method and
        // key must be same which by first activity
        val str = intent.getStringExtra("FirstActivityKey")
        // display the string into textView
        text.text = str?.toEditable()

        goToOtherApps.setOnClickListener {
            sendWhatsapp(text.text.toString())
            //injectLaunchIntent()
            //Toast.makeText(this, "There is no package available in android", Toast.LENGTH_LONG).show();
        }

        backButtonForPreviousActivity.setOnClickListener {
            val intent = Intent()
            intent.putExtra(Intent.EXTRA_TEXT, text.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }

    private fun observeViewModel() {
        nameViewModel.setvalue.observe(this) { price ->
            price?.let {
                Toast.makeText(this, "Got the observer result $it", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun sendWhatsapp(message: String) {
        val url = "https://api.whatsapp.com/send?phone=+8801739841696&text=$message"
        val i = Intent(Intent.ACTION_VIEW)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.data = Uri.parse(url)
        startActivity(i)
        // Toast.makeText(this, "There is no package available in android", Toast.LENGTH_LONG).show()
    }
}


fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
