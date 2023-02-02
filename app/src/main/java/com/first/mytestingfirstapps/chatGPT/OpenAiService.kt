package com.first.mytestingfirstapps.chatGPT

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface OpenAiService {
    @FormUrlEncoded
    @POST("/v1/engines/text-davinci/jobs")
    fun predict(@Field("apiKey") apiKey: String,
                @Field("prompt") prompt: String,
                @Field("max_tokens") maxTokens: Int,
                @Field("temperature") temperature: Float): Call<ApiResponse>

    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: OpenAiService = retrofit.create(OpenAiService::class.java)
    }
}