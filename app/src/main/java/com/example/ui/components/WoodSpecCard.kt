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
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
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
import com.example.model.WoodSpec
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.WalnutBorder
import com.example.ui.theme.WalnutCard
import com.example.ui.theme.WalnutCardElevated
import com.example.ui.theme.WalnutDark

@Composable
fun WoodSpecBreakdownSection(
    woodSpecs: List<WoodSpec>,
    selectedWood: String,
    onWoodSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var activeSpecIndex by remember { mutableStateOf(0) }
    val currentSpec = woodSpecs.getOrNull(activeSpecIndex) ?: woodSpecs.firstOrNull()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(WalnutCard)
            .border(1.dp, WalnutBorder, RoundedCornerShape(18.dp))
            .padding(16.dp)
            .testTag("wood_spec_breakdown_section")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Wood Specification Breakdown",
                    color = GoldBright,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    text = "Certified botanical grade & seasoning analysis",
                    color = Color(0xFFAFA094),
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF382316))
                    .border(0.6.dp, GoldPrimary, RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "100% Solid Hardwood",
                    color = GoldBright,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Wood Type Selector Tabs (Teak vs Sheesham vs Walnut)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            woodSpecs.forEachIndexed { index, spec ->
                val isSelected = activeSpecIndex == index
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (isSelected) {
                                Brush.horizontalGradient(listOf(GoldDark, GoldBright))
                            } else {
                                Brush.verticalGradient(listOf(Color(0xFF1E130D), Color(0xFF140A06)))
                            }
                        )
                        .border(
                            1.dp,
                            if (isSelected) GoldBright else WalnutBorder,
                            RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            activeSpecIndex = index
                            onWoodSelected(spec.woodType)
                        }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (spec.woodType.contains("Teak", ignoreCase = true)) "Teak (Sagwan)"
                               else if (spec.woodType.contains("Sheesham", ignoreCase = true)) "Sheesham"
                               else "Walnut / Burl",
                        color = if (isSelected) WalnutDark else Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }

        if (currentSpec != null) {
            Spacer(modifier = Modifier.height(14.dp))

            // Botanical name and intro
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF1C110B))
                    .border(0.6.dp, WalnutBorder, RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = currentSpec.woodType,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        Text(
                            text = currentSpec.botName,
                            color = GoldPrimary,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Serif
                        )
                    }
                    Text(
                        text = currentSpec.grainDescription,
                        color = Color(0xFFD3C5B8),
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 4-Spec Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SpecMetricBox(
                    icon = Icons.Default.WaterDrop,
                    title = "Moisture Content",
                    value = currentSpec.moistureContent,
                    modifier = Modifier.weight(1f)
                )
                SpecMetricBox(
                    icon = Icons.Default.Shield,
                    title = "Durability Rating",
                    value = currentSpec.durabilityClass,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SpecMetricBox(
                    icon = Icons.Default.BugReport,
                    title = "Termite Immunity",
                    value = currentSpec.termiteResistance,
                    modifier = Modifier.weight(1f)
                )
                SpecMetricBox(
                    icon = Icons.Default.HourglassTop,
                    title = "Seasoning Protocol",
                    value = currentSpec.seasoningMethod,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun SpecMetricBox(
    icon: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF22160E))
            .border(0.6.dp, WalnutBorder, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = GoldPrimary,
                    modifier = Modifier.size(13.dp)
                )
                Text(
                    text = title,
                    color = GoldBright,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp
                )
            }
            Text(
                text = value,
                color = Color.White,
                fontSize = 11.sp,
                lineHeight = 14.sp
            )
        }
    }
}
