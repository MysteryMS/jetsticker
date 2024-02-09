package dev.pedropiva.jetsticker.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.pedropiva.jetsticker.ext.dashedBorder

@Composable
fun AddImageDashed(showTrayLabel: Boolean = false, onClick: () -> Unit) {
    Box {
        if (showTrayLabel) {
            Box(
                modifier = Modifier
                    .offset(y = (-10).dp)
                    .width(65.dp)
                    .height(20.dp)
                    .align(Alignment.TopCenter)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.primary)
                    .zIndex(1f)
            ) {
                Text(
                    text = "TRAY ICON",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .dashedBorder(BorderStroke(2.dp, Color.White), RoundedCornerShape(10), 6.dp, 8.dp)
                .clip(RoundedCornerShape(10))
                .clickable { onClick() }
        ) {
            Column(
                Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                )
                Text("Add image")
            }
        }
    }
}