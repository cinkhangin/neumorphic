package com.ckgin.neumorphic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

interface Texture {
    fun DrawScope.draw()
}

fun Modifier.texture(
    texture: Texture,
    shape: Shape = RectangleShape,
): Modifier = this.drawBehind {
    val outline = shape.createOutline(size, layoutDirection, this)
    val path = when (outline) {
        is Outline.Rectangle -> Path().apply { addRect(outline.rect) }
        is Outline.Rounded -> Path().apply { addRoundRect(outline.roundRect) }
        is Outline.Generic -> outline.path
    }
    clipPath(path) {
        with(texture) {
            draw()
        }
    }
}

class ConcreteTexture(
    private val color: Color = Color(0xFF959595),
    private val grainColor: Color = Color(0xFF707070),
    private val seed: Int = 42
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(2000) {
            val x = random.nextFloat() * size.width
            val y = random.nextFloat() * size.height
            val radius = random.nextFloat() * 1.5f
            drawCircle(
                color = grainColor.copy(alpha = random.nextFloat() * 0.3f),
                radius = radius,
                center = Offset(x, y)
            )
        }
    }
}

class IronTexture(
    private val color: Color = Color(0xFF434343),
    private val scratchColor: Color = Color(0xFF606060),
    private val seed: Int = 123
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(150) {
            val y = random.nextFloat() * size.height
            val startX = random.nextFloat() * size.width
            val length = random.nextFloat() * size.width * 0.5f
            drawLine(
                color = scratchColor.copy(alpha = 0.15f),
                start = Offset(startX, y),
                end = Offset(startX + length, y + (random.nextFloat() - 0.5f) * 2f),
                strokeWidth = 1f
            )
        }
    }
}

class WoodTexture(
    private val color: Color = Color(0xFF8B4513),
    private val grainColor: Color = Color(0xFF5D2906),
    private val seed: Int = 789
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        val numLines = 40
        repeat(numLines) { i ->
            val path = Path()
            val startY = (size.height / numLines) * i + (random.nextFloat() * 10f)
            path.moveTo(0f, startY)
            
            var currentX = 0f
            val stepX = 30f
            while (currentX < size.width) {
                currentX += stepX
                val variation = (random.nextFloat() - 0.5f) * 15f
                path.quadraticTo(
                    currentX - stepX / 2, startY + variation * 2,
                    currentX, startY + variation
                )
            }
            
            drawPath(
                path = path,
                color = grainColor.copy(alpha = 0.25f),
                style = Stroke(width = 2.5f)
            )
        }
    }
}

class CarbonFiberTexture(
    private val color: Color = Color(0xFF1A1A1A),
    private val patternColor: Color = Color(0xFF2D2D2D),
    private val tileSize: Float = 10f
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val rows = (size.height / tileSize).toInt() + 1
        val cols = (size.width / tileSize).toInt() + 1
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if ((r + c) % 2 == 0) {
                    drawRect(
                        color = patternColor,
                        topLeft = Offset(c * tileSize, r * tileSize),
                        size = Size(tileSize, tileSize)
                    )
                }
            }
        }
    }
}

class PaperTexture(
    private val color: Color = Color(0xFFF9F4E8),
    private val fiberColor: Color = Color(0xFFE0D8C0),
    private val seed: Int = 456
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(1000) {
            val x = random.nextFloat() * size.width
            val y = random.nextFloat() * size.height
            val length = random.nextFloat() * 4f + 2f
            val angle = random.nextFloat() * 360f
            drawLine(
                color = fiberColor.copy(alpha = 0.3f),
                start = Offset(x, y),
                end = Offset(
                    x + Math.cos(Math.toRadians(angle.toDouble())).toFloat() * length,
                    y + Math.sin(Math.toRadians(angle.toDouble())).toFloat() * length
                ),
                strokeWidth = 0.5f
            )
        }
    }
}

class FabricTexture(
    private val color: Color = Color(0xFF3F51B5),
    private val weaveColor: Color = Color(0xFF303F9F),
    private val spacing: Float = 4f
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        // Horizontal threads
        var y = 0f
        while (y < size.height) {
            drawLine(
                color = weaveColor.copy(alpha = 0.3f),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1f
            )
            y += spacing
        }
        // Vertical threads
        var x = 0f
        while (x < size.width) {
            drawLine(
                color = weaveColor.copy(alpha = 0.3f),
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = 1f
            )
            x += spacing
        }
    }
}

class MarbleTexture(
    private val color: Color = Color(0xFFFFFFFF),
    private val veinColor: Color = Color(0xFFE0E0E0),
    private val seed: Int = 111
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(10) {
            val path = Path()
            path.moveTo(random.nextFloat() * size.width, 0f)
            var curY = 0f
            var curX = random.nextFloat() * size.width
            while (curY < size.height) {
                val nextY = curY + random.nextFloat() * 50f + 20f
                val nextX = curX + (random.nextFloat() - 0.5f) * 100f
                path.quadraticTo(curX, curY, nextX, nextY)
                curX = nextX
                curY = nextY
            }
            drawPath(
                path = path,
                color = veinColor.copy(alpha = random.nextFloat() * 0.5f),
                style = Stroke(width = random.nextFloat() * 5f + 1f)
            )
        }
    }
}

class LeatherTexture(
    private val color: Color = Color(0xFF3E2723),
    private val grainColor: Color = Color(0xFF21100D),
    private val seed: Int = 222
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(3000) {
            val x = random.nextFloat() * size.width
            val y = random.nextFloat() * size.height
            drawCircle(
                color = grainColor.copy(alpha = 0.1f),
                radius = random.nextFloat() * 2f + 1f,
                center = Offset(x, y)
            )
        }
    }
}

class SandTexture(
    private val color: Color = Color(0xFFEDC9AF),
    private val grainColor: Color = Color(0xFFC2B280),
    private val seed: Int = 333
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(5000) {
            drawCircle(
                color = grainColor.copy(alpha = 0.4f),
                radius = 0.5f,
                center = Offset(random.nextFloat() * size.width, random.nextFloat() * size.height)
            )
        }
    }
}

class DenimTexture(
    private val color: Color = Color(0xFF2B3E50),
    private val threadColor: Color = Color(0xFF4A647A),
    private val spacing: Float = 3f
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        var i = -size.height
        while (i < size.width) {
            drawLine(
                color = threadColor.copy(alpha = 0.4f),
                start = Offset(i, 0f),
                end = Offset(i + size.height, size.height),
                strokeWidth = 1.5f
            )
            i += spacing
        }
    }
}

class GoldTexture(
    private val color: Color = Color(0xFFFFD700),
    private val highlightColor: Color = Color(0xFFFFF8E1),
    private val shadowColor: Color = Color(0xFFB8860B),
    private val seed: Int = 777
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(100) {
            val x = random.nextFloat() * size.width
            val w = random.nextFloat() * size.width * 0.2f
            drawRect(
                color = highlightColor.copy(alpha = 0.1f),
                topLeft = Offset(x, 0f),
                size = Size(w, size.height)
            )
        }
        repeat(50) {
            val y = random.nextFloat() * size.height
            drawLine(
                color = shadowColor.copy(alpha = 0.2f),
                start = Offset(0f, y),
                end = Offset(size.width, y + (random.nextFloat() - 0.5f) * 20f),
                strokeWidth = 0.5f
            )
        }
    }
}

class GridTexture(
    private val color: Color = Color.White,
    private val lineColor: Color = Color(0xFFE0E0E0),
    private val gridSize: Float = 20f
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        var x = 0f
        while (x <= size.width) {
            drawLine(lineColor, Offset(x, 0f), Offset(x, size.height), strokeWidth = 1f)
            x += gridSize
        }
        var y = 0f
        while (y <= size.height) {
            drawLine(lineColor, Offset(0f, y), Offset(size.width, y), strokeWidth = 1f)
            y += gridSize
        }
    }
}

class BrushedSteelTexture(
    private val color: Color = Color(0xFF757575),
    private val highlightColor: Color = Color(0xFFBDBDBD),
    private val seed: Int = 888
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(400) {
            val y = random.nextFloat() * size.height
            val startX = random.nextFloat() * size.width
            val length = random.nextFloat() * size.width
            drawLine(
                color = highlightColor.copy(alpha = 0.1f),
                start = Offset(startX, y),
                end = Offset(startX + length, y),
                strokeWidth = 0.5f
            )
        }
    }
}

class GrassTexture(
    private val color: Color = Color(0xFF4CAF50),
    private val bladeColor: Color = Color(0xFF2E7D32),
    private val seed: Int = 999
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(1000) {
            val x = random.nextFloat() * size.width
            val y = random.nextFloat() * size.height
            val height = random.nextFloat() * 10f + 5f
            val tilt = (random.nextFloat() - 0.5f) * 5f
            drawLine(
                color = bladeColor.copy(alpha = 0.6f),
                start = Offset(x, y),
                end = Offset(x + tilt, y - height),
                strokeWidth = 1f
            )
        }
    }
}

class PlasticTexture(
    private val color: Color = Color(0xFFE0E0E0),
    private val seed: Int = 555
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(1000) {
            val x = random.nextFloat() * size.width
            val y = random.nextFloat() * size.height
            drawCircle(
                color = Color.White.copy(alpha = 0.05f),
                radius = random.nextFloat() * 2f,
                center = Offset(x, y)
            )
            drawCircle(
                color = Color.Black.copy(alpha = 0.02f),
                radius = random.nextFloat() * 1f,
                center = Offset(x + 1f, y + 1f)
            )
        }
    }
}

class SteelTexture(
    private val color: Color = Color(0xFF9E9E9E),
    private val highlight: Color = Color(0xFFEEEEEE),
    private val shadow: Color = Color(0xFF424242)
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val numReflections = 4
        val widthPerReflection = size.width / numReflections
        for (i in 0 until numReflections) {
            val x = i * widthPerReflection
            drawRect(
                color = highlight.copy(alpha = 0.3f),
                topLeft = Offset(x + widthPerReflection * 0.1f, 0f),
                size = Size(widthPerReflection * 0.2f, size.height)
            )
            drawRect(
                color = shadow.copy(alpha = 0.2f),
                topLeft = Offset(x + widthPerReflection * 0.7f, 0f),
                size = Size(widthPerReflection * 0.1f, size.height)
            )
        }
    }
}

class ChineseBowlTexture(
    private val color: Color = Color(0xFFF5F5F5),
    private val patternColor: Color = Color(0xFF003BA0),
    private val seed: Int = 1234
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val gridSize = 40f
        val rows = (size.height / gridSize).toInt() + 1
        val cols = (size.width / gridSize).toInt() + 1
        
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                val cx = c * gridSize + gridSize / 2
                val cy = r * gridSize + gridSize / 2
                
                drawCircle(
                    color = patternColor.copy(alpha = 0.15f),
                    radius = 8f,
                    center = Offset(cx, cy),
                    style = Stroke(width = 1.5f)
                )
                repeat(4) { i ->
                    val angle = i * 90f
                    val rad = Math.toRadians(angle.toDouble())
                    val ox = Math.cos(rad).toFloat() * 12f
                    val oy = Math.sin(rad).toFloat() * 12f
                    drawCircle(
                        color = patternColor.copy(alpha = 0.1f),
                        radius = 4f,
                        center = Offset(cx + ox, cy + oy)
                    )
                }
            }
        }
    }
}

class BrickTexture(
    private val color: Color = Color(0xFFB7410E),
    private val groutColor: Color = Color(0xFFD3D3D3),
    private val brickWidth: Float = 60f,
    private val brickHeight: Float = 30f
) : Texture {
    override fun DrawScope.draw() {
        drawRect(groutColor)
        val rows = (size.height / brickHeight).toInt() + 1
        val cols = (size.width / brickWidth).toInt() + 2
        for (r in 0 until rows) {
            val offset = if (r % 2 == 1) brickWidth / 2 else 0f
            for (c in 0 until cols) {
                drawRect(
                    color = color,
                    topLeft = Offset(c * brickWidth - offset + 2f, r * brickHeight + 2f),
                    size = Size(brickWidth - 4f, brickHeight - 4f)
                )
            }
        }
    }
}

class HoneycombTexture(
    private val color: Color = Color(0xFFFFA000),
    private val lineColor: Color = Color(0xFFFFC107),
    private val radius: Float = 20f
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val h = radius * Math.sqrt(3.0).toFloat()
        val w = radius * 2
        val rows = (size.height / h).toInt() + 2
        val cols = (size.width / (w * 0.75f)).toInt() + 2
        
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                val cx = c * w * 0.75f
                val cy = r * h + (if (c % 2 == 1) h / 2 else 0f)
                
                val path = Path()
                for (i in 0 until 6) {
                    val angle = i * 60f
                    val rad = Math.toRadians(angle.toDouble())
                    val px = cx + radius * Math.cos(rad).toFloat()
                    val py = cy + radius * Math.sin(rad).toFloat()
                    if (i == 0) path.moveTo(px, py) else path.lineTo(px, py)
                }
                path.close()
                drawPath(path, lineColor.copy(alpha = 0.3f), style = Stroke(width = 2f))
            }
        }
    }
}

class CircuitBoardTexture(
    private val color: Color = Color(0xFF1B5E20),
    private val traceColor: Color = Color(0xFF4CAF50),
    private val seed: Int = 1337
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(20) {
            val path = Path()
            var curX = random.nextFloat() * size.width
            var curY = random.nextFloat() * size.height
            path.moveTo(curX, curY)
            
            repeat(4) {
                val horizontal = random.nextBoolean()
                val dist = (random.nextFloat() * 100f + 50f) * (if (random.nextBoolean()) 1 else -1)
                if (horizontal) curX += dist else curY += dist
                path.lineTo(curX, curY)
            }
            
            drawPath(path, traceColor.copy(alpha = 0.4f), style = Stroke(width = 2f))
            drawCircle(traceColor.copy(alpha = 0.6f), radius = 4f, center = Offset(curX, curY))
        }
    }
}

class DiamondPlateTexture(
    private val color: Color = Color(0xFF757575),
    private val highlight: Color = Color(0xFFBDBDBD),
    private val spacing: Float = 40f
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val rows = (size.height / spacing).toInt() + 1
        val cols = (size.width / spacing).toInt() + 1
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                val cx = c * spacing + (if (r % 2 == 1) spacing / 2 else 0f)
                val cy = r * spacing
                
                val rotation = if (r % 2 == 1) 45f else -45f
                // Simplified diamond shape
                drawOval(
                    color = highlight.copy(alpha = 0.3f),
                    topLeft = Offset(cx - 10f, cy - 4f),
                    size = Size(20f, 8f)
                )
            }
        }
    }
}

class GraniteTexture(
    private val color: Color = Color(0xFF616161),
    private val seed: Int = 144
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        val fleckColors = listOf(Color(0xFF212121), Color(0xFF9E9E9E), Color(0xFF424242), Color(0xFFF5F5F5))
        repeat(3000) {
            val x = random.nextFloat() * size.width
            val y = random.nextFloat() * size.height
            drawCircle(
                color = fleckColors[random.nextInt(fleckColors.size)].copy(alpha = 0.5f),
                radius = random.nextFloat() * 2f + 0.5f,
                center = Offset(x, y)
            )
        }
    }
}

class CorkTexture(
    private val color: Color = Color(0xFFC19A6B),
    private val poreColor: Color = Color(0xFF8B5A2B),
    private val seed: Int = 155
) : Texture {
    override fun DrawScope.draw() {
        drawRect(color)
        val random = Random(seed)
        repeat(1500) {
            val x = random.nextFloat() * size.width
            val y = random.nextFloat() * size.height
            drawOval(
                color = poreColor.copy(alpha = 0.3f),
                topLeft = Offset(x, y),
                size = Size(random.nextFloat() * 8f + 2f, random.nextFloat() * 4f + 1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TexturePreview() {
    Column(
        modifier = Modifier.size(900.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.size(80.dp).texture(ConcreteTexture()))
            Box(modifier = Modifier.size(80.dp).texture(IronTexture()))
            Box(modifier = Modifier.size(80.dp).texture(WoodTexture()))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.size(80.dp).texture(CarbonFiberTexture()))
            Box(modifier = Modifier.size(80.dp).texture(PaperTexture()))
            Box(modifier = Modifier.size(80.dp).texture(FabricTexture()))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.size(80.dp).texture(MarbleTexture()))
            Box(modifier = Modifier.size(80.dp).texture(LeatherTexture()))
            Box(modifier = Modifier.size(80.dp).texture(SandTexture()))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.size(80.dp).texture(DenimTexture()))
            Box(modifier = Modifier.size(80.dp).texture(GoldTexture()))
            Box(modifier = Modifier.size(80.dp).texture(GridTexture()))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.size(80.dp).texture(BrushedSteelTexture()))
            Box(modifier = Modifier.size(80.dp).texture(GrassTexture()))
            Box(modifier = Modifier.size(80.dp).texture(PlasticTexture()))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.size(80.dp).texture(SteelTexture()))
            Box(modifier = Modifier.size(80.dp).texture(ChineseBowlTexture()))
            Box(modifier = Modifier.size(80.dp).texture(BrickTexture()))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.size(80.dp).texture(HoneycombTexture()))
            Box(modifier = Modifier.size(80.dp).texture(CircuitBoardTexture()))
            Box(modifier = Modifier.size(80.dp).texture(CircuitBoardTexture()))
            Box(modifier = Modifier.size(80.dp).texture(DiamondPlateTexture()))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.size(80.dp).texture(GraniteTexture()))
            Box(modifier = Modifier.size(80.dp).texture(CorkTexture()))
        }
    }
}
