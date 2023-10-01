package kr.co.fastcampus.part4plus.movieapp.features.common.network.api

import com.google.gson.reflect.TypeToken
import kr.co.fastcampus.part4plus.movieapp.features.common.network.model.MovieResponse
import kr.co.fastcampus.part4plus.movieapp.library.network.model.ApiResult
import kr.co.fastcampus.part4plus.movieapp.library.network.retrofit.NetworkRequestFactory
import javax.inject.Inject

/**
 * <강의 메모> 05:07
 * @Inject constructor( private val networkRequestFactory: NetworkRequestFactory 역할
 * 통신에 대한 정보를 넣어주는 부분
 */
class MovieAppNetworkApi @Inject constructor(
    private val networkRequestFactory: NetworkRequestFactory
) : IMovieAppNetworkApi {
    override suspend fun getMovies(): ApiResult<List<MovieResponse>> {
        return networkRequestFactory.create(
            url = "top250.json",
            type = object : TypeToken<List<MovieResponse>>(){}.type
        )
    }
}