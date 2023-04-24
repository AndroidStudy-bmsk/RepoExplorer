package org.bmsk.android_network_2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.bmsk.android_network_2.adapter.UserAdapter
import org.bmsk.android_network_2.databinding.ActivityMainBinding
import org.bmsk.android_network_2.model.Repo
import org.bmsk.android_network_2.model.UserDTO
import org.bmsk.android_network_2.network.GitHubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val gitHubService = retrofit.create(GitHubService::class.java)
        gitHubService.getListRepos("yibeomseok").enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.d("MainActivity", "List Repo: ${response.body().toString()}")
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

            }
        })

        val userAdapter = UserAdapter()

        val recyclerView = binding.userRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        gitHubService.getSearchUsers("squar").enqueue(object: Callback<UserDTO>{
            override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                Log.d("MainActivity", "Search User: ${response.body().toString()}")

                userAdapter.submitList(response.body()?.items)
            }

            override fun onFailure(call: Call<UserDTO>, t: Throwable) {

            }
        })
    }
}