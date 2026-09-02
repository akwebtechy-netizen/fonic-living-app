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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.data.FurnitureRepository
import com.example.data.local.InquiryEntity
import com.example.model.Product
import com.example.ui.components.LuxuryTopAppBar
import com.example.ui.components.ProductCard
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.WalnutBorder
import com.example.ui.theme.WalnutCard
import com.example.ui.theme.WalnutDark
import com.example.ui.theme.WalnutSurface
import com.example.ui.theme.WhatsAppGreen
import com.example.viewmodel.FurnitureViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SavedInquiriesScreen(
    viewModel: FurnitureViewModel,
    onProductClick: (Product) -> Unit,
    onBackClick: () -> Unit,
    onWoodGuideClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val favoriteIds by viewModel.favoriteIds.collectAsState()
    val inquiries by viewModel.inquiryHistory.collectAsState()

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Saved Creations (${favoriteIds.size})", "WhatsApp Inquiries (${inquiries.size})")

    val favoriteProducts = remember(favoriteIds) {
        FurnitureRepository.products.filter { favoriteIds.contains(it.id) }
    }

    Scaffold(
        topBar = {
            LuxuryTopAppBar(
                title = "Atelier Concierge & Vault",
                onBackClick = onBackClick,
                onConciergeClick = { viewModel.launchDirectConciergeWhatsApp(context) },
                onBookmarksClick = {},
                bookmarkCount = favoriteIds.size,
                onWoodGuideClick = onWoodGuideClick
            )
        },
        containerColor = WalnutDark,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("saved_inquiries_screen")
        ) {
            // Tabs
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = WalnutSurface,
                contentColor = GoldPrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = GoldBright
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTabIndex == index) GoldBright else Color(0xFFAFA094),
                                fontSize = 12.sp
                            )
                        }
                    )
                }
            }

            if (selectedTabIndex == 0) {
                // Bookmarked Products
                if (favoriteProducts.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = null,
                                tint = GoldPrimary.copy(alpha = 0.5f),
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Your Vault is Empty",
                                color = Color.White,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Bookmark your preferred bespoke beds, dining tables, or swings from the collection to review anytime.",
                                color = Color(0xFFA6968B),
                                fontSize = 12.sp,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(favoriteProducts, key = { it.id }) { product ->
                            ProductCard(
                                product = product,
                                isFavorite = true,
                                onProductClick = { onProductClick(product) },
                                onFavoriteToggle = { viewModel.toggleFavorite(product.id) },
                                onWhatsAppInquiry = {
                                    viewModel.sendWhatsAppInquiry(context, product)
                                }
                            )
                        }
                    }
                }
            } else {
                // Inquiry History
                if (inquiries.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = null,
                                tint = GoldPrimary.copy(alpha = 0.5f),
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "No Recent Inquiries",
                                color = Color.White,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Inquiries sent directly to WhatsApp (+91 63998 99031) will be saved here for your project records.",
                                color = Color(0xFFA6968B),
                                fontSize = 12.sp,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(inquiries, key = { it.id }) { inquiry ->
                            InquiryHistoryCard(
                                inquiry = inquiry,
                                onDelete = { viewModel.deleteInquiry(inquiry.id) },
                                onReInquire = {
                                    val prod = FurnitureRepository.getProductById(inquiry.productId)
                                    if (prod != null) {
                                        viewModel.sendWhatsAppInquiry(
                                            context = context,
                                            product = prod,
                                            customWood = inquiry.selectedWood,
                                            customDim = inquiry.dimensions,
                                            customFin = inquiry.selectedFinish,
                                            clientName = inquiry.clientName,
                                            clientCity = inquiry.clientCity,
                                            clientCountry = inquiry.clientCountry,
                                            notes = inquiry.specialNotes
                                        )
                                    } else {
                                        viewModel.launchDirectConciergeWhatsApp(context)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InquiryHistoryCard(
    inquiry: InquiryEntity,
    onDelete: () -> Unit,
    onReInquire: () -> Unit
) {
    val dateStr = remember(inquiry.timestamp) {
        SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date(inquiry.timestamp))
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(WalnutCard)
            .border(1.dp, WalnutBorder, RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = inquiry.productName,
                        color = Color.White,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "$dateStr • ${inquiry.category}",
                        color = Color(0xFFA6968B),
                        fontSize = 10.sp
                    )
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color(0xFFA6968B),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E130D))
                    .padding(8.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(text = "🪵 Timber: ${inquiry.selectedWood}", color = GoldBright, fontSize = 11.sp)
                    Text(text = "📐 Dimensions: ${inquiry.dimensions}", color = Color(0xFFD6C8BC), fontSize = 11.sp)
                    Text(text = "🎨 Polish: ${inquiry.selectedFinish}", color = Color(0xFFD6C8BC), fontSize = 11.sp)
                    if (inquiry.clientCity.isNotBlank()) {
                        Text(text = "📍 Destination: ${inquiry.clientCity}, ${inquiry.clientCountry}", color = Color(0xFFA6968B), fontSize = 10.sp)
                    }
                }
            }

            // Quick Re-open on WhatsApp button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        Brush.horizontalGradient(listOf(WhatsAppGreen, Color(0xFF1EBE5D)))
                    )
                    .clickable { onReInquire() }
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Chat,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "Continue WhatsApp Chat with Carver",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
