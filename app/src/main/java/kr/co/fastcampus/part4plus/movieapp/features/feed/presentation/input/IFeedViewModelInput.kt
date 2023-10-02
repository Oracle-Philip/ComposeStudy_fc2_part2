package kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.input

/**
 * Input을 통해서는 Data를 받아올 것이다.
 *
 * Input, Output은 Interface를 통해서 viewModel에서 상속을 받을 것이다.
 */
interface IFeedViewModelInput {
    fun openDetail(movieName : String)
    fun openInfoDialog()

    /**
     * <강의 메모>  27:45 ch16
     *
     * Interface IFeedViewModelInput에 경우
     * 기존 sealed class FeedUiEffect와 유사한 점이 있다.
     * 차이점은 refreshFeed()인데,
     *
     * Effect를 사용하는 이유는
     * Effect를 사용해서 viewModel단에서 view단까지 움직여야 한다.
     * 동작을 트리거 하기 위해서 function과 Effect를 같이 사용하는 거였다.
     *
     * refreshFeed()는 viewModel단에서 처리가 된다. viewModel 밑에 있는
     * repository를 refresh하면 되는거니까 굳이 Effect를 사용할 필요가 없는거였다.
     *
     * 그래서 Input에는 refreshFeed()가 있지만 Effect에는 없어도 되는 것이다!!
     */
    fun refreshFeed()
}