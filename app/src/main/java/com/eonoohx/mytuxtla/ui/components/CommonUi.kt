package com.eonoohx.mytuxtla.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.TextStyle

@Composable
fun ResizableText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.titleLarge,
) {
    var currentStyle by remember {
        mutableStateOf(style)
    }

    var shouldDraw by remember {
        mutableStateOf(false)
    }

    Text(text = text, maxLines = 1, softWrap = false, style = currentStyle, onTextLayout = { result ->
        if (result.didOverflowWidth) {
            currentStyle = currentStyle.copy(
                fontSize = currentStyle.fontSize * 0.95
            )
        } else {
            shouldDraw = true
        }
    }, modifier = modifier.drawWithContent { if (shouldDraw) drawContent() })
}
