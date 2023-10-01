package kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4plus.movieapp.features.common.repository.IMovieDataSource
import kr.co.fastcampus.part4plus.movieapp.features.common.repository.MovieRepository
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
    private val movieRepository: IMovieDataSource
) : ViewModel() {

/*    init {
        viewModelScope.launch {
            movieRepository.getMovieList()
        }
    }*/

    fun getMoviews() {
        viewModelScope.launch {
            movieRepository.getMovieList()
        }
    }
}