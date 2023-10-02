package kr.co.fastcampus.part4plus.movieapp.features.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4plus.movieapp.features.detail.domain.usecase.IGetMovieDetailUseCase
import kr.co.fastcampus.part4plus.movieapp.features.detail.presentation.input.IDetailViewModelInputs
import kr.co.fastcampus.part4plus.movieapp.features.detail.presentation.output.DetailUiEffect
import kr.co.fastcampus.part4plus.movieapp.features.detail.presentation.output.IDetailViewModelOutputs
import kr.co.fastcampus.part4plus.movieapp.features.detail.presentation.output.MovieDetailState
import javax.inject.Inject

/**
 * <강의 메모> 07:34 ch19
 * MovieDetailViewModel에도 Effect와 State가 있다.
 * Input, Output이 있는 것이다.
 *   참고) Interface IFeedViewModelOutput에서 처럼(feedState는 들어가는 것만, feedUiEffect는 나가는 것만)
 *
 *  MovieDetailViewModel의 역할은 이름으로 저장된 것들중 찾아서 가져오는 것이다. 상세정보...
 *  이는 IGetMovieDetailUseCase를 통해서 repostory로부터 정보를 가져온다.
 *
 *  마찬가지로 UseCase Interface와 그 구현체를 구현한다.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: IGetMovieDetailUseCase
) : ViewModel(), IDetailViewModelInputs, IDetailViewModelOutputs {

    val inputs: IDetailViewModelInputs = this
    val outputs: IDetailViewModelOutputs = this

    private val _detailState: MutableStateFlow<MovieDetailState> =
        MutableStateFlow(MovieDetailState.Initial)
    override val detailState: StateFlow<MovieDetailState>
        get() = _detailState

    private val _detailUiEffect = MutableSharedFlow<DetailUiEffect>(replay = 0)
    override val detailUiEffect: SharedFlow<DetailUiEffect>
        get() = _detailUiEffect

    suspend fun initMovieName(movieName: String) {
        _detailState.value = MovieDetailState.Main(
            movieDetailEntity = getMovieDetailUseCase(movieName)
        )
    }

    /**
     * 뒤로 가는 역할
     */
    override fun goBackToFeed() {
        viewModelScope.launch {
            _detailUiEffect.emit(
                DetailUiEffect.Back
            )
        }
    }

    /**
     * Imdb 다이얼로그 띄우기
     */
    override fun openImdbClicked() {
        viewModelScope.launch {
            if (detailState.value is MovieDetailState.Main) {
                val value = detailState.value as MovieDetailState.Main
                _detailUiEffect.emit(
                    DetailUiEffect.OpenUrl(
                        value.movieDetailEntity.imdbPath
                    )
                )
            }
        }
    }

    /**
     * rate 다이얼로그 띄우기
     */
    override fun rateClicked() {
        viewModelScope.launch {
            if (detailState.value is MovieDetailState.Main) {
                val value = detailState.value as MovieDetailState.Main
                _detailUiEffect.emit(
                    DetailUiEffect.RateMovie(
                        movieTitle = value.movieDetailEntity.title,
                        rating = value.movieDetailEntity.rating
                    )
                )
            }
        }
    }
}
