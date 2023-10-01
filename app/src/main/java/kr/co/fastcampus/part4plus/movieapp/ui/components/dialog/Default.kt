package kr.co.fastcampus.part4plus.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogContent
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogText
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogTitle

object DialogPopup

@Composable
fun DialogPopup.Default(
    title : String,
    bodyText : String,
    buttons : List<DialogButton>
){
    BaseDialogPopup(
        dialogTitle = DialogTitle.Default(text = title),
        dialogContent = DialogContent.Default(
            dialogText = DialogText.Default(
                text = bodyText
            )
        ),
        buttons = buttons
    )
}
//@Composable
//fun DialogPopup.Alert(
//    title : String,
//    bodyText : String,
//    buttons : List<DialogButton>
//){
//    BaseDialogPopup(
//        dialogTitle = DialogTitle.Header(text = "Title"),
//        dialogContent = DialogContent.Large(
//            dialogText = DialogText.Default(
//                text = "ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE ABCDE"
//            )
//        ),
//        buttons = listOf(
//            DialogButton.Primary(
//                title = "Okay"
//            )
//        ),
//    )
//}