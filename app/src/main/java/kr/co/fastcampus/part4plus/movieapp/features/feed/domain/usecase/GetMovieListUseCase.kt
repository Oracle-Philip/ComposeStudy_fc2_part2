package kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.fastcampus.part4plus.movieapp.features.common.network.model.MovieResponse
import kr.co.fastcampus.part4plus.movieapp.features.common.repository.IMovieDataSource
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.enum_.SortOrder
import kr.co.fastcampus.part4plus.movieapp.library.network.model.ApiResponse
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    val dataSource : IMovieDataSource
): IGetMovieListUseCase{
    override suspend fun invoke(): List<MovieResponse>? =
        dataSource.getMovieList()
}
