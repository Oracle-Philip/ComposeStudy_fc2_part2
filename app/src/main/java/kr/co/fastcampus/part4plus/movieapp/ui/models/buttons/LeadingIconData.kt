package kr.co.fastcampus.part4plus.movieapp.ui.models.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LeadingIconData(
    @DrawableRes val IconDrawable : Int,
    @StringRes val iconContentDescription : Int
)
