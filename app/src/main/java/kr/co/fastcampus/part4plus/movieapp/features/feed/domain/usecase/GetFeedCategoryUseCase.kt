package kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.fastcampus.part4plus.movieapp.features.common.repository.IMovieDataSource
import kr.co.fastcampus.part4plus.movieapp.features.common.repository.MovieRepository
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.enum_.SortOrder
import javax.inject.Inject

/**
 * UseCase란 단 하나의 역할, 단 하나의 일을
 * 수행하기 위한 class 이다. 역시
 * Interface와 이를 구현한 Class로 나뉘어진다.
 */
class GetFeedCategoryUseCase @Inject constructor(
    /**
     * 주의 할점은 다이렉트로 val repository: MovieRepository를 가져오지 말고
     * interface IMovieDataSource를 사용한다...
     */
    private val dataSource: IMovieDataSource
): IGetFeedCategoryUseCase{
    override suspend fun invoke(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return dataSource.getCategories(sortOrder)
    }

}
