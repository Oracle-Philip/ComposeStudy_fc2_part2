package kr.co.fastcampus.part4plus.chapter2.ui.content

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4plus.chapter2.R
import kr.co.fastcampus.part4plus.chapter2.model.memos

private val MinTitleOffset = 20.dp
private val MaxTitleOffset = 100.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun ContentScreen(memoId: Int) {
    val memo = remember(memos) { memos.single { it.id == memoId } }

    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Body(scroll)
        /**
         * scroll.value 가 아니라
         * {scroll.value}로 람다로 바꾼다
         */
        Title(memo.text, {scroll.value})
    }
}

@Composable
private fun Body(
    scroll: ScrollState
) {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaxTitleOffset)
        )
        Column(
            modifier = Modifier
                .verticalScroll(scroll)
        ) {
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(110.dp))
                    Text(
                        text = stringResource(R.string.detail_placeholder),
                        style = MaterialTheme.typography.body1,
                        color = Color.Black,
                        modifier = HzPadding
                    )

                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
/**
 * 상태읽기 연기 기법을 이용한 리팩토링
 *
 * scrollProvider의 return Type을 Int가 아니라
 * Int를 반환하는 람다로 바꾼다.
 *
 * 이 리팩토링을 통해 값을 직접 전해주지 않고 람다를 통해서 값을 전해줘서
 * recompsition이 발생하지 않는 거다.
 */
private fun Title(memoText: String, scrollProvider: () -> Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }

    Column(
        modifier = Modifier
            .heightIn(min = MaxTitleOffset)
            .offset {
                /**
                 * scrollProvider을 call 할 수 있게 scrollProvider()로 바꾼다.
                 */
                val offset = (maxOffset - scrollProvider()).coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(
            text = memoText,
            style = MaterialTheme.typography.h4,
            color = Color.Black,
            modifier = HzPadding
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "MEMO",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primaryVariant,
            modifier = HzPadding
        )

        Spacer(Modifier.height(8.dp))
    }
}
