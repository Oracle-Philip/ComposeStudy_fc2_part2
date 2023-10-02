package kr.co.fastcampus.part4plus.movieapp.features.detail.presentation.output

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.MovieDetailEntity
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output.FeedState

sealed class MovieDetailState {
    /**
     * <강의 메모> 08:25 ch19
     * 저장된 정보를 가져오기 때문에 Loading이 아니라 Initial로 시작한다
     *  <비교> sealed class FeedState { object Loading : FeedState()
     * 그리고 class Failed(val reason : String) : FeedState() 가 불필요하다
     * --> Failed는 Data를 못가져왔다는거고 결국 상세화면으로 들어갈 수 없다는 거니까
     */
    object Initial : MovieDetailState()
    class Main(val movieDetailEntity: MovieDetailEntity) : MovieDetailState()
}


