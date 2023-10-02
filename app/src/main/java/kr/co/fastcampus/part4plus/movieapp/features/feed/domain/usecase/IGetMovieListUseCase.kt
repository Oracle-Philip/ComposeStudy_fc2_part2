package kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.fastcampus.part4plus.movieapp.features.common.network.model.MovieResponse
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.enum_.SortOrder
import kr.co.fastcampus.part4plus.movieapp.library.network.model.ApiResponse

interface IGetMovieListUseCase {
    suspend operator fun invoke() : List<MovieResponse>?
    /*override suspend fun getMovieList(): List<MovieResponse>? {
        val data = movieNetworkApi.getMovies().response
        if (data is ApiResponse.Success){
            val movieList = data.data
            return movieList
        }
        return null
    }
*/
}
