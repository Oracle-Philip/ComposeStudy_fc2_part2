package kr.co.fastcampus.part4plus.movieapp.features.common.repository

/**
 * Repository도 DataSource의 한 예이기 때문에
 */
interface IMovieDataSource {
    suspend fun getMovieList()
}