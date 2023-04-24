package org.bmsk.android_network_2.network

import org.bmsk.android_network_2.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("/users/{username}/repos")
    fun getListRepos(
        @Path("username") username: String
    ): Call<List<Repo>>
}