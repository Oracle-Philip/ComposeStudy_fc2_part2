package kr.co.fastcampus.part4plus.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kr.co.fastcampus.part4plus.movieapp.R
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogContent
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogText
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Alert(
    title : String,
    bodyText : String,
    buttons : List<DialogButton>
){
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header(text = title),
        dialogContent = DialogContent.Large(
            dialogText = DialogText.Default(
                text = bodyText
            )
        ),
        buttons = buttons
    )
}
//@Composable
//fun DialogPopup.Alert(
//    title : String? = null,
//    bodyText : String,
//    buttons : List<DialogButton>
//){
//    BaseDialogPopup(
//        dialogTitle = DialogTitle.Header(text = stringResource(id = R.string.app_name)),
//        dialogContent = DialogContent.Large(
//            dialogText = DialogText.Default(
//                text = bodyText
//            )
//        ),
//        buttons = buttons
//    )
//}