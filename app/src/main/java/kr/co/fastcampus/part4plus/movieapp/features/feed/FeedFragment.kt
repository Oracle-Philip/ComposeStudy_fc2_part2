package kr.co.fastcampus.part4plus.movieapp.features.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel.FeedViewModel
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

        viewModel.getMoviews()

        return ComposeView(requireContext()).apply {
            setContent {
                MovieAppTheme {
                    Text(
                        text = "FeedFragment"
                    )
                }
            }
        }
    }
}