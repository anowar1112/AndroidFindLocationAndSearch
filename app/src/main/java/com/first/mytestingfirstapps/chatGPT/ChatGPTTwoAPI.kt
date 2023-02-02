package com.first.mytestingfirstapps.chatGPT

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.first.mytestingfirstapps.R
import com.first.mytestingfirstapps.toEditable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatGPTTwoAPI : AppCompatActivity() {
    lateinit var backButtonForPreviousActivity: TextView
    lateinit var textView:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chat_gpttwo_api)

        textView = findViewById<View>(R.id.eidTextChatGPT2) as EditText
        backButtonForPreviousActivity = findViewById<View>(R.id.textChatGPT2) as TextView
        val chatGPT = findViewById<View>(R.id.chatGPT2) as Button
        chatGPT.setOnClickListener{
            extracted()
        }
    }

      /*
       * Here are the steps to sign up for an API key:
       * Go to the OpenAI website: https://beta.openai.com/
       * Click on the "Sign up" button in the top right corner of the page.
       * Fill in the required information to create an account.
       * Verify your email address.
       * Log in to your OpenAI account.
       * Go to the API section of your account dashboard.
       * Generate an API key.
       * */

    private fun extracted() {
        backButtonForPreviousActivity.text = "Api on going".toEditable()

        //val apiKey = "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"
        val apiKey = "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"
        val accountKey = "org-7vGTmJEUFuSeC09lZi7Ekec5"

        val maxTokens = 1000
        val temperature = 0.5f
        val inputText = "I am amra. Who are you?"

        val openAiService = OpenAiService.service
        val call = openAiService.predict(apiKey, inputText, maxTokens, temperature)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null) {
                        val predictedOutput = apiResponse.text
                        backButtonForPreviousActivity.text = predictedOutput.toEditable()
                        // Update your UI here to display the predicted output
                    } else {
                        // Handle error cases here
                        backButtonForPreviousActivity.text = "Api Response null".toEditable()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                backButtonForPreviousActivity.text = "On Failed Response".toEditable()
                // Handle network errors here
            }
        })
    }
}