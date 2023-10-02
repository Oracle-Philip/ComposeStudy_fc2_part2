package kr.co.fastcampus.part4plus.movieapp.features.common.repository

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.MovieDetailEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.network.model.MovieResponse
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.enum_.SortOrder

/**
 * Repository도 DataSource의 한 예이기 때문에
 */
interface IMovieDataSource {
    suspend fun getMovieList() : List<MovieResponse>?

    suspend fun getCategories(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
    suspend fun getMovieDetail(movieName: String): MovieDetailEntity
}