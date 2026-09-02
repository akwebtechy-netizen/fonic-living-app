package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.WalnutBorder
import com.example.ui.theme.WalnutCard
import com.example.ui.theme.WalnutCardElevated
import com.example.ui.theme.WalnutDark

@Composable
fun HeroShowcaseBanner(
    onExploreClick: () -> Unit,
    onConsultClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        WalnutCardElevated,
                        WalnutCard,
                        WalnutDark
                    )
                )
            )
            .border(
                1.5.dp,
                Brush.linearGradient(
                    listOf(GoldDark, GoldBright, GoldPrimary, GoldDark)
                ),
                RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
    ) {
        Column {
            // Top Badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF3B2816))
                    .border(1.dp, GoldPrimary.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.WorkspacePremium,
                    contentDescription = "Royal Standard",
                    tint = GoldBright,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "HEIRLOOM TIMBER ATELIER",
                    color = GoldBright,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Architectural Wooden Splendor",
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Handcrafted from 100% seasoned Grade-A Nilambur Teak & North Indian Sheesham. Custom sculpted for luxury villas, palaces & estates.",
                color = Color(0xFFD3C4B8),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Three core pillars
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PillarPill(title = "100% Solid Wood", sub = "No Veneers")
                PillarPill(title = "Lifetime Proof", sub = "Termite Immune")
                PillarPill(title = "Custom Sizes", sub = "Bespoke Scale")
            }

            Spacer(modifier = Modifier.height(18.dp))

            // CTA Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ElevatedButton(
                    onClick = onExploreClick,
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = GoldPrimary,
                        contentColor = WalnutDark
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("explore_collection_button")
                ) {
                    Text(
                        text = "Explore Portfolio",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(WalnutCard)
                        .border(1.dp, GoldPrimary, RoundedCornerShape(12.dp))
                        .clickable { onConsultClick() }
                        .testTag("consult_artisan_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Handyman,
                            contentDescription = null,
                            tint = GoldBright,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Talk to Carver",
                            color = GoldBright,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PillarPill(title: String, sub: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF22150E))
            .border(0.8.dp, WalnutBorder, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = GoldBright,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp
        )
        Text(
            text = sub,
            color = Color(0xFFAFA094),
            fontSize = 9.sp
        )
    }
}
