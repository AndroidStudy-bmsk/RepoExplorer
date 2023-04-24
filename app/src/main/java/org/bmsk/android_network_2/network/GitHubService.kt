package org.bmsk.android_network_2.network

import org.bmsk.android_network_2.TOKEN
import org.bmsk.android_network_2.model.Repo
import org.bmsk.android_network_2.model.UserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @Headers("Authorization: Bearer $TOKEN")
    @GET("/users/{username}/repos")
    fun getListRepos(
        @Path("username") username: String
    ): Call<List<Repo>>

    @Headers("Authorization: Bearer $TOKEN")
    @GET("/search/users")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserDTO>
}