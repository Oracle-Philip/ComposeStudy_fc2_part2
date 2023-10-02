package kr.co.fastcampus.part4plus.movieapp.features.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output.FeedUiEffect
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.screen.FeedScreen
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel.FeedViewModel
import kr.co.fastcampus.part4plus.movieapp.ui.navigation.safeNavigate
import kr.co.fastcampus.part4plus.movieapp.ui.theme.MovieAppTheme

/**
 * <강의 메모>
 * part2_ch3_15 DI와 레트로핏 설정 강의에서는
 * gradle에 Hilt 의존성을 추가해 Hilt를 사용하고
 * viewModel도 생성해본다.
 *
 * Hilt에서는 viewModel을 지원해준다.
 * Hilt의 viewModel 기능들을 이용해보자.
 */
@AndroidEntryPoint
class FeedFragment : Fragment(){

    private val viewModel : FeedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * 어떤 view를 return할 것인지 명시해야한다.
         * ComposeView를 return 해보자.
         */
        //return super.onCreateView(inflater, container, savedInstanceState)
//        viewModel.getMoviews()

        /**
         * <강의 메모> 05:50 ch19
         * observeUiEffects()를 call하자.
         */
        observeUiEffects()

        return ComposeView(requireContext()).apply {
            setContent {
                MovieAppTheme {
                    /*Text(
                        text = "FeedFragment"
                    )*/
                    /**
                     * <강의 메모> 08:34 ch18
                     * Type mismatch. Required: State<FeedState> Found: StateFlow<FeedState>
                     * 에러가 발생된다. FeedScreen( viewModel.output.feedState, 하면 말이다.
                     * feedStateHolder는 feedStateHolder: State<FeedState>,로써
                     * Jetpack compose에서 사용하는 상태를 update해주기 위한 State이다.
                     *
                     * Jetpack compose에서 사용하는 State와 StateFlow에서 사용하는 State는 다른 것이다.
                     *
                     * 하지만 StateFlow에서의 State는
                     * Jetpack compose에서 사용하는 State로 바꿀 수 있다.
                     *
                     * 바로 collectAsState()를 통해서 말이다!!
                     */
                    FeedScreen(
                        feedStateHolder = viewModel.output.feedState.collectAsState(),
                        input = viewModel.input
                    )
                }
            }
        }
    }

    private fun observeUiEffects() {
        /**
         * <강의 메모> 03:01 ch19
         * findNavController()는 NavigationController를 가져온다.
         *  repeatOnLifecycle(Lifecycle.State.RESUMED) { 의미 --> State.RESUMED가 될때 (즉, emit이 되어서)
         *      viewModel.output.feedUiEffect.collectLatest --> 가장 최근에 들어온 값을 가져온다는 의미
         */
        val navController = findNavController()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.output.feedUiEffect.collectLatest {
                    when (it) {
                        is FeedUiEffect.OpenMovieDetail -> {
                            navController.safeNavigate(
                                FeedFragmentDirections.actionFeedToDetail(it.movieName)
                            )
                        }

                        is FeedUiEffect.OpenInfoDialog -> {
//                            navController.safeNavigate(
//                                FeedFragmentDirections.actionFeedToInfo()
//                            )
                        }
                    }
                }
            }
        }
    }
}