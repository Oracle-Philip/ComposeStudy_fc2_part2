package kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * output을 통해 화면단과 통신 할 것이다.
 * Input을 통해서는 Data를 받아올 것이다.
 */
interface IFeedViewModelOutput {
    val feedState : StateFlow<FeedState>
    val feedUiEffect : SharedFlow<FeedUiEffect>
}

/**
 * UiEffect는 사용자로부터 입력을 받았을때
 * UiEffect를 사용해서 효과를 만드는 것이다.
 */
sealed class FeedUiEffect {
    data class OpenMovieDetail(val movieName : String) : FeedUiEffect()
    object OpenInfoDialog : FeedUiEffect()
}