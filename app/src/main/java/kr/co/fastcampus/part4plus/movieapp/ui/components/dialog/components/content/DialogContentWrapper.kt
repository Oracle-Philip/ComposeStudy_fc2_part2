package kr.co.fastcampus.part4plus.movieapp.ui.components.dialog.components.content

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.em
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogContent
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogText
import kr.co.fastcampus.part4plus.movieapp.ui.theme.Paddings

data class DialogContentStyle(
    val textStyle : @Composable () -> TextStyle = { MaterialTheme.typography.body1 },
    val contentTopPadding : Dp = Paddings.xlarge,
    val contentBottomPadding : Dp = Paddings.extra
)

/**
 * 위 class로 정의해주고
 * compositionLocal로 정의해준다.
 */
val LocalDialogContentStyle = compositionLocalOf { DialogContentStyle() }
@Composable
fun ColumnScope.DialogContentWrapper(content : DialogContent) {
    when(content){
        is DialogContent.Default -> {
            CompositionLocalProvider(
                LocalDialogContentStyle provides DialogContentStyle(
                    textStyle = {
                        MaterialTheme.typography.body2.copy(
                            lineHeight = 1.6.em
                        )
                    },
                    contentTopPadding = Paddings.small,
                    contentBottomPadding = Paddings.extra
                )
            ) {
                NormalTextContent(content.dialogText)
            }
        }
        is DialogContent.Large -> {
            CompositionLocalProvider(
                LocalDialogContentStyle provides DialogContentStyle(
                    textStyle = {
                        MaterialTheme.typography.subtitle1.copy(
                            lineHeight = 1.6.em
                        )
                    },
                    contentTopPadding = Paddings.extra,
                    contentBottomPadding = Paddings.extra
                )
            ) {
                NormalTextContent(content.dialogText)
            }
        }
        is DialogContent.Rating -> {
            RatingContent(content.dialogText)
        }
    }
}

@Composable
fun ColumnScope.NormalTextContent(text: DialogText) {
    Text(
        text = getStringFromDialogText(text.text),
        modifier = Modifier
            /**
             * ColumnScope가 아니기 때문에
             * .align(Alignment.CenterHorizontally) 사용할 수 없고
             * 따라서 ColumnScope.NormalTextContent 와 같이 확장함수로 재정의한다.
             */

            /**
             * 복습>
             * Column의 방향은 위에서 아래로 이다.
             * Column의 방향과 같이 위에서 아래로 내려오는걸
             * Arrangement라고 한다.
             * Alignment는 반대이다.. 왼쪽에서 오른쪽
             * 즉, .align(Alignment.CenterHorizontally)은 중간정렬...
             * 왼쪽에서 오른쪽으로
             * 가운데 정렬겠다는 의미이다.
             */
            .align(Alignment.CenterHorizontally)
            .padding(
                top = LocalDialogContentStyle.current.contentTopPadding,
                bottom = LocalDialogContentStyle.current.contentBottomPadding
            ),
        textAlign = TextAlign.Center,
        style = LocalDialogContentStyle.current.textStyle.invoke()
    )
}

fun getStringFromDialogText(text : String?) : String{
    return text?.let{
        it
    } ?: ""
}

@Composable
fun getStringFromDialogText(text : DialogText) : String{
    return text.id?.let{
        stringResource(id = it)
    } ?: text.text ?: ""
}
