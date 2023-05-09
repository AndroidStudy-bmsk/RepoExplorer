package org.bmsk.android_network_2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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
    private lateinit var userAdapter: UserAdapter
    private var searchFor = ""
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUserAdapter()
        setupSearchEditText()
    }

    private fun setupUserAdapter() {
        userAdapter = UserAdapter {
            val intent = Intent(this@MainActivity, RepoActivity::class.java)
            intent.putExtra("username", it.username)
            startActivity(intent)
        }

        binding.userRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    private fun setupSearchEditText() {
        val runnable = Runnable {
            searchUser()
        }
        binding.searchEditText.addTextChangedListener {
            searchFor = it.toString()
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 300)
        }
    }

    private fun searchUser() {
        val gitHubService = APIClient.retrofit.create(GitHubService::class.java)

        gitHubService.getSearchUsers(searchFor).enqueue(object : Callback<UserDTO> {
            override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                userAdapter.submitList(response.body()?.items)
            }

            override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                Toast.makeText(this@MainActivity, R.string.error_awake, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }
}