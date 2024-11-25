package com.example.oneupfarm.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.oneupfarm.R

@Composable
fun OUFBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_background_login),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        content()
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 917)
@Composable
fun GradientBoxPreview() {
    OUFBackground {

    }
}
