package kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output

sealed class FeedState {
    object Loading : FeedState()
    class Main(
        /**
         * Thumbnail에 보여지는 정보는 3개이다.
         * 영화 이미지, 영화이름, 별점
         */
        val movielist = List<MovieFeedItemEntity>
    ) : FeedState()
    class Failed(
        val reason : String
    ) : FeedState()
}