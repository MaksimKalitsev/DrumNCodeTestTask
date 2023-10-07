package ua.zp.drumncodetesttask.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ua.zp.drumncodetesttask.Config
import ua.zp.drumncodetesttask.data.network.response.ServerResponse

interface Api {
    @GET("?method=flickr.interestingness.getList&format=json&nojsoncallback=1&api_key=${Config.API_KEY}")
    suspend fun fetchPopularImages(
        @Query("page") page: Int? = null,
        @Query("per_page") perPage: Int? = 100,
    ): ServerResponse
}