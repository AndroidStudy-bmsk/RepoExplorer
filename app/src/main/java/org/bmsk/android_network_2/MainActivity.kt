package org.bmsk.android_network_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.bmsk.android_network_2.model.Repo
import org.bmsk.android_network_2.network.GitHubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val gitHubService = retrofit.create(GitHubService::class.java)
        gitHubService.getListRepos("yibeomseok").enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.d("MainActivity", response.body().toString())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

            }
        })
    }
}