package kr.co.fastcampus.part4plus.movieapp.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part4plus.movieapp.ui.components.dialog.components.button.DialogButtonsColumn
import kr.co.fastcampus.part4plus.movieapp.ui.components.dialog.components.content.DialogContentWrapper
import kr.co.fastcampus.part4plus.movieapp.ui.components.dialog.components.title.DialogTitleWrapper
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogContent
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogText
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogTitle
import kr.co.fastcampus.part4plus.movieapp.ui.theme.MovieAppTheme
import kr.co.fastcampus.part4plus.movieapp.ui.theme.Paddings
import kr.co.fastcampus.part4plus.movieapp.ui.theme.colorScheme

/**
 * Wrapper들은 Title, Content, button 정보를 가져와서
 * 어떤 Title이냐, 어떤 Content이냐, 어떤 Button이냐에 따라
 * 각각 어떠한 Composable을 보내줄지 결정을 하게 만드는데 필요한 역할을 한다
 */
@Composable
fun BaseDialogPopup(
    dialogTitle : DialogTitle? = null,
    dialogContent : DialogContent? = null,
    buttons : List<DialogButton>? = null
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = Paddings.none,
        backgroundColor = MaterialTheme.colorScheme.background,
        shape = MaterialTheme.shapes.large
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            dialogTitle?.let{
                DialogTitleWrapper(it)
            }
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(
                        start = Paddings.xlarge,
                        end = Paddings.xlarge,
                        bottom = Paddings.xlarge
                    )
            ){
                dialogContent?.let { DialogContentWrapper(it) }
                buttons?.let { DialogButtonsColumn(it) }
            }
        }
    }
}

@Preview
@Composable
fun BaseDialogHeaderPopupPreview(){
    MovieAppTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Header(text = "Title"),
            dialogContent = DialogContent.Large(
                dialogText = DialogText.Default(
                    text = "ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE"
                )
            ),
            buttons = listOf(
                DialogButton.Primary(
                    title = "Okay"
                )
            ),
        )
    }
}

@Preview
@Composable
fun BaseDialogLargePopupPreview(){
    MovieAppTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Large(text = "Title"),
            dialogContent = DialogContent.Default(
                dialogText = DialogText.Default(
                    text = "ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE"
                )
            ),
            buttons = listOf(
                DialogButton.Secondary(
                    title = "Okay"
                ),
                DialogButton.UnderlinedText(
                    title = "Cancel"
                )
            ),
        )
    }
}

@Preview
@Composable
fun BaseDialogRatingPopupPreview(){
    MovieAppTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Large(text = "Title"),
            dialogContent = DialogContent.Rating(
                dialogText = DialogText.Rating(
                    text = "Joker",
                    rating = 3.7f
                )
            ),
            buttons = listOf(
                DialogButton.Primary(
                    title = "Okay"
                ),
                DialogButton.Secondary(
                    title = "Cancel"
                )
            ),
        )
    }
}


