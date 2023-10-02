package kr.co.fastcampus.part4plus.movieapp.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * <강의메모> 01:00 lec19
 * 확장함수 safeNavigate 기능은 유저가 여러번 눌렀을때
 * stack에 불필요하게 여러번 쌓이는 것을 방지해준다.
 *
 * 적절한 요청이 들어왔을 때에만 실행해준다.
 * safeNavigate통해 fragment->fragment로 이동하게 구현해보자.
 *
 * 그리고 이전 시간 만들었던 Effect를 이용해 볼 것이다!!
 */
fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}
