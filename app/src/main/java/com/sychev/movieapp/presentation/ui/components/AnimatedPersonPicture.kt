package com.sychev.movieapp.presentation.ui.components

import android.graphics.Bitmap
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.sychev.movieapp.R
import com.sychev.movieapp.util.loadPictureFromTMDB

@Composable
fun AnimatedPersonPicture(
    transitionState: MutableTransitionState<PictureState>,
    bitmap: Bitmap,
    onClick: () -> Unit
){
    val transition = updateTransition(targetState = transitionState.targetState)

    val fractionWidth = transition.animateFloat(
        transitionSpec = {
            tween(1000)
        }
    ) {
        when (it) {
            PictureState.SMALL -> 0.3f
            PictureState.BIG -> 1f
        }
    }

    val fractionHeight = transition.animateFloat(
        transitionSpec = {
            tween(1000)
        }
    ) {
        when (it) {
            PictureState.SMALL -> 0.35f
            PictureState.BIG -> 1f
        }
    }

        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(fractionWidth.value)
                .fillMaxHeight(fractionHeight.value)
                .clickable {
                    onClick()
                }
        )


}

enum class PictureState{
    SMALL, BIG
}