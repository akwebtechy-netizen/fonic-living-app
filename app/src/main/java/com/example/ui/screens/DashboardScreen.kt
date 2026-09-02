package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.ui.components.BespokeInquirySheet
import com.example.ui.components.CategorySelector
import com.example.ui.components.GlobalDeliveryBanner
import com.example.ui.components.HeroShowcaseBanner
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

@Composable
fun DashboardScreen(
    viewModel: FurnitureViewModel,
    onProductClick: (Product) -> Unit,
    onBookmarksClick: () -> Unit,
    onWoodGuideClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val products by viewModel.filteredProducts.collectAsState()
    val favoriteIds by viewModel.favoriteIds.collectAsState()

    var activeInquiryProduct by remember { mutableStateOf<Product?>(null) }

    Scaffold(
        topBar = {
            LuxuryTopAppBar(
                onConciergeClick = { viewModel.launchDirectConciergeWhatsApp(context) },
                onBookmarksClick = onBookmarksClick,
                bookmarkCount = favoriteIds.size,
                onWoodGuideClick = onWoodGuideClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.launchDirectConciergeWhatsApp(context) },
                containerColor = WhatsAppGreen,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier
                    .navigationBarsPadding()
                    .testTag("floating_whatsapp_concierge")
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "WhatsApp Concierge (+916399899031)",
                    modifier = Modifier.size(26.dp)
                )
            }
        },
        containerColor = WalnutDark,
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("dashboard_product_list"),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Search Bar
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { viewModel.setSearchQuery(it) },
                        placeholder = {
                            Text(
                                text = "Search Maharaja beds, dining sets, swings...",
                                color = Color(0xFFAFA094),
                                fontSize = 13.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = GoldPrimary
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear",
                                        tint = Color(0xFFAFA094)
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("search_bar_input"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = GoldPrimary,
                            unfocusedBorderColor = WalnutBorder,
                            focusedContainerColor = WalnutCard,
                            unfocusedContainerColor = WalnutCard,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = GoldBright
                        )
                    )
                }
            }

            // Hero Showcase Banner (shown when no search query)
            if (searchQuery.isBlank() && selectedCategory == ProductCategory.ALL) {
                item {
                    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                        HeroShowcaseBanner(
                            onExploreClick = { viewModel.selectCategory(ProductCategory.LUXURY_BEDS) },
                            onConsultClick = { viewModel.launchDirectConciergeWhatsApp(context) }
                        )
                    }
                }
            }

            // Global Delivery & Trust Banner
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    GlobalDeliveryBanner(
                        onViewExportGuide = onWoodGuideClick
                    )
                }
            }

            // Filterable Category Chips
            item {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Curated Masterpiece Collections",
                            color = GoldBright,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Text(
                            text = "${products.size} Pieces",
                            color = Color(0xFFAFA094),
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    CategorySelector(
                        selectedCategory = selectedCategory,
                        onCategorySelected = { viewModel.selectCategory(it) }
                    )
                }
            }

            // Empty state if search returns zero
            if (products.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "No bespoke pieces found for \"$searchQuery\"",
                                color = Color.White,
                                fontFamily = FontFamily.Serif,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Our craftsmen can build any custom blueprint on request.",
                                color = Color(0xFFAFA094),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            } else {
                // Product Grid / List
                items(products, key = { it.id }) { product ->
                    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                        ProductCard(
                            product = product,
                            isFavorite = favoriteIds.contains(product.id),
                            onProductClick = { onProductClick(product) },
                            onFavoriteToggle = { viewModel.toggleFavorite(product.id) },
                            onWhatsAppInquiry = { activeInquiryProduct = product }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }

    // Bespoke Inquiry Modal Sheet
    if (activeInquiryProduct != null) {
        val prod = activeInquiryProduct!!
        BespokeInquirySheet(
            product = prod,
            selectedWood = prod.primaryWood,
            selectedDimension = prod.standardDimensions.firstOrNull()?.name ?: "Standard",
            selectedFinish = prod.availableFinishes.firstOrNull()?.name ?: "Imperial Dark Walnut Gloss",
            onDismiss = { activeInquiryProduct = null },
            onSubmitWhatsApp = { wood, dim, fin, name, city, country, notes ->
                viewModel.sendWhatsAppInquiry(
                    context = context,
                    product = prod,
                    customWood = wood,
                    customDim = dim,
                    customFin = fin,
                    clientName = name,
                    clientCity = city,
                    clientCountry = country,
                    notes = notes
                )
                activeInquiryProduct = null
            }
        )
    }
}
