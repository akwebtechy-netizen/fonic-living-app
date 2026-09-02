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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Product
import com.example.model.WoodFinish
import com.example.ui.theme.GoldBright
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.WalnutBorder
import com.example.ui.theme.WalnutCard
import com.example.ui.theme.WalnutCardElevated
import com.example.ui.theme.WalnutDark
import com.example.ui.theme.WalnutSurface
import com.example.ui.theme.WhatsAppGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BespokeInquirySheet(
    product: Product,
    selectedWood: String,
    selectedDimension: String,
    selectedFinish: String,
    onDismiss: () -> Unit,
    onSubmitWhatsApp: (
        wood: String,
        dimension: String,
        finish: String,
        clientName: String,
        clientCity: String,
        clientCountry: String,
        notes: String
    ) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var currentWood by remember { mutableStateOf(selectedWood.ifBlank { product.primaryWood }) }
    var currentDim by remember { mutableStateOf(selectedDimension.ifBlank { product.standardDimensions.firstOrNull()?.name ?: "Standard Bespoke" }) }
    var currentFinish by remember { mutableStateOf(selectedFinish.ifBlank { product.availableFinishes.firstOrNull()?.name ?: "Imperial Dark Walnut Gloss" }) }

    var clientName by remember { mutableStateOf("") }
    var clientCity by remember { mutableStateOf("") }
    var clientCountry by remember { mutableStateOf("India") }
    var customNotes by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = WalnutSurface,
        dragHandle = null,
        modifier = Modifier.navigationBarsPadding().testTag("bespoke_inquiry_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .verticalScroll(scrollState)
        ) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                Brush.linearGradient(listOf(GoldDark, GoldBright))
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.WorkspacePremium,
                            contentDescription = null,
                            tint = WalnutDark,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "Request Bespoke Quote",
                            color = GoldBright,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Direct Artisan Concierge • +91 63998 99031",
                            color = Color(0xFFAFA094),
                            fontSize = 11.sp
                        )
                    }
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(WalnutCard)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Product Summary Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(WalnutCardElevated)
                    .border(1.dp, WalnutBorder, RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = product.name,
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = product.category.displayName,
                            color = GoldPrimary,
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
                            text = product.priceTag,
                            color = GoldBright,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Step 1: Wood Selection
            Text(
                text = "1. SELECT BESPOKE TIMBER",
                color = GoldBright,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                val woodOptions = listOf(
                    "Grade-A Nilambur Teak (Sagwan) - Gold Standard",
                    "Old-Growth Indian Sheesham (Rosewood)",
                    "Kashmir / Dark Walnut Burl Timber"
                )
                woodOptions.forEach { wood ->
                    val isSelected = currentWood.startsWith(wood.take(10)) || currentWood == wood
                    OptionSelectRow(
                        title = wood,
                        isSelected = isSelected,
                        onClick = { currentWood = wood }
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Step 2: Dimensions Guide & Selection
            Text(
                text = "2. CHOOSE SCALE & DIMENSIONS",
                color = GoldBright,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                product.standardDimensions.forEach { dim ->
                    val isSelected = currentDim == dim.name
                    OptionSelectRow(
                        title = "${dim.name} (${dim.dimensionsImperial})",
                        sub = dim.dimensionsMetric,
                        isSelected = isSelected,
                        onClick = { currentDim = dim.name }
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Step 3: Polish & Finish
            Text(
                text = "3. POLISH & ARTISAN FINISH",
                color = GoldBright,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                product.availableFinishes.forEach { finish ->
                    val isSelected = currentFinish == finish.name
                    OptionSelectRow(
                        title = finish.name,
                        sub = finish.description,
                        isSelected = isSelected,
                        onClick = { currentFinish = finish.name }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Client & Delivery Destination
            Text(
                text = "4. PATRON & DESTINATION DETAILS",
                color = GoldBright,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = clientName,
                onValueChange = { clientName = it },
                label = { Text("Your Name / Architect / Studio Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().testTag("inquiry_client_name"),
                colors = luxuryTextFieldColors()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = clientCity,
                    onValueChange = { clientCity = it },
                    label = { Text("City (e.g., Dubai, London, Delhi)") },
                    singleLine = true,
                    modifier = Modifier.weight(1f).testTag("inquiry_client_city"),
                    colors = luxuryTextFieldColors()
                )
                OutlinedTextField(
                    value = clientCountry,
                    onValueChange = { clientCountry = it },
                    label = { Text("Country") },
                    singleLine = true,
                    modifier = Modifier.weight(1f).testTag("inquiry_client_country"),
                    colors = luxuryTextFieldColors()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = customNotes,
                onValueChange = { customNotes = it },
                label = { Text("Custom specifications, room blueprints or carving notes (Optional)") },
                modifier = Modifier.fillMaxWidth().height(84.dp).testTag("inquiry_custom_notes"),
                colors = luxuryTextFieldColors()
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Submit to WhatsApp Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(WhatsAppGreen, Color(0xFF1EBE5D))
                        )
                    )
                    .clickable {
                        onSubmitWhatsApp(
                            currentWood,
                            currentDim,
                            currentFinish,
                            clientName,
                            clientCity,
                            clientCountry,
                            customNotes
                        )
                    }
                    .testTag("submit_whatsapp_quote_button"),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Chat,
                        contentDescription = "WhatsApp",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Send Inquiry via WhatsApp (+91 63998 99031)",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color(0xFFAFA094),
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Direct connection with Saharanpur Master Craftsman atelier",
                    color = Color(0xFFAFA094),
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
private fun OptionSelectRow(
    title: String,
    sub: String? = null,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) Color(0xFF382316) else WalnutCard)
            .border(
                1.dp,
                if (isSelected) GoldBright else WalnutBorder,
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = if (isSelected) GoldBright else Color.White,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 12.sp
                )
                if (sub != null) {
                    Text(
                        text = sub,
                        color = Color(0xFFA6968B),
                        fontSize = 10.sp
                    )
                }
            }
            if (isSelected) {
                Text(
                    text = "✓",
                    color = GoldBright,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun luxuryTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = GoldPrimary,
    unfocusedBorderColor = WalnutBorder,
    focusedLabelColor = GoldBright,
    unfocusedLabelColor = Color(0xFFAFA094),
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    cursorColor = GoldBright
)
