package com.example.demomovie.android.extension

import androidx.compose.animation.core.*
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.debugInspectorInfo

fun Modifier.shake(enable: Boolean) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "shake"
        value = enable
    }
) {


    val infiniteTransition = rememberInfiniteTransition()
    val animScale by infiniteTransition.animateFloat(
        initialValue = 1.6f,
        targetValue = .5f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val animRotate by infiniteTransition.animateFloat(
        initialValue = -50f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(300, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val scaleFloat by animateFloatAsState(targetValue = if (enable) animScale else 1f)
    val rotateFloat by animateFloatAsState(targetValue = if (enable) animRotate else 0f)

    graphicsLayer {
        scaleY = scaleFloat
        scaleX = scaleFloat
        rotationZ = rotateFloat
    }
}

