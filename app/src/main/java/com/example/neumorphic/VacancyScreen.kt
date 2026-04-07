package com.example.neumorphic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neumorphic.theme.ComposeTheme

@Composable
fun VacancyScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F5))
    ) {
        // Decorative background text "VACANCY"
        Box(modifier = Modifier.fillMaxSize().alpha(0.03f)) {
            Text(
                text = "V",
                fontSize = 500.sp,
                fontWeight = FontWeight.Black,
                color = Color.Gray,
                modifier = Modifier.offset(x = (-40).dp, y = 100.dp)
            )
            Text(
                text = "A",
                fontSize = 500.sp,
                fontWeight = FontWeight.Black,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.TopEnd).offset(x = 180.dp, y = 250.dp)
            )
            Text(
                text = "C",
                fontSize = 500.sp,
                fontWeight = FontWeight.Black,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterStart).offset(x = (-100).dp, y = 300.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = stringResource(R.string.current_vacancies),
                    fontSize = 54.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 52.sp,
                    color = Color.Black,
                    letterSpacing = (-1.5).sp
                )
                
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterIcon(modifier = Modifier.size(16.dp))
                        Text(
                            text = stringResource(R.string.filters),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = stringResource(R.string.information_technologies),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF8E8E93),
                modifier = Modifier.align(Alignment.End),
                lineHeight = 20.sp
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                // Stacked cards
                CardPlaceholder(
                    offsetY = 0.dp,
                    color = Color(0xFFAEE64B),
                    scale = 0.82f
                )
                CardPlaceholder(
                    offsetY = 35.dp,
                    color = Color(0xFFE5E2D0),
                    scale = 0.88f
                )
                CardPlaceholder(
                    offsetY = 70.dp,
                    color = Color(0xFFF9A8A8),
                    scale = 0.94f
                )
                
                // Main foreground card
                VacancyCard(
                    modifier = Modifier
                        .padding(top = 105.dp)
                        .fillMaxWidth()
                )
            }
        }
        
        // Bottom floating button
        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 32.dp, end = 24.dp)
                .size(80.dp),
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 12.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                ChatIcon(modifier = Modifier.size(40.dp))
            }
        }
    }
}

@Composable
fun FilterIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val strokeWidth = 2.dp.toPx()
        // Line 1
        drawLine(
            color = Color.Black,
            start = Offset(0f, size.height * 0.35f),
            end = Offset(size.width, size.height * 0.35f),
            strokeWidth = strokeWidth
        )
        drawCircle(
            color = Color.Black,
            radius = 2.dp.toPx(),
            center = Offset(size.width * 0.3f, size.height * 0.35f)
        )
        // Line 2
        drawLine(
            color = Color.Black,
            start = Offset(0f, size.height * 0.65f),
            end = Offset(size.width, size.height * 0.65f),
            strokeWidth = strokeWidth
        )
        drawCircle(
            color = Color.Black,
            radius = 2.dp.toPx(),
            center = Offset(size.width * 0.7f, size.height * 0.65f)
        )
    }
}

@Composable
fun ChatIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            addOval(Rect(Offset(0f, 0f), Size(size.width, size.height * 0.85f)))
            moveTo(size.width * 0.7f, size.height * 0.75f)
            lineTo(size.width * 0.85f, size.height)
            lineTo(size.width * 0.9f, size.height * 0.7f)
            close()
        }
        drawPath(path, Color.Black)
        
        val dotRadius = 2.dp.toPx()
        val dotY = size.height * 0.42f
        drawCircle(Color.White, dotRadius, Offset(size.width * 0.35f, dotY))
        drawCircle(Color.White, dotRadius, Offset(size.width * 0.5f, dotY))
        drawCircle(Color.White, dotRadius, Offset(size.width * 0.65f, dotY))
    }
}

@Composable
fun CardPlaceholder(
    offsetY: androidx.compose.ui.unit.Dp,
    color: Color,
    scale: Float
) {
    Surface(
        modifier = Modifier
            .offset(y = offsetY)
            .fillMaxWidth(scale)
            .height(140.dp),
        shape = RoundedCornerShape(48.dp),
        color = color
    ) {}
}

@Composable
fun VacancyCard(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(480.dp),
        shape = RoundedCornerShape(56.dp),
        color = Color(0xFF76D1E3)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.White.copy(alpha = 0.4f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                   CloudIcon(modifier = Modifier.size(32.dp))
                }
                
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                     Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color.Black.copy(alpha = 0.12f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        HeartIcon(modifier = Modifier.size(28.dp))
                    }
                     Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color.Black.copy(alpha = 0.12f), CircleShape)
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = stringResource(R.string.salesforce),
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = stringResource(R.string.regional_vice_president),
                color = Color.Black,
                fontSize = 46.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 48.sp,
                letterSpacing = (-1).sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = stringResource(R.string.salary_range),
                color = Color.Black,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            Surface(
                onClick = {},
                color = Color(0xFF65C1D3).copy(alpha = 0.6f),
                shape = RoundedCornerShape(24.dp)
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.see_details),
                        color = Color.Black.copy(alpha = 0.5f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CloudIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val color = Color.White
        val w = size.width
        val h = size.height
        
        drawCircle(color, radius = w * 0.28f, center = Offset(w * 0.32f, h * 0.65f))
        drawCircle(color, radius = w * 0.38f, center = Offset(w * 0.52f, h * 0.45f))
        drawCircle(color, radius = w * 0.3f, center = Offset(w * 0.76f, h * 0.62f))
        
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.32f, h * 0.55f),
            size = Size(w * 0.44f, h * 0.28f),
            cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
        )
    }
}

@Composable
fun HeartIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val path = Path().apply {
            moveTo(w / 2f, h * 0.75f)
            cubicTo(w * 0.2f, h * 0.45f, w * 0.25f, h * 0.15f, w / 2f, h * 0.35f)
            cubicTo(w * 0.75f, h * 0.15f, w * 0.8f, h * 0.45f, w / 2f, h * 0.75f)
        }
        drawPath(path, Color.Black.copy(alpha = 0.35f), style = Stroke(width = 2.5.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun VacancyScreenPreview() {
    ComposeTheme {
        VacancyScreen()
    }
}