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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.example.model.DimensionOption
import com.example.model.Product
import com.example.model.WoodFinish
import com.example.ui.components.BespokeInquirySheet
import com.example.ui.components.LuxuryTopAppBar
import com.example.ui.components.ProductVisualArtwork
import com.example.ui.components.WoodSpecBreakdownSection
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.TeakAmber
import com.example.ui.theme.WalnutBorder
import com.example.ui.theme.WalnutCard
import com.example.ui.theme.WalnutCardElevated
import com.example.ui.theme.WalnutDark
import com.example.ui.theme.WalnutSurface
import com.example.ui.theme.WhatsAppGreen
import com.example.viewmodel.FurnitureViewModel

@Composable
fun ProductDetailScreen(
    product: Product,
    viewModel: FurnitureViewModel,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onWoodGuideClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val favoriteIds by viewModel.favoriteIds.collectAsState()
    val isFavorite = favoriteIds.contains(product.id)

    var selectedGalleryIndex by remember { mutableIntStateOf(0) }
    var selectedWood by remember { mutableStateOf(product.primaryWood) }
    var selectedDimension by remember { mutableStateOf(product.standardDimensions.firstOrNull()?.name ?: "Imperial Standard") }
    var selectedFinish by remember { mutableStateOf(product.availableFinishes.firstOrNull()?.name ?: "Imperial Dark Walnut Gloss") }
    var showInquirySheet by remember { mutableStateOf(false) }

    val galleryAngles = listOf(
        "Master Grand Elevation",
        "Hand-Carved Relief Detail",
        "Mortise Joinery & Grain",
        "Palatial Suite Ambience"
    )

    Scaffold(
        topBar = {
            LuxuryTopAppBar(
                title = product.name,
                onBackClick = onBackClick,
                onConciergeClick = { viewModel.launchDirectConciergeWhatsApp(context) },
                onBookmarksClick = onBookmarksClick,
                bookmarkCount = favoriteIds.size,
                onWoodGuideClick = onWoodGuideClick
            )
        },
        bottomBar = {
            // Sticky Bottom Luxury CTA Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                WalnutSurface.copy(alpha = 0.95f),
                                WalnutDark
                            )
                        )
                    )
                    .border(0.8.dp, WalnutBorder)
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Price on Request Tag Column
                    Column(modifier = Modifier.weight(0.9f)) {
                        Text(
                            text = "OFFICIAL ESTIMATE",
                            color = Color(0xFFAFA094),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = product.priceTag,
                            color = GoldBright,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }

                    // Request Bespoke Quote Button (WhatsApp Integration)
                    ElevatedButton(
                        onClick = { showInquirySheet = true },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1.6f)
                            .height(48.dp)
                            .testTag("detail_request_quote_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Chat,
                                contentDescription = "WhatsApp",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Request Bespoke Quote",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        },
        containerColor = WalnutDark,
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("product_detail_content"),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Hero Gallery Slider View
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Column {
                        // Main Artwork Canvas
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp)
                        ) {
                            ProductVisualArtwork(
                                product = product,
                                galleryIndex = selectedGalleryIndex,
                                modifier = Modifier.fillMaxSize()
                            )

                            // Favorite button on top right
                            IconButton(
                                onClick = { viewModel.toggleFavorite(product.id) },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(12.dp)
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xCC1A0F0A))
                                    .border(1.dp, GoldPrimary, CircleShape)
                                    .testTag("detail_favorite_button")
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                    contentDescription = "Bookmark",
                                    tint = if (isFavorite) GoldBright else Color(0xFFD6C8BC),
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            // View Name Overlay
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xCC000000))
                                    .border(0.6.dp, WalnutBorder, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "🔍 ${galleryAngles[selectedGalleryIndex]}",
                                    color = GoldBright,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Gallery Thumbnail Thumb Selector
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            galleryAngles.forEachIndexed { index, angleName ->
                                val isSelected = selectedGalleryIndex == index
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(34.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) Color(0xFF382316) else WalnutCard)
                                        .border(
                                            1.dp,
                                            if (isSelected) GoldBright else WalnutBorder,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { selectedGalleryIndex = index }
                                        .padding(horizontal = 4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Angle ${index + 1}",
                                        color = if (isSelected) GoldBright else Color(0xFFAFA094),
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Title & Luxury Badge Header
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = product.category.displayName.uppercase(),
                            color = GoldPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            letterSpacing = 1.sp
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFF332014))
                                .border(0.6.dp, GoldPrimary, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "🏷️ ${product.priceTag}",
                                color = GoldBright,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = product.name,
                        color = Color.White,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        lineHeight = 28.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = product.description,
                        color = Color(0xFFD6C8BC),
                        fontSize = 13.sp,
                        lineHeight = 19.sp
                    )
                }
            }

            // Wood Specification Breakdown (Core Feature 3)
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    WoodSpecBreakdownSection(
                        woodSpecs = product.woodSpecs,
                        selectedWood = selectedWood,
                        onWoodSelected = { selectedWood = it }
                    )
                }
            }

            // Custom Dimensions Guide (Core Feature 3)
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    CustomDimensionsGuideCard(
                        dimensions = product.standardDimensions,
                        selectedDimension = selectedDimension,
                        onDimensionSelected = { selectedDimension = it }
                    )
                }
            }

            // Available Luxury Finishes Swatch Selector
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    FinishesPickerCard(
                        finishes = product.availableFinishes,
                        selectedFinish = selectedFinish,
                        onFinishSelected = { selectedFinish = it }
                    )
                }
            }

            // Craftsmanship & Heirloom Joinery Details
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(WalnutCard)
                        .border(1.dp, WalnutBorder, RoundedCornerShape(18.dp))
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "Artisan Craftsmanship Highlights",
                            color = GoldBright,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        product.craftsmanshipDetails.forEach { detail ->
                            Row(
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(16.dp).padding(top = 2.dp)
                                )
                                Text(
                                    text = detail,
                                    color = Color(0xFFD6C8BC),
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }
            }

            // Export Wooden Crate & Global Transit Card
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(
                            Brush.verticalGradient(
                                listOf(Color(0xFF28180F), WalnutCard)
                            )
                        )
                        .border(1.dp, GoldPrimary.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Inventory2,
                                contentDescription = null,
                                tint = GoldBright,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Export Wooden Crate Packaging",
                                color = GoldBright,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                        Text(
                            text = product.exportPackaging,
                            color = Color(0xFFD6C8BC),
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "⏱️ ${product.deliveryLeadTimeWeeks}",
                                color = GoldPrimary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "🛡️ ${product.warrantyYears} Years Structural Warranty",
                                color = Color(0xFFD6C8BC),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    }

    // Modal Inquiry Sheet
    if (showInquirySheet) {
        BespokeInquirySheet(
            product = product,
            selectedWood = selectedWood,
            selectedDimension = selectedDimension,
            selectedFinish = selectedFinish,
            onDismiss = { showInquirySheet = false },
            onSubmitWhatsApp = { wood, dim, fin, name, city, country, notes ->
                viewModel.sendWhatsAppInquiry(
                    context = context,
                    product = product,
                    customWood = wood,
                    customDim = dim,
                    customFin = fin,
                    clientName = name,
                    clientCity = city,
                    clientCountry = country,
                    notes = notes
                )
                showInquirySheet = false
            }
        )
    }
}

@Composable
private fun CustomDimensionsGuideCard(
    dimensions: List<DimensionOption>,
    selectedDimension: String,
    onDimensionSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(WalnutCard)
            .border(1.dp, WalnutBorder, RoundedCornerShape(18.dp))
            .padding(16.dp)
            .testTag("custom_dimensions_guide_card")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Custom Dimensions Guide",
                    color = GoldBright,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Select scale or request architectural custom sizing",
                    color = Color(0xFFAFA094),
                    fontSize = 11.sp
                )
            }
            Icon(
                imageVector = Icons.Default.Straighten,
                contentDescription = null,
                tint = GoldPrimary,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            dimensions.forEach { option ->
                val isSelected = selectedDimension == option.name
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) Color(0xFF382316) else Color(0xFF1E130D))
                        .border(
                            1.dp,
                            if (isSelected) GoldBright else WalnutBorder,
                            RoundedCornerShape(10.dp)
                        )
                        .clickable { onDimensionSelected(option.name) }
                        .padding(12.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = option.name,
                                color = if (isSelected) GoldBright else Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            if (isSelected) {
                                Text(
                                    text = "SELECTED",
                                    color = GoldBright,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Text(
                            text = "📐 Imperial: ${option.dimensionsImperial}",
                            color = Color(0xFFD6C8BC),
                            fontSize = 11.sp
                        )
                        Text(
                            text = "📏 Metric: ${option.dimensionsMetric}",
                            color = Color(0xFFA6968B),
                            fontSize = 10.sp
                        )
                        Text(
                            text = "🏠 Room fit: ${option.idealRoomSize}",
                            color = GoldPrimary.copy(alpha = 0.8f),
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FinishesPickerCard(
    finishes: List<WoodFinish>,
    selectedFinish: String,
    onFinishSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(WalnutCard)
            .border(1.dp, WalnutBorder, RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Artisan Polish & Surface Finishes",
            color = GoldBright,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = "Hand-applied natural hardwax oils, French polish & 24k gold leaf",
            color = Color(0xFFAFA094),
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            finishes.forEach { finish ->
                val isSelected = selectedFinish == finish.name
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) Color(0xFF382316) else Color(0xFF1E130D))
                        .border(
                            1.dp,
                            if (isSelected) GoldBright else WalnutBorder,
                            RoundedCornerShape(10.dp)
                        )
                        .clickable { onFinishSelected(finish.name) }
                        .padding(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Swatch circle
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .clip(CircleShape)
                                .background(Color(finish.hexTone))
                                .border(1.dp, GoldBright, CircleShape)
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = finish.name,
                                color = if (isSelected) GoldBright else Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                            Text(
                                text = finish.description,
                                color = Color(0xFFA6968B),
                                fontSize = 10.sp,
                                lineHeight = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
