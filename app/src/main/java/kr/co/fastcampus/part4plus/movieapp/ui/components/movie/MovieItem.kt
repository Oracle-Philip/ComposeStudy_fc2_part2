package kr.co.fastcampus.part4plus.movieapp.ui.components.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4plus.movieapp.R
import kr.co.fastcampus.part4plus.movieapp.ui.theme.Paddings

private val CARD_WIDTH = 150.dp
private val ICON_SIZE = 12.dp
@Composable
fun movieItem(){
    Column(
        modifier = Modifier
            .width(CARD_WIDTH)
            .padding(
                Paddings.large)
    ){
        Poster(
            modifier = Modifier
                .width(CARD_WIDTH)
        )

        Text(
            text = "The Load the Ring 1",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(
                top = Paddings.large),
            style = MaterialTheme.typography.body2
        )

        Row(
            modifier = Modifier.padding(
                vertical = Paddings.medium),
            /**
             * Alignment
             * Row에 경우 수평방향인데,
             * 수평방향에 대해서 끝에 쌓아라, 앞에쌓아라 지정을 해주는 것. --> Arrangement
             * 위 아래로 쌓아라 지정해주는것 --> Alignment
             * 위 아래로 중앙정렬 해라... 예) ★ 9.0점
             */
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .padding(Paddings.small)
                    .size(ICON_SIZE),
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.ic_rating
                ),
                tint = Color.Black.copy(
                    alpha = 0.5f
                ),
                contentDescription = "rating icon"
            )
            Text(
                text = "5.0",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(
                    alpha = 0.5f
                )
            )
        }
    }
}

@Composable
fun Poster(
    modifier: Modifier
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ){
        Box(
            modifier = Modifier.background(Color.Blue)
        )
    }
}

@Preview
@Composable
fun MovieItemPreview(){
    movieItem()
}