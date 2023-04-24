package org.bmsk.android_network_2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.bmsk.android_network_2.adapter.RepoAdapter
import org.bmsk.android_network_2.databinding.ActivityRepoBinding
import org.bmsk.android_network_2.model.Repo
import org.bmsk.android_network_2.network.GitHubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoAdapter: RepoAdapter

    private val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username") ?: return
        binding.userNameTextView.text = username

        repoAdapter = RepoAdapter()

        binding.repoRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@RepoActivity)
            adapter = repoAdapter
        }

        listRepo(username)
    }

    private fun listRepo(username: String) {
        val gitHubService = retrofit.create(GitHubService::class.java)
        gitHubService.getListRepos(username).enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.d("MainActivity", "List Repo: ${response.body().toString()}")

                repoAdapter.submitList(response.body())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

            }
        })
    }
}