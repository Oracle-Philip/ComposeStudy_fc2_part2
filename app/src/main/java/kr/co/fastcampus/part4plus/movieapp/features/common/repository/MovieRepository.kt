package kr.co.fastcampus.part4plus.movieapp.features.common.repository

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.MovieDetailEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.network.api.IMovieAppNetworkApi
import kr.co.fastcampus.part4plus.movieapp.features.common.network.model.MovieResponse
import kr.co.fastcampus.part4plus.movieapp.features.feed.data.FeedConstants
import kr.co.fastcampus.part4plus.movieapp.features.feed.data.mapper.CategoryMapper
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.enum_.SortOrder
import kr.co.fastcampus.part4plus.movieapp.library.network.model.ApiResponse
import kr.co.fastcampus.part4plus.movieapp.library.storage.IStorage
import javax.inject.Inject

/**
 * MovieRepository는 DI로 네트워크 통신을 하는 API를 가져 올 것이고
 * 그 API에서는 통신을 할 것이다. 그래서 Data를 가져와서 뿌려줄 것이다.
 * Data를 가져와서 뿌려주는 MovieRepository에 대한 Interface를 먼저 만들어 본다.
 */

/**
 * DI를 사용해 API를 만들어 준다.
 * MovieAppNetworkApi를 만들어준다.
 */

/**
 * @Inject constructor( ) 의미
 * Inject constructor(  안에 있는 지정해준 모든 Instance.. 즉 parameter에 대해서
 * 해당 class안에서 사용할 수 있게 해준다는 의미이다.
 */

/**
 * <강의 메모> 03:10
 * library 패키지에 있는 class들은 네트워크 기능을 할 수 있는 class들을 모아둔 것이다.
 * 하지만 common/network 패키지에 있는 class는 네트워크 통신을 하기 위한 규약들을 모아둔 것이다.
 */

/**
 * DI를 사용하면 IMovieAppNetworkApi의 구현체를 가져올 수 있다.
 */

/**
 * <강의 메모> 01:45 ch17
 * categoryMapper는 response로 받아온 file을 category로 만드는데 사용된다.
 */

/**
 * <강의 메모> 04:11 ch17
 * EntityWrapper는 Data를 success or fail 할 수 있는데 그 성공 실패 여부를 가지고
 * 있는것은 Api Result이다. 이때 sealed class ApiResponse는 네트워크 계층을 고려해 구현되어 있다.
 * 이와 관련해 화면 계층에 success와 fail을 보여줄 수 있게 EntityWrapper를 만드는 것이다!!
 */
class MovieRepository @Inject constructor(
    private val movieNetworkApi : IMovieAppNetworkApi,
    private val storage : IStorage,
    private val categoryMapper : CategoryMapper
): IMovieDataSource {
    override suspend fun getMovieList(): List<MovieResponse>? {
        val data = movieNetworkApi.getMovies().response
        if (data is ApiResponse.Success){
            val movieList = data.data
            return movieList
        }
        return null
    }

    override suspend fun getCategories(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return categoryMapper.mapFromResult(
            result = movieNetworkApi.getMovies(),
            extra = sortOrder
        )
    }

    override suspend fun getMovieDetail(movieName: String): MovieDetailEntity {
/*        return storage
            .get<List<MovieDetailEntity>>(FeedConstants.MOVIE_LIST_KEY)
            ?.single { it.title == movieName }
            ?: MovieDetailEntity()*/
        return storage
            .get<List<MovieDetailEntity>>(
                FeedConstants.MOVIE_LIST_KEY)
                ?.single { it.title == movieName }
                ?: MovieDetailEntity()
    }
}