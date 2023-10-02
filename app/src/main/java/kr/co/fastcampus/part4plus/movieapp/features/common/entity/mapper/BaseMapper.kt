package kr.co.fastcampus.part4plus.movieapp.features.common.entity.mapper

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.fastcampus.part4plus.movieapp.library.network.model.ApiResponse
import kr.co.fastcampus.part4plus.movieapp.library.network.model.ApiResult

/**
 * abstract class... 일부만 구현되어 있고
 * 일부는 구현되어 있지 않은 class..
 *
 * abstract class BaseMapper<M, E> ->와 같이 제네릭 사용...
 */
abstract class BaseMapper<M, E> {

    fun mapFromResult(result: ApiResult<M>, extra: Any? = null): EntityWrapper<E> =
        when (result.response) {
            is ApiResponse.Success -> getSuccess(model = result.response.data, extra = extra)
            is ApiResponse.Fail -> getFailure(error = result.response.error)
        }

    abstract fun getSuccess(model: M?, extra: Any?): EntityWrapper.Success<E>

    abstract fun getFailure(error: Throwable): EntityWrapper.Fail<E>
}
