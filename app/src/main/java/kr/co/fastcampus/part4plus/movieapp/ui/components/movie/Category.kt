package kr.co.fastcampus.part4plus.movieapp.ui.components.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part4plus.movieapp.features.common.entity.CategoryEntity
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.input.IFeedViewModelInput
import kr.co.fastcampus.part4plus.movieapp.ui.theme.MovieAppTheme
import kr.co.fastcampus.part4plus.movieapp.ui.theme.Paddings


@Composable
fun CategoryRow(
    categoryEntity: CategoryEntity,
    input : IFeedViewModelInput
){
    /**
     * Type 이름 예) Action, Comedy...
     * list --> Row...수평방향으로
     */
    Column(

    ){
//        CategoryTitle(titleName = "Action")
        CategoryTitle(categoryEntity.genre)
        LazyRow(
            /**
             * Item 하나가 가지는 패딩보다 더 큰 패딩...
             */
            contentPadding = PaddingValues(
                horizontal = Paddings.large
            )
        ){
            /**
             * for문이 아니라 items를 사용해야 한다. itemIndexed나...
             */
            /*item{
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
            }*/
            /**
             * <강의 메모> 09:29 ch18
             */
            itemsIndexed(categoryEntity.movieFeedEntities){
                index, item ->
                MovieItem(
                    movie = item,
                    input = input
                )
            }
        }
    }
}

@Composable
fun CategoryTitle(titleName: String) {
    Text(
        text = titleName,
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
        //CategoryRow()
    }
}