package kr.co.fastcampus.part4plus.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part4plus.movieapp.R
import kr.co.fastcampus.part4plus.movieapp.ui.models.buttons.LeadingIconData
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.movieapp.ui.theme.MovieAppTheme

@Preview
@Composable
fun AlertPreview(){
    MovieAppTheme {
        DialogPopup.Alert(
            title = "test",
            bodyText = "1233",
            buttons = listOf(
                DialogButton.UnderlinedText(
                    title = "2112",
                )
            )
        )
    }
}

@Preview
@Composable
fun DefaultPreview(){
    MovieAppTheme {
        DialogPopup.Default(
            title = "TEST",
            bodyText = "1233",
            buttons = listOf(
                DialogButton.Primary(
                    title = "OPEN",
                ),
                DialogButton.SecondaryBorderless(
                    title = "CANCEL",
                )
            )
        )
    }
}

@Preview
@Composable
fun RatingPreview(){
    MovieAppTheme {
        DialogPopup.Rating(
            movieName = "TEST",
            rating = 1.2f,
//            bodyText = "1233",
            buttons = listOf(
                DialogButton.Primary(
                    leadingIconData = LeadingIconData(
                        iconDrawable = R.drawable.ic_send,
                        iconContentDescription = null
                    ),
                    title = "OPEN"
                ),
                DialogButton.SecondaryBorderless(
                    title = "CANCEL",
                )
            )
        )
    }
}