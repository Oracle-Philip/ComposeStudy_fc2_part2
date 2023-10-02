package kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.EntityWrapper
import kr.co.fastcampus.part4plus.movieapp.features.common.network.model.MovieResponse
import kr.co.fastcampus.part4plus.movieapp.features.common.repository.IMovieDataSource
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase.IGetFeedCategoryUseCase
import kr.co.fastcampus.part4plus.movieapp.features.feed.domain.usecase.IGetMovieListUseCase
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.input.IFeedViewModelInput
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output.FeedState
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output.FeedUiEffect
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output.IFeedViewModelOutput
import javax.inject.Inject

/**
 * <강의 메모> 05:24
 * android mvvm
 * view가 viewmodel 참조
 * viewmodel은 repository 참조
 * https://static.blog.gangnamunni.com/files/41402906-e322-4ad7-88fa-84098d9e028a
 *
 * mvvm clean architecture
 * 관심사 분리
 * 각 class들의 역할 분리
 * https://miro.medium.com/v2/resize:fit:1400/0*5eJUx2N-5IKoIJNO.png
 *
 * android mvvm과 mvvm clean architecture 간에 차이가 있다면
 * 가운데 domain(UseCase) layer가 있다는 것이다.
 * domain layer는 UseCase를 의미하며
 * <<UseCase>>는 domain과 viewModel(repository로부터 가져온 data를 화면에 보여주기 위한 것)과 repository(그냥 data) 사이에서
 * 어떠한 동작을 하도록 하는것
 * 특정한 data만 가져오도록 한다든지, 특정한 동작을 통해서 일종의 비지니스 로직을 만들어가지고 view(activity, fragment)에 가져오는
 * 동작을 하게 만드는 것
 *
 * https://objectivegroup.com/wp-content/uploads/2020/01/Camada-de-Dados-Data-Layer.png
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    /**
     * <강의 메모> 18:44 ch17
     * UseCase IGetFeedCategoryUseCase를 사용하기 전에는
     * IMovieDataSource Type에 movieRepository를 사용했지만,
     * 이제 이것을 주석 처리한다. 왜냐하면
     * IGetFeedCategoryUseCase를 구현한 UseCase class에서 똑같이
     * private val movieRepository: IMovieDataSource를 사용하기 때문에
     * UseCase class만 있으면 movieRepository를 사용할 수 있다.
     * 따라서 FeedViewModel에서 IMovieDataSource 직접 참조를 하지 않을 것이다.
     */
//    private val movieRepository: IMovieDataSource,
    /**
     * <강의 메모> 16:01 ch17
     * useCase를 추가한다.
     *
     * 16:43 useCase는 단 하나의 역할, 단 하나의 일을 수행하기 위한 class로
     * Interface와 이를 구현한 class로 나뉘어진다.
     * DI를 통해 처리해보자.
     */
    private val getFeedCategoryUseCase : IGetFeedCategoryUseCase,
    /**
     * <<강의 외적인 것>> 20:41 ch17 2023-10-02-03-55-PM
     * 이것은 강의 외적인 것으로 getMovieList()에 관한 UseCase 이다.
     */
    private val getMovieListUseCase : IGetMovieListUseCase
) : ViewModel(), IFeedViewModelOutput, IFeedViewModelInput {


    /**
     * FeedFragment에서
     * FeedScreen(feedStateHolder = , input = )를 나타내기 위해
     * IFeedViewModelOutput과 IFeedViewModelInput를 자기자신this를 이용해 전달 하려 한다.
     */
    val output : IFeedViewModelOutput = this
    val input : IFeedViewModelInput = this

    /**
     * Repository로부터 Data를 가져왔기 때문에
     * stateFlow를 이용해서 화면에 Data를 나타내보자.
     * stateFlow -> 상태를 가지고 있는 Flow이다.
     *
     * 처음 _feedState 프로퍼티에 MutableStateFlow 인스턴스를 부여하기 위해서는
     * MutableStateFlow(FeedState.Loading) 와 같이 초기값이 부여되어야 한다.
     */

    /**
     * 유저에게 Flow를 통한 state를 통해 화면에 Data를 보여준다.
     *
     * 추가설명> 28:25
     * 유저로부터 입력을 받아서 fragment 단에서 액션을 수행하기 위한 flow
     */
    private val _feedState : MutableStateFlow<FeedState> =
        MutableStateFlow(FeedState.Loading)

    override val feedState : StateFlow<FeedState>
        get() = _feedState

    /**
     * <강의 메모> 23:23 ch16
     * 유저로부터 입력을 받기 위한 Flow!!
     * 이때, MutableStateFlow가 아닌 MutableSharedFlow를 사용한다.
     * MutableSharedFlow는 기본값이 필요없다!!
     *
     * MutableSharedFlow에는 replay라는게 있다. Data를 몇번씩 반복해서 보여주냐는 의미!!
     */
    private val _feedUiEffect = MutableSharedFlow<FeedUiEffect>(replay = 0)
    override val feedUiEffect : SharedFlow<FeedUiEffect>
        get() = _feedUiEffect

    init{
        fetchFeed()
        //getMoviews()
    }

    /**
     * 이제까지 처리했던 영화의 Data를 viewModel로 가져오는 함수
     */
    private fun fetchFeed() {
        viewModelScope.launch {
            _feedState.value = FeedState.Loading

            /**
             * UseCase class의 메소드나 인스턴스가 아니다.
             * interface IGetFeedCategoryUseCase에 대해서
             * UseCase가 하나의 일만을 수행하기 위해 .invoke()를 사용하는데,
             * 소괄호 (, )만으로 대체하기 위해
             * suspend operator fun invoke()를 사용한 것이다.
             * 그걸 의미한다.
             * getFeedCategoryUseCase.invoke() 인것이다.
             */
            val categories = getFeedCategoryUseCase()
            _feedState.value = when(categories){
                is EntityWrapper.Success -> {
                    FeedState.Main(
                        categories = categories.entity
                    )
                }
                is EntityWrapper.Fail -> {
                    FeedState.Failed(
                        reason = categories.error.message ?: "Unknown Error"
                    )
                }
            }
        }
    }

    fun getMoviews() : List<MovieResponse>?{
        viewModelScope.launch {
            /**
             * 구현으로이동 — Go to Implementation
                Win: control + alt + B
                mac: command + alt + B
             */
            //movieRepository.getMovieList()
            /**
             * <<강의 외적인 것>>
             * 기존 movieRepository.getMovieList() 기능을 수행하는
             * UseCase Interface와 class를 구현해보자!!
             */

           val data = getMovieListUseCase()
        //return@launch data
        }
        return null
    }

    override fun openDetail(movieName: String) {
        /**
         * <강의 메모> 01:46 ch19
         * Effect를 본격적으로 사용해 볼 것이다.
         * Effect는 Flow여서 코루틴 스코프에서 사용한다!
         */
        viewModelScope.launch {
            /**
             * emit( value = )를 통해 값을 바꿔주자!!
              */
            _feedUiEffect.emit(
                value = FeedUiEffect.OpenMovieDetail(
                    movieName = movieName
                )
                /**
                 * 위와 같이 바꿔주고 FeedUiEffect를 받는 FeedFragment 코드를 수정해본다!!
                 * Fragment에서 네비게이션을 옮길 수 있기 때문이다.
                 */

                /**
                 * <강의 메모> 02:21 ch19
                 * 19강의에서 위 프로젝트가 MVI와 가까운 이유는
                 * 들어가는 데이터와 나가는 데이터의 방향이 다르기 때문이다...
                 *
                 * 즉, 들어가는거는 들어가는 것만 해주고.. 나가는거는 나가는 것만 해준다..
                 *
                 * Interface IFeedViewModelOutput에서
                 * feedState는 들어가는 것만 해준다...
                 * feedUiEffect는 나가는 것만 해준다...
                 *
                 * Uni Directional Flow 즉... 단방향 전송 말이다..
                 *
                 * FeedFragment에서는 observeUiEffects()라는 메소드를 사용 할 것이다!
                 */
            )
        }
    }

    override fun openInfoDialog() {
        TODO("Not yet implemented")
    }

    override fun refreshFeed() {
        TODO("Not yet implemented")
    }
}