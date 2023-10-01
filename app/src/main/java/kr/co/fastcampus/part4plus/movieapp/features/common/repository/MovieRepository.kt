package kr.co.fastcampus.part4plus.movieapp.features.common.repository

import kr.co.fastcampus.part4plus.movieapp.features.common.network.api.IMovieAppNetworkApi
import kr.co.fastcampus.part4plus.movieapp.library.network.model.ApiResponse
import timber.log.Timber
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
class MovieRepository @Inject constructor(
    private val movieNetworkApi : IMovieAppNetworkApi
): IMovieDataSource {
    override suspend fun getMovieList() {
        val data = movieNetworkApi.getMovies().response
        if (data is ApiResponse.Success){
            val movieList = data.data
        }
    }
}