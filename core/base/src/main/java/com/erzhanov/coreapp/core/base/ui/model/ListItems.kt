package com.erzhanov.coreapp.core.base.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.erzhanov.coreapp.core.base.R

interface ListItem {
    val id: String
}
data class ProgressItem(override val id: String): ListItem
sealed class PlaceHolderItem(
    @DrawableRes open val imageId: Int, @DrawableRes open val imageIdDark: Int, @StringRes open val textId: Int
) : ListItem {
    override val id: String = imageId.toString()

    fun getImage(isLight: Boolean) = imageId.takeIf { isLight } ?: imageIdDark

    data class Empty(
        @DrawableRes override val imageId: Int = R.drawable.ic_empty_list_image,
        @DrawableRes override val imageIdDark: Int = R.drawable.ic_empty_list_image_dark,
        @StringRes override val textId: Int = R.string.empty_list_msg
    ) : PlaceHolderItem(imageId, imageIdDark, textId)

    data class Error(
        @DrawableRes override val imageId: Int = R.drawable.ic_error_list_image,
        @DrawableRes override val imageIdDark: Int = R.drawable.ic_error_list_image_dark,
        @StringRes override val textId: Int = R.string.error_list_msg,
        @StringRes val btnTextId: Int = R.string.retry
    ) : PlaceHolderItem(imageId, imageIdDark, textId)
}