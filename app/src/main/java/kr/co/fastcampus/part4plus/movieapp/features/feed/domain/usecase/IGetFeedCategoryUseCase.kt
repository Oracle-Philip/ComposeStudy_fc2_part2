package kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.enum_.SortOrder

/**
 * <강의 메모> 16:51 ch17
 * invoke()를 통해서 대부분 useCase를 처리한다.
 * 왜냐하면 하나의 useCase 당 무조건 처리해야하는 일이 하나이기 때문이다.
 * .invoke() 없이 소괄호 ()만으로 해보자. 단, 소괄호 만으로 처리하려면
 * operator를 붙혀야 한다!!
 */
interface IGetFeedCategoryUseCase {
    /**
     * return type이 EntityWrapper<List<CategoryEntity>> 이유는
     * repository단에서 가져오는 Data Tpye이 EntityWrapper<List<CategoryEntity>>이기 때문이다.
     * 이를 통해 useCase를 통해 viewModel에 가져오려 한다.
     *
     * mvvm 형태 : ViewModel -> UseCase -> Repository
     */

    /**
     * <강의 메모> 17:39 ch17
     * 이를 구현하는 UseCase class에서는 DI를 통해 repository를 불러와서 구현할 것이다.
     */
    suspend operator fun invoke(sortOrder: SortOrder ?= null) : EntityWrapper<List<CategoryEntity>>
}
