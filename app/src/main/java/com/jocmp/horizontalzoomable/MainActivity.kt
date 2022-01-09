package com.jocmp.horizontalzoomable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.jocmp.horizontalzoomable.ui.theme.HorizontalZoomableTheme
import com.mxalbert.zoomable.Zoomable

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HorizontalZoomableTheme {
                Scaffold {
                    val drawableIds = listOf(
                        R.drawable.hendrick_avercamp,
                        R.drawable.pieter_de_hooch,
                        R.drawable.jacob_van_ruisdael
                    )
                    Box {
                        HorizontalPager(
                            count = drawableIds.size,
                            itemSpacing = 8.dp
                        ) { page ->
                            val drawableId = drawableIds[page]
                            Zoomable {
                                DrawableImage(drawableId = drawableId)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DrawableImage(@DrawableRes drawableId: Int) {
    val modifier = Modifier.fillMaxSize()
    val painter = painterResource(id = drawableId)
    val size = painter.intrinsicSize

    val imageModifier = if (size != Size.Unspecified) {
        Modifier
            .aspectRatio(size.width / size.height)
            .then(modifier)
    } else {
        modifier
    }

    Image(
        painter = painter,
        modifier = imageModifier,
        contentScale = ContentScale.Fit,
        contentDescription = null
    )
}
