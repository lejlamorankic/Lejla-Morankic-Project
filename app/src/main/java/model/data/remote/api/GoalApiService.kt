package model.data.remote.api

import model.data.remote.dto.GoalDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GoalApiService {

    @GET("posts")
    suspend fun getGoals(): List<GoalDto>

    @POST("posts")
    suspend fun createGoal(
        @Body goal: GoalDto
    ): GoalDto

    @PUT("posts/{id}")
    suspend fun updateGoal(
        @Path("id") id: Int,
        @Body goal: GoalDto
    ): GoalDto

    @DELETE("posts/{id}")
    suspend fun deleteGoal(
        @Path("id") id: Int
    )
}