package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.ui.theme.WalnutDark
import com.example.ui.theme.WalnutSurface
import com.example.ui.theme.WhatsAppGreen

@Composable
fun LuxuryTopAppBar(
    title: String? = null,
    onBackClick: (() -> Unit)? = null,
    onConciergeClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    bookmarkCount: Int = 0,
    onWoodGuideClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        WalnutDark,
                        WalnutSurface
                    )
                )
            )
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left: Back button or Royal Brand Logo
            if (onBackClick != null) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(WalnutCard)
                        .border(1.dp, WalnutBorder, CircleShape)
                        .testTag("back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = GoldPrimary
                    )
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    // Gold Crown/Diamond Logo Emblem
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                Brush.linearGradient(
                                    listOf(GoldDark, GoldBright, GoldPrimary)
                                )
                            )
                            .padding(1.dp)
                            .clip(RoundedCornerShape(7.dp))
                            .background(WalnutDark),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "FL",
                            color = GoldBright,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text(
                            text = "FONIC LIVING",
                            color = GoldBright,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            letterSpacing = 1.2.sp
                        )
                        Text(
                            text = "BESPOKE HANDCRAFTED WOOD",
                            color = GoldPrimary.copy(alpha = 0.85f),
                            fontWeight = FontWeight.Medium,
                            fontSize = 9.sp,
                            letterSpacing = 0.8.sp
                        )
                    }
                }
            }

            if (title != null && onBackClick != null) {
                Text(
                    text = title,
                    color = GoldBright,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            // Right Action Icons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Wood Heritage Guide Icon
                IconButton(
                    onClick = onWoodGuideClick,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(WalnutCard)
                        .border(1.dp, WalnutBorder, CircleShape)
                        .testTag("wood_guide_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Wood Specifications Guide",
                        tint = GoldPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Bookmarks / Saved Quotes
                IconButton(
                    onClick = onBookmarksClick,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(WalnutCard)
                        .border(1.dp, WalnutBorder, CircleShape)
                        .testTag("saved_bookmarks_button")
                ) {
                    BadgedBox(
                        badge = {
                            if (bookmarkCount > 0) {
                                Badge(
                                    containerColor = GoldPrimary,
                                    contentColor = WalnutDark
                                ) {
                                    Text(
                                        text = "$bookmarkCount",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (bookmarkCount > 0) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Saved Pieces & Inquiries",
                            tint = GoldPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // WhatsApp Master Concierge Direct CTA
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            Brush.horizontalGradient(
                                listOf(WhatsAppGreen, Color(0xFF1EBE5D))
                            )
                        )
                        .clickable { onConciergeClick() }
                        .padding(horizontal = 10.dp, vertical = 7.dp)
                        .testTag("direct_concierge_whatsapp_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "WhatsApp Concierge",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Concierge",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}
