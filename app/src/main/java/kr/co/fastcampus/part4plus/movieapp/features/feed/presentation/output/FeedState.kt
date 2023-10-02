package kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.MovieFeedItemEntity

sealed class FeedState {
    object Loading : FeedState()
    class Main(
        /**
         * Thumbnail에 보여지는 정보는 3개이다.
         * 영화 이미지, 영화이름, 별점
         */
//        val movieList : List<MovieFeedItemEntity>
        val categories : List<CategoryEntity>
    ) : FeedState()
    class Failed(
        val reason : String
    ) : FeedState()
}