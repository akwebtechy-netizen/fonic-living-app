package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.WalnutCard
import com.example.ui.theme.WalnutDark
import com.example.ui.theme.WalnutSurface

@Composable
fun ProductVisualArtwork(
    product: Product,
    modifier: Modifier = Modifier,
    galleryIndex: Int = 0
) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val goldShimmer by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmerFloat"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        WalnutCard,
                        WalnutDark,
                        Color(0xFF0F0704)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Background subtle architectural luxury panel lines
            drawRect(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF382215), Color(0xFF140B07)),
                    center = Offset(w / 2f, h / 2f),
                    radius = w * 0.8f
                )
            )

            // Outer fine gold border
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        GoldDark.copy(alpha = 0.5f),
                        GoldPrimary.copy(alpha = 0.8f),
                        GoldDark.copy(alpha = 0.4f)
                    )
                ),
                topLeft = Offset(8f, 8f),
                size = Size(w - 16f, h - 16f),
                cornerRadius = CornerRadius(14f, 14f),
                style = Stroke(width = 1.2f)
            )

            when (product.category) {
                ProductCategory.LUXURY_BEDS -> {
                    drawLuxuryBedVisual(w, h, galleryIndex, goldShimmer)
                }
                ProductCategory.ROYAL_DINING -> {
                    drawRoyalDiningVisual(w, h, galleryIndex, goldShimmer)
                }
                ProductCategory.CARVED_SOFAS -> {
                    drawCarvedSofaVisual(w, h, galleryIndex, goldShimmer)
                }
                ProductCategory.WOODEN_JHULA -> {
                    drawWoodenJhulaVisual(w, h, galleryIndex, goldShimmer)
                }
                ProductCategory.EPOXY_TABLES -> {
                    drawEpoxyTableVisual(w, h, galleryIndex, goldShimmer)
                }
                else -> {
                    drawLuxuryFurnitureEmblem(w, h, goldShimmer)
                }
            }
        }
    }
}

private fun DrawScope.drawLuxuryBedVisual(w: Float, h: Float, galleryIndex: Int, shimmer: Float) {
    val cx = w / 2f
    val base = h * 0.76f

    // Floor shadow
    drawOval(
        color = Color(0x66000000),
        topLeft = Offset(w * 0.1f, base + 10f),
        size = Size(w * 0.8f, 24f)
    )

    // Bed Headboard Arch
    val headboardPath = Path().apply {
        moveTo(w * 0.2f, base - h * 0.45f)
        cubicTo(
            w * 0.25f, base - h * 0.65f,
            w * 0.4f, base - h * 0.72f,
            cx, base - h * 0.72f
        )
        cubicTo(
            w * 0.6f, base - h * 0.72f,
            w * 0.75f, base - h * 0.65f,
            w * 0.8f, base - h * 0.45f
        )
        lineTo(w * 0.8f, base)
        lineTo(w * 0.2f, base)
        close()
    }

    // Wood fill
    drawPath(
        path = headboardPath,
        brush = Brush.verticalGradient(
            colors = listOf(Color(0xFF5A3520), Color(0xFF331E12), Color(0xFF1E1009))
        )
    )

    // Headboard Gold Carved Cresting
    drawPath(
        path = headboardPath,
        brush = Brush.linearGradient(
            colors = listOf(GoldDark, GoldBright, GoldPrimary, GoldDark),
            start = Offset(w * 0.2f, 0f),
            end = Offset(w * 0.8f, 0f)
        ),
        style = Stroke(width = 3.5f)
    )

    // Crown Floral Emblem at Top
    drawCircle(
        brush = Brush.radialGradient(listOf(GoldBright, GoldDark)),
        radius = 12f,
        center = Offset(cx, base - h * 0.72f + 4f)
    )

    // Tufted Diamond Lattice Pattern on Headboard
    val startY = base - h * 0.6f
    for (i in 0..3) {
        val yOffset = startY + i * 28f
        drawLine(
            color = GoldPrimary.copy(alpha = 0.35f),
            start = Offset(w * 0.3f + i * 15f, yOffset - 25f),
            end = Offset(w * 0.7f - i * 15f, yOffset + 25f),
            strokeWidth = 1.2f
        )
        drawLine(
            color = GoldPrimary.copy(alpha = 0.35f),
            start = Offset(w * 0.7f - i * 15f, yOffset - 25f),
            end = Offset(w * 0.3f + i * 15f, yOffset + 25f),
            strokeWidth = 1.2f
        )
    }

    // Royal Mattress & Bedspread with Gold Silk Runners
    drawRoundRect(
        brush = Brush.verticalGradient(listOf(Color(0xFFECE5D8), Color(0xFFC7BBAA))),
        topLeft = Offset(w * 0.18f, base - h * 0.22f),
        size = Size(w * 0.64f, h * 0.24f),
        cornerRadius = CornerRadius(10f, 10f)
    )

    // Gold silk luxury runner
    drawRoundRect(
        brush = Brush.horizontalGradient(listOf(GoldDark, GoldPrimary, GoldBright, GoldDark)),
        topLeft = Offset(w * 0.22f, base - h * 0.12f),
        size = Size(w * 0.56f, h * 0.08f),
        cornerRadius = CornerRadius(6f, 6f)
    )

    // Carved Footboard & Legs
    drawRoundRect(
        brush = Brush.verticalGradient(listOf(Color(0xFF4C2B18), Color(0xFF27150C))),
        topLeft = Offset(w * 0.16f, base - h * 0.08f),
        size = Size(w * 0.68f, h * 0.12f),
        cornerRadius = CornerRadius(8f, 8f)
    )
    drawRoundRect(
        brush = Brush.horizontalGradient(listOf(GoldDark, GoldBright, GoldDark)),
        topLeft = Offset(w * 0.16f, base - h * 0.08f),
        size = Size(w * 0.68f, h * 0.12f),
        cornerRadius = CornerRadius(8f, 8f),
        style = Stroke(width = 2f)
    )

    // Two Master Pillows
    drawRoundRect(
        color = Color(0xFFF9F6F0),
        topLeft = Offset(w * 0.26f, base - h * 0.28f),
        size = Size(w * 0.22f, h * 0.10f),
        cornerRadius = CornerRadius(6f, 6f)
    )
    drawRoundRect(
        color = Color(0xFFF9F6F0),
        topLeft = Offset(w * 0.52f, base - h * 0.28f),
        size = Size(w * 0.22f, h * 0.10f),
        cornerRadius = CornerRadius(6f, 6f)
    )
}

private fun DrawScope.drawRoyalDiningVisual(w: Float, h: Float, galleryIndex: Int, shimmer: Float) {
    val cx = w / 2f
    val base = h * 0.72f

    // Floor shadow
    drawOval(
        color = Color(0x77000000),
        topLeft = Offset(w * 0.1f, base + 20f),
        size = Size(w * 0.8f, 26f)
    )

    // Background Chairs (4 chairs behind table)
    val chairWidth = w * 0.10f
    for (i in 0..3) {
        val chairX = w * 0.25f + i * (w * 0.14f)
        // High carved chair back
        val chairPath = Path().apply {
            moveTo(chairX, base - h * 0.46f)
            cubicTo(chairX + 5f, base - h * 0.52f, chairX + chairWidth - 5f, base - h * 0.52f, chairX + chairWidth, base - h * 0.46f)
            lineTo(chairX + chairWidth, base - h * 0.12f)
            lineTo(chairX, base - h * 0.12f)
            close()
        }
        drawPath(
            chairPath,
            brush = Brush.verticalGradient(listOf(Color(0xFF4A2B18), Color(0xFF28160B)))
        )
        drawPath(
            chairPath,
            brush = Brush.verticalGradient(listOf(GoldDark, GoldPrimary)),
            style = Stroke(width = 1.5f)
        )
        // Royal velvet inner back cushion
        drawRoundRect(
            color = Color(0xFF8B1E28),
            topLeft = Offset(chairX + 4f, base - h * 0.44f),
            size = Size(chairWidth - 8f, h * 0.25f),
            cornerRadius = CornerRadius(4f, 4f)
        )
    }

    // Monumental Tabletop (Teak slab with bookmatched grain)
    val tablePath = Path().apply {
        moveTo(w * 0.12f, base - h * 0.15f)
        lineTo(w * 0.88f, base - h * 0.15f)
        lineTo(w * 0.84f, base - h * 0.05f)
        lineTo(w * 0.16f, base - h * 0.05f)
        close()
    }
    drawPath(
        tablePath,
        brush = Brush.linearGradient(
            colors = listOf(Color(0xFF633A20), Color(0xFF422412), Color(0xFF6B3F23), Color(0xFF2E170A))
        )
    )
    drawPath(
        tablePath,
        brush = Brush.horizontalGradient(listOf(GoldDark, GoldBright, GoldPrimary, GoldDark)),
        style = Stroke(width = 2.5f)
    )

    // Dual Hand-Carved Lion Pedestal Bases
    drawRoundRect(
        brush = Brush.verticalGradient(listOf(Color(0xFF482917), Color(0xFF1E0E06))),
        topLeft = Offset(w * 0.26f, base - h * 0.05f),
        size = Size(w * 0.12f, h * 0.20f),
        cornerRadius = CornerRadius(6f, 6f)
    )
    drawRoundRect(
        brush = Brush.verticalGradient(listOf(Color(0xFF482917), Color(0xFF1E0E06))),
        topLeft = Offset(w * 0.62f, base - h * 0.05f),
        size = Size(w * 0.12f, h * 0.20f),
        cornerRadius = CornerRadius(6f, 6f)
    )
    // Gold filigree ring on pedestals
    drawRect(
        brush = Brush.horizontalGradient(listOf(GoldDark, GoldBright, GoldDark)),
        topLeft = Offset(w * 0.24f, base + h * 0.05f),
        size = Size(w * 0.16f, 8f)
    )
    drawRect(
        brush = Brush.horizontalGradient(listOf(GoldDark, GoldBright, GoldDark)),
        topLeft = Offset(w * 0.60f, base + h * 0.05f),
        size = Size(w * 0.16f, 8f)
    )

    // Tabletop Centerpiece (Gold fruit bowl / candelabra silhouette)
    drawOval(
        brush = Brush.radialGradient(listOf(GoldBright, GoldDark)),
        topLeft = Offset(cx - 25f, base - h * 0.22f),
        size = Size(50f, 14f)
    )
}

private fun DrawScope.drawCarvedSofaVisual(w: Float, h: Float, galleryIndex: Int, shimmer: Float) {
    val cx = w / 2f
    val base = h * 0.72f

    // Floor shadow
    drawOval(
        color = Color(0x77000000),
        topLeft = Offset(w * 0.1f, base + 15f),
        size = Size(w * 0.8f, 25f)
    )

    // Carved Maharaja Crown Backrest
    val backPath = Path().apply {
        moveTo(w * 0.14f, base - h * 0.25f)
        cubicTo(w * 0.22f, base - h * 0.58f, w * 0.40f, base - h * 0.65f, cx, base - h * 0.65f)
        cubicTo(w * 0.60f, base - h * 0.65f, w * 0.78f, base - h * 0.58f, w * 0.86f, base - h * 0.25f)
        lineTo(w * 0.86f, base)
        lineTo(w * 0.14f, base)
        close()
    }
    drawPath(
        backPath,
        brush = Brush.verticalGradient(listOf(Color(0xFF56311C), Color(0xFF2C160B)))
    )
    drawPath(
        backPath,
        brush = Brush.linearGradient(
            colors = listOf(GoldDark, GoldBright, GoldPrimary, GoldDark),
            start = Offset(w * 0.14f, 0f),
            end = Offset(w * 0.86f, 0f)
        ),
        style = Stroke(width = 3.5f)
    )

    // Plush Tufted Cream/Champagne Velvet Back Cushion
    val cushionPath = Path().apply {
        moveTo(w * 0.20f, base - h * 0.22f)
        cubicTo(w * 0.28f, base - h * 0.48f, w * 0.40f, base - h * 0.54f, cx, base - h * 0.54f)
        cubicTo(w * 0.60f, base - h * 0.54f, w * 0.72f, base - h * 0.48f, w * 0.80f, base - h * 0.22f)
        lineTo(w * 0.80f, base - h * 0.10f)
        lineTo(w * 0.20f, base - h * 0.10f)
        close()
    }
    drawPath(
        cushionPath,
        brush = Brush.verticalGradient(listOf(Color(0xFFEFE9DC), Color(0xFFC7BCAA)))
    )

    // Tufted gold buttons
    for (row in 0..2) {
        val y = base - h * 0.45f + row * 24f
        for (col in 0..4) {
            val x = w * 0.32f + col * (w * 0.09f) + (if (row % 2 == 1) 12f else 0f)
            if (x in (w * 0.25f)..(w * 0.75f)) {
                drawCircle(
                    color = GoldDark,
                    radius = 3.5f,
                    center = Offset(x, y)
                )
            }
        }
    }

    // Deep Spring Seat Base
    drawRoundRect(
        brush = Brush.verticalGradient(listOf(Color(0xFFE4DCCE), Color(0xFFB5A695))),
        topLeft = Offset(w * 0.12f, base - h * 0.14f),
        size = Size(w * 0.76f, h * 0.18f),
        cornerRadius = CornerRadius(12f, 12f)
    )

    // Carved Scroll Armrests
    drawRoundRect(
        brush = Brush.horizontalGradient(listOf(GoldDark, GoldBright, GoldDark)),
        topLeft = Offset(w * 0.10f, base - h * 0.30f),
        size = Size(w * 0.10f, h * 0.26f),
        cornerRadius = CornerRadius(10f, 10f)
    )
    drawRoundRect(
        brush = Brush.horizontalGradient(listOf(GoldDark, GoldBright, GoldDark)),
        topLeft = Offset(w * 0.80f, base - h * 0.30f),
        size = Size(w * 0.10f, h * 0.26f),
        cornerRadius = CornerRadius(10f, 10f)
    )

    // Bottom Carved Apron & Cabriole Legs
    drawRect(
        brush = Brush.horizontalGradient(listOf(GoldDark, GoldBright, GoldDark)),
        topLeft = Offset(w * 0.12f, base + h * 0.04f),
        size = Size(w * 0.76f, 12f)
    )
}

private fun DrawScope.drawWoodenJhulaVisual(w: Float, h: Float, galleryIndex: Int, shimmer: Float) {
    val cx = w / 2f
    val base = h * 0.74f

    // Floor shadow
    drawOval(
        color = Color(0x77000000),
        topLeft = Offset(w * 0.15f, base + 20f),
        size = Size(w * 0.7f, 22f)
    )

    // Arch Stand Frame (Carved Pillars)
    val pillarW = w * 0.08f
    // Left pillar
    drawRoundRect(
        brush = Brush.verticalGradient(listOf(Color(0xFF5A351E), Color(0xFF28140A))),
        topLeft = Offset(w * 0.08f, h * 0.15f),
        size = Size(pillarW, h * 0.72f),
        cornerRadius = CornerRadius(6f, 6f)
    )
    // Right pillar
    drawRoundRect(
        brush = Brush.verticalGradient(listOf(Color(0xFF5A351E), Color(0xFF28140A))),
        topLeft = Offset(w * 0.84f, h * 0.15f),
        size = Size(pillarW, h * 0.72f),
        cornerRadius = CornerRadius(6f, 6f)
    )
    // Top Carved Arch Beam
    val topBeam = Path().apply {
        moveTo(w * 0.05f, h * 0.15f)
        cubicTo(w * 0.25f, h * 0.08f, w * 0.75f, h * 0.08f, w * 0.95f, h * 0.15f)
        lineTo(w * 0.95f, h * 0.24f)
        cubicTo(w * 0.75f, h * 0.17f, w * 0.25f, h * 0.17f, w * 0.05f, h * 0.24f)
        close()
    }
    drawPath(
        topBeam,
        brush = Brush.verticalGradient(listOf(Color(0xFF6B3E22), Color(0xFF331A0D)))
    )
    drawPath(
        topBeam,
        brush = Brush.linearGradient(listOf(GoldDark, GoldBright, GoldPrimary, GoldDark)),
        style = Stroke(width = 2.5f)
    )

    // Solid Brass Chandelier Hanging Chains with Elephant/Peacock links
    val chainX1 = w * 0.24f
    val chainX2 = w * 0.76f
    val chainStartY = h * 0.22f
    val chainEndY = base - h * 0.26f

    val linksCount = 10
    for (i in 0 until linksCount) {
        val y = chainStartY + i * ((chainEndY - chainStartY) / linksCount)
        // Brass chain loops
        drawOval(
            brush = Brush.linearGradient(listOf(GoldBright, GoldDark)),
            topLeft = Offset(chainX1 - 4f, y),
            size = Size(9f, 14f),
            style = Stroke(width = 2.2f)
        )
        drawOval(
            brush = Brush.linearGradient(listOf(GoldBright, GoldDark)),
            topLeft = Offset(chainX2 - 4f, y),
            size = Size(9f, 14f),
            style = Stroke(width = 2.2f)
        )
    }

    // Carved Jhula Plank Seat
    val seatPath = Path().apply {
        moveTo(w * 0.18f, base - h * 0.25f)
        cubicTo(w * 0.35f, base - h * 0.40f, w * 0.65f, base - h * 0.40f, w * 0.82f, base - h * 0.25f)
        lineTo(w * 0.82f, base - h * 0.08f)
        lineTo(w * 0.18f, base - h * 0.08f)
        close()
    }
    drawPath(
        seatPath,
        brush = Brush.verticalGradient(listOf(Color(0xFF542E18), Color(0xFF271308)))
    )
    drawPath(
        seatPath,
        brush = Brush.horizontalGradient(listOf(GoldDark, GoldBright, GoldDark)),
        style = Stroke(width = 2.5f)
    )

    // Royal Indian Silk Velvet Cushion
    drawRoundRect(
        color = Color(0xFF8B2500),
        topLeft = Offset(w * 0.22f, base - h * 0.18f),
        size = Size(w * 0.56f, h * 0.10f),
        cornerRadius = CornerRadius(6f, 6f)
    )

    // Peacock Emblem in Center of Arch
    drawCircle(
        brush = Brush.radialGradient(listOf(GoldBright, GoldDark)),
        radius = 14f,
        center = Offset(cx, h * 0.16f)
    )
}

private fun DrawScope.drawEpoxyTableVisual(w: Float, h: Float, galleryIndex: Int, shimmer: Float) {
    val cx = w / 2f
    val base = h * 0.68f

    // Floor reflection
    drawOval(
        color = Color(0x66000000),
        topLeft = Offset(w * 0.1f, base + 24f),
        size = Size(w * 0.8f, 26f)
    )

    // Live-Edge Tabletop Surface (Angled perspective view)
    val leftWoodSlab = Path().apply {
        moveTo(w * 0.14f, base - h * 0.35f)
        lineTo(w * 0.42f, base - h * 0.35f)
        cubicTo(w * 0.36f, base - h * 0.25f, w * 0.44f, base - h * 0.15f, w * 0.39f, base)
        lineTo(w * 0.16f, base)
        close()
    }

    val rightWoodSlab = Path().apply {
        moveTo(w * 0.58f, base - h * 0.35f)
        lineTo(w * 0.86f, base - h * 0.35f)
        lineTo(w * 0.84f, base)
        lineTo(w * 0.61f, base)
        cubicTo(w * 0.66f, base - h * 0.15f, w * 0.56f, base - h * 0.25f, w * 0.58f, base - h * 0.35f)
        close()
    }

    // Draw Dark Walnut Burl Wood Slabs
    drawPath(
        leftWoodSlab,
        brush = Brush.linearGradient(
            colors = listOf(Color(0xFF3F2314), Color(0xFF261309), Color(0xFF4A2A19)),
            start = Offset(w * 0.14f, base - h * 0.35f),
            end = Offset(w * 0.42f, base)
        )
    )
    drawPath(
        rightWoodSlab,
        brush = Brush.linearGradient(
            colors = listOf(Color(0xFF4A2A19), Color(0xFF261309), Color(0xFF3F2314)),
            start = Offset(w * 0.58f, base - h * 0.35f),
            end = Offset(w * 0.86f, base)
        )
    )

    // Deep Ocean Blue / Emerald Epoxy Liquid River Channel in Center
    val riverPath = Path().apply {
        moveTo(w * 0.42f, base - h * 0.35f)
        lineTo(w * 0.58f, base - h * 0.35f)
        cubicTo(w * 0.56f, base - h * 0.25f, w * 0.66f, base - h * 0.15f, w * 0.61f, base)
        lineTo(w * 0.39f, base)
        cubicTo(w * 0.44f, base - h * 0.15f, w * 0.36f, base - h * 0.25f, w * 0.42f, base - h * 0.35f)
        close()
    }

    drawPath(
        riverPath,
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF0D47A1),
                Color(0xFF00B0FF),
                Color(0xFF00897B),
                Color(0xFF004D40)
            ),
            start = Offset(cx, base - h * 0.35f),
            end = Offset(cx, base)
        )
    )

    // Resin Diamond Gloss Highlighting Swirls
    drawLine(
        brush = Brush.horizontalGradient(listOf(Color.Transparent, Color(0xCCFFFFFF), Color.Transparent)),
        start = Offset(cx - 20f, base - h * 0.25f),
        end = Offset(cx + 20f, base - h * 0.23f),
        strokeWidth = 3f
    )

    // Outer Gold Rim Edge
    drawRoundRect(
        brush = Brush.linearGradient(listOf(GoldDark, GoldBright, GoldDark)),
        topLeft = Offset(w * 0.14f, base - h * 0.35f),
        size = Size(w * 0.72f, h * 0.35f),
        cornerRadius = CornerRadius(8f, 8f),
        style = Stroke(width = 2.2f)
    )

    // Modern Geometric Brass Spider Base
    drawLine(
        brush = Brush.verticalGradient(listOf(GoldBright, GoldDark)),
        start = Offset(w * 0.30f, base),
        end = Offset(w * 0.24f, base + h * 0.20f),
        strokeWidth = 5f
    )
    drawLine(
        brush = Brush.verticalGradient(listOf(GoldBright, GoldDark)),
        start = Offset(w * 0.70f, base),
        end = Offset(w * 0.76f, base + h * 0.20f),
        strokeWidth = 5f
    )
    drawLine(
        brush = Brush.verticalGradient(listOf(GoldBright, GoldDark)),
        start = Offset(w * 0.42f, base),
        end = Offset(w * 0.58f, base + h * 0.20f),
        strokeWidth = 4f
    )
}

private fun DrawScope.drawLuxuryFurnitureEmblem(w: Float, h: Float, shimmer: Float) {
    val cx = w / 2f
    val cy = h / 2f
    drawCircle(
        brush = Brush.radialGradient(listOf(GoldBright, GoldDark)),
        radius = 45f,
        center = Offset(cx, cy),
        style = Stroke(width = 2f)
    )
}
