package kr.co.fastcampus.part4plus.movieapp.ui.components.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4plus.movieapp.ui.theme.MovieAppTheme
import kr.co.fastcampus.part4plus.movieapp.ui.theme.Paddings


@Composable
fun CategoryRow(){
    /**
     * Type 이름 예) Action, Comedy...
     * list --> Row...수평방향으로
     */
    Column(

    ){
        CategoryTitle(titleName = "Action")
        LazyRow(
            /**
             * Item 하나가 가지는 패딩보다 더 큰 패딩...
             */
            contentPadding = PaddingValues(
                horizontal = 10.dp
            )
        ){
            /**
             * for문이 아니라 items를 사용해야 한다. itemIndexed나...
             */
            item{
                movieItem()
            }
            item{
                movieItem()
            }
            item{
                movieItem()
            }
            item{
                movieItem()
            }
            item{
                movieItem()
            }
        }
    }
}

@Composable
fun CategoryTitle(titleName: String) {
    Text(
        text = "Action",
        modifier = Modifier
            .padding(
                vertical = Paddings.large,
                horizontal = Paddings.extra
            ),
        style = MaterialTheme.typography.h5
    )
}

@Preview
@Composable
fun CategoryRowPreview(){
    MovieAppTheme() {
        CategoryRow()
    }
}