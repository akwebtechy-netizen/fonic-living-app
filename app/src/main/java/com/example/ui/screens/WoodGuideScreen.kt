package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.LuxuryTopAppBar
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.WalnutBorder
import com.example.ui.theme.WalnutCard
import com.example.ui.theme.WalnutCardElevated
import com.example.ui.theme.WalnutDark
import com.example.ui.theme.WhatsAppGreen
import com.example.viewmodel.FurnitureViewModel

@Composable
fun WoodGuideScreen(
    viewModel: FurnitureViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            LuxuryTopAppBar(
                title = "Artisan Timber Master Guide",
                onBackClick = onBackClick,
                onConciergeClick = { viewModel.launchDirectConciergeWhatsApp(context) },
                onBookmarksClick = {},
                bookmarkCount = 0,
                onWoodGuideClick = {}
            )
        },
        containerColor = WalnutDark,
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .testTag("wood_guide_screen"),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(
                            Brush.verticalGradient(
                                listOf(Color(0xFF382216), WalnutCard)
                            )
                        )
                        .border(1.dp, GoldPrimary, RoundedCornerShape(18.dp))
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = GoldBright,
                                modifier = Modifier.size(22.dp)
                            )
                            Text(
                                text = "The Fonic Living Timber Manifesto",
                                color = GoldBright,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp
                            )
                        }
                        Text(
                            text = "We never use MDF, particle boards, or engineered wood veneer. Every piece is carved from 100% solid, seasoned hardwood logs carefully handpicked for exceptional grain continuity and lifetime durability.",
                            color = Color(0xFFD6C8BC),
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }

            // Teak Breakdown
            item {
                WoodComparisonCard(
                    title = "Nilambur & Burma Teak (Sagwan)",
                    botName = "Tectona grandis",
                    accentColor = Color(0xFFC9822B),
                    summary = "Known as the 'King of Timbers', Teak contains rich natural silica and organic oils that make it naturally waterproof, immune to white ants/termites, and virtually immune to warping over centuries.",
                    points = listOf(
                        "Durability: Class 1 (50+ to 100+ years heirloom lifespan)",
                        "Grain: Golden amber to warm caramel with straight, elegant grain lines",
                        "Best suited for: Royal Beds, Intricate Floral Sofas, Mandirs & Outdoor Jhula/Swings",
                        "Seasoning: 45-day solar dehumidification kiln chamber (8-10% Moisture)"
                    )
                )
            }

            // Sheesham Breakdown
            item {
                WoodComparisonCard(
                    title = "North Indian Sheesham (Rosewood)",
                    botName = "Dalbergia sissoo",
                    accentColor = Color(0xFF8B3A1C),
                    summary = "A dense, heavy hardwood revered for its dramatic, expressive heartwood figure. Features alternating streaks of dark chocolate, auburn, and espresso tones.",
                    points = listOf(
                        "Durability: High impact resistance with exceptional screw-holding strength",
                        "Grain: Rich dramatic swirl, deep natural figure with natural luster",
                        "Best suited for: Heavy 8-Seater Dining Sets, Bookcases, Carved Swings",
                        "Seasoning: Steam-steeped kiln stabilization to eliminate timber tension"
                    )
                )
            }

            // Dark Walnut & Burl
            item {
                WoodComparisonCard(
                    title = "Kashmir Dark Walnut & Live-Edge Burl",
                    botName = "Juglans regia",
                    accentColor = Color(0xFF5C3317),
                    summary = "Prized by royal courts for its velvety chatoyancy and complex burl clusters. Hand-sanded to a glass-smooth finish.",
                    points = listOf(
                        "Durability: Moderate to heavy density with extraordinary dimensional stability",
                        "Grain: Spectacular marbled burl, curl patterns, and deep smoky hues",
                        "Best suited for: Epoxy River Conference/Dining Tables & Accent Armchairs",
                        "Seasoning: Dual-stage vacuum kiln stabilized"
                    )
                )
            }

            // Export Wooden Crate Specifications
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(WalnutCard)
                        .border(1.dp, WalnutBorder, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "📦 Custom Export Wooden Crates Standards",
                            color = GoldBright,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "• Layer 1: Breathable anti-static lint-free muslin shroud.\n• Layer 2: 12mm EPE shock absorption closed-cell foam wrapping.\n• Layer 3: Heavy-duty 5-ply corrugated edge armor protectors.\n• Layer 4: Moisture-absorbing silica gel bags inside heat-sealed poly-barrier.\n• Layer 5: ISPM-15 heat-treated & fumigated solid pine wooden crate with steel banding.",
                            color = Color(0xFFD6C8BC),
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            // WhatsApp Direct Action
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.horizontalGradient(listOf(WhatsAppGreen, Color(0xFF1EBE5D)))
                        )
                        .clickable { viewModel.launchDirectConciergeWhatsApp(context) }
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Consult Timber Specialist on WhatsApp (+91 63998 99031)",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun WoodComparisonCard(
    title: String,
    botName: String,
    accentColor: Color,
    summary: String,
    points: List<String>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(WalnutCardElevated)
            .border(1.dp, WalnutBorder, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = title,
                        color = Color.White,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = botName,
                        color = GoldPrimary,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(accentColor)
                )
            }

            Text(
                text = summary,
                color = Color(0xFFD6C8BC),
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                points.forEach { pt ->
                    Text(
                        text = "• $pt",
                        color = Color(0xFFAFA094),
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    )
                }
            }
        }
    }
}
