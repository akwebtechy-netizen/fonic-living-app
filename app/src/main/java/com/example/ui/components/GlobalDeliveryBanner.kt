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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
fun GlobalDeliveryBanner(
    modifier: Modifier = Modifier,
    onViewExportGuide: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF2E1C12),
                        WalnutCard,
                        WalnutDark
                    )
                )
            )
            .border(
                1.dp,
                Brush.horizontalGradient(
                    listOf(GoldDark, GoldPrimary, GoldDark)
                ),
                RoundedCornerShape(18.dp)
            )
            .clickable { expanded = !expanded }
            .padding(16.dp)
            .testTag("global_delivery_banner")
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(GoldPrimary, GoldDark)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Public,
                            contentDescription = "Global Delivery",
                            tint = WalnutDark,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "Global Insured Delivery",
                            color = GoldBright,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "USA • UK • UAE • Canada • Australia • Pan-India",
                            color = Color(0xFFD6C8BC),
                            fontWeight = FontWeight.Medium,
                            fontSize = 11.sp
                        )
                    }
                }

                Text(
                    text = if (expanded) "Hide Details ▲" else "View Standards ▼",
                    color = GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Badges Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TrustBadgeItem(
                    icon = Icons.Default.Inventory2,
                    title = "Export Wooden Crates",
                    sub = "Fumigated 7-layer shockproof packing",
                    modifier = Modifier.weight(1f)
                )
                TrustBadgeItem(
                    icon = Icons.Default.Verified,
                    title = "Master Certified",
                    sub = "100% Solid Seasoned Timber",
                    modifier = Modifier.weight(1f)
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(14.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1E120A))
                        .border(0.8.dp, WalnutBorder, RoundedCornerShape(12.dp))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "FONIC LIVING EXPORT PROTOCOLS:",
                        color = GoldBright,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )

                    ExportStepRow(step = "1", title = "Moisture Chamber Stabilization", desc = "Timber is calibrated to destination climate (8-10% RH) preventing shrinkage.")
                    ExportStepRow(step = "2", title = "Anti-Scratch EPE Armor Wrap", desc = "Multi-layer high density foam and corner edge guards protect carved motifs.")
                    ExportStepRow(step = "3", title = "ISPM-15 Fumigated Pine Crates", desc = "Custom bolted solid pine international shipping crates suitable for sea & air freight.")
                    ExportStepRow(step = "4", title = "Comprehensive Transit Insurance", desc = "100% replacement value covered from Saharanpur workshop to your doorstep.")
                }
            }
        }
    }
}

@Composable
private fun TrustBadgeItem(
    icon: ImageVector,
    title: String,
    sub: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF23150D))
            .border(0.6.dp, WalnutBorder, RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = GoldPrimary,
                modifier = Modifier.size(18.dp)
            )
            Column {
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    maxLines = 1
                )
                Text(
                    text = sub,
                    color = Color(0xFFA6968B),
                    fontSize = 9.sp,
                    lineHeight = 12.sp,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
private fun ExportStepRow(step: String, title: String, desc: String) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .clip(CircleShape)
                .background(GoldPrimary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = step,
                color = WalnutDark,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            )
        }
        Column {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp
            )
            Text(
                text = desc,
                color = Color(0xFFC0B1A5),
                fontSize = 10.sp,
                lineHeight = 14.sp
            )
        }
    }
}
