package com.example.therapet.app.ui.components.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics

@Composable
fun NeedBar(
    modifier: Modifier = Modifier,
    width: Dp = 16.dp,
    fillFraction: Float = 0.5f,
    testTag: String
) {
    Box(
        modifier = modifier
            .width(width)
            .border(2.dp, Color.White, RoundedCornerShape(4.dp))
            .background(Color.White, RoundedCornerShape(4.dp))

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fillFraction)
                .align(Alignment.BottomCenter)
                .background(Color.Red, RoundedCornerShape(4.dp))
                .testTag(testTag)
                .semantics {
                    //FOR TESTING
                    progressBarRangeInfo = ProgressBarRangeInfo(
                        current = fillFraction,
                        range = 0f..1f,
                        steps = 0
                    )
                }
        )
    }
}