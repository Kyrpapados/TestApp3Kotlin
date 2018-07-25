package kyrpap.testapp3kotlin.data.network

import io.reactivex.Single
import kyrpap.testapp3kotlin.data.model.responce.TrendsResponse
import retrofit2.http.GET


interface TrendsApiInterface {

    @GET("search/repositories?q=android&sort=stars&order=asc")
    fun getTrends(): Single<TrendsResponse>

}