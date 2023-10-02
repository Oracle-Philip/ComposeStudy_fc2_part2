package kr.co.fastcampus.part4plus.movieapp.features.feed.data.mapper

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.MovieDetailEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.mapper.BaseMapper
import kr.co.fastcampus.part4plus.movieapp.features.common.network.model.MovieResponse
import kr.co.fastcampus.part4plus.movieapp.features.feed.data.FeedConstants
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.enum_.SortOrder
import kr.co.fastcampus.part4plus.movieapp.library.storage.IStorage
import javax.inject.Inject

/**
 * <강의 메모> 07:48 ch17
 * CategoryMapper 역할...
 * MovieResponse를 받아서 CategoryEntity로 만들어주는 역할을 한다.
 * 네트워크에서 받은 정보를 Domain... 화면에 뿌려줄 수 있는 정보로 바꿔주는 역할을 한다.
 * 다양한 계층에서 사용할 수 있게..
 */
class CategoryMapper @Inject constructor(
    private val storage: IStorage
) : BaseMapper<List<MovieResponse>, List<CategoryEntity>>() {
    override fun getSuccess(
        model: List<MovieResponse>?,
        extra: Any?
    ): EntityWrapper.Success<List<CategoryEntity>> {
        return model?.let {
            EntityWrapper.Success(
                entity = mutableListOf<MovieDetailEntity>()
                    .apply {
                        addAll(model.map { it.toMovieDetailEntity() })
                    }
                    .also {
                        storage.save(FeedConstants.MOVIE_LIST_KEY, it)
                    }
                    .map {
                        it.toMovieThumbnailEntity()
                    }
                    .toCategoryList(if (extra is SortOrder) extra else SortOrder.RATING)
            )
        } ?: EntityWrapper.Success(
            entity = listOf()
        )
    }

    override fun getFailure(error: Throwable): EntityWrapper.Fail<List<CategoryEntity>> {
        return EntityWrapper.Fail(error)
    }
}
