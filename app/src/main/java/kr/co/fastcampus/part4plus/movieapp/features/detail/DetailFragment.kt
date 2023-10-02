package kr.co.fastcampus.part4plus.movieapp.features.detail

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
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4plus.movieapp.features.detail.presentation.MovieDetailScreen
import kr.co.fastcampus.part4plus.movieapp.features.detail.presentation.output.DetailUiEffect
import kr.co.fastcampus.part4plus.movieapp.features.detail.presentation.viewmodel.MovieDetailViewModel
import kr.co.fastcampus.part4plus.movieapp.ui.navigation.safeNavigate
import kr.co.fastcampus.part4plus.movieapp.ui.theme.MovieAppTheme

@AndroidEntryPoint
class DetailFragment : Fragment(){
    private val viewModel : MovieDetailViewModel by viewModels()
    private val args : DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        observeUiEffects()

        /**
         * argument를 받아오는 역할 수행
         */
        init()

        return ComposeView(requireContext()).apply {
            setContent {
                MovieAppTheme {
//                    Text(
//                        text = "DetailFragment"
//                    )
                    MovieDetailScreen(
                        //movieDetailState = viewModel.detailState.collectAsState(),
                        movieDetailState = viewModel.outputs.detailState.collectAsState(),
                        input = viewModel.inputs
                    )
                }
            }
        }
    }

    private fun init() {
//        viewModel.viewModelScope.launch {
//            viewModel.initMovieName(args.movieName)
//        }
        lifecycleScope.launch {
            viewModel.initMovieName(args.movieName)
        }
    }

    /**
     * <강의 메모> 20:30 ch19
     * DetailFragment에서는 observeUiEffects()를 통해서
     * DetailUiEffect.Back(뒤로가기), DetailUiEffect.OpenUrl(IMDB 다이얼로그 띄우기), DetailUiEffect.RateMovie(평점 다이얼로그 띄우기)
     * 를 수행한다..
     * 따라서, DialogFragment도 만들어줘야 한다..
     */

    private fun observeUiEffects() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.outputs.detailUiEffect.collectLatest {
                    when (it) {
                        is DetailUiEffect.Back -> {
                            findNavController().navigateUp()
                        }

                        /**
                         * <강의 메모> 27:55 ch19
                         * is DetailUiEffect.OpenUrl -> {   ,  is DetailUiEffect.RateMovie -> {
                         * 각각 DetailFragmentDirections.actionDetailToImdbDialog( ,
                         * DetailFragmentDirections.actionDetailToRating( 가 동작하기 위해서
                         * xml navGraph에 action을 명시해줘야 한다...
                         */

                        is DetailUiEffect.OpenUrl -> {
                            findNavController().safeNavigate(
                                DetailFragmentDirections.actionDetailToImdbDialog(
                                    it.url
                                )
                            )
                        }

                        is DetailUiEffect.RateMovie -> {
                            findNavController().safeNavigate(
                                DetailFragmentDirections.actionDetailToRating(
                                    movieName = it.movieTitle,
                                    rating = it.rating
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}