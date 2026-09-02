package com.example.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FurnitureRepository
import com.example.data.local.FavoriteEntity
import com.example.data.local.FonicLivingDatabase
import com.example.data.local.InquiryEntity
import com.example.model.DimensionOption
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.model.WoodFinish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class BespokeCustomizationState(
    val selectedWood: String = "",
    val selectedDimension: String = "",
    val selectedFinish: String = "",
    val clientName: String = "",
    val clientCity: String = "",
    val clientCountry: String = "India",
    val customNotes: String = ""
)

class FurnitureViewModel(application: Application) : AndroidViewModel(application) {

    private val db = FonicLivingDatabase.getInstance(application)
    private val dao = db.dao()

    private val _selectedCategory = MutableStateFlow(ProductCategory.ALL)
    val selectedCategory: StateFlow<ProductCategory> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    private val _customizationState = MutableStateFlow(BespokeCustomizationState())
    val customizationState: StateFlow<BespokeCustomizationState> = _customizationState.asStateFlow()

    val favoriteIds: StateFlow<Set<String>> = dao.getFavoriteProductIds()
        .combine(MutableStateFlow(Unit)) { list, _ -> list.toSet() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    val inquiryHistory: StateFlow<List<InquiryEntity>> = dao.getAllInquiries()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val filteredProducts: StateFlow<List<Product>> = combine(
        _selectedCategory,
        _searchQuery
    ) { category, query ->
        val list = FurnitureRepository.getProductsByCategory(category)
        if (query.isBlank()) {
            list
        } else {
            list.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.tagline.contains(query, ignoreCase = true) ||
                it.primaryWood.contains(query, ignoreCase = true) ||
                it.category.displayName.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FurnitureRepository.products)

    fun selectCategory(category: ProductCategory) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectProduct(product: Product?) {
        _selectedProduct.value = product
        if (product != null) {
            _customizationState.value = BespokeCustomizationState(
                selectedWood = product.woodSpecs.firstOrNull()?.woodType ?: product.primaryWood,
                selectedDimension = product.standardDimensions.firstOrNull()?.name ?: "Standard Bespoke Fit",
                selectedFinish = product.availableFinishes.firstOrNull()?.name ?: "Imperial Dark Walnut Gloss"
            )
        }
    }

    fun updateCustomWood(wood: String) {
        _customizationState.value = _customizationState.value.copy(selectedWood = wood)
    }

    fun updateCustomDimension(dimension: String) {
        _customizationState.value = _customizationState.value.copy(selectedDimension = dimension)
    }

    fun updateCustomFinish(finish: String) {
        _customizationState.value = _customizationState.value.copy(selectedFinish = finish)
    }

    fun updateClientDetails(name: String, city: String, country: String, notes: String) {
        _customizationState.value = _customizationState.value.copy(
            clientName = name,
            clientCity = city,
            clientCountry = country,
            customNotes = notes
        )
    }

    fun toggleFavorite(productId: String) {
        viewModelScope.launch {
            if (favoriteIds.value.contains(productId)) {
                dao.removeFavorite(productId)
            } else {
                dao.addFavorite(FavoriteEntity(productId = productId))
            }
        }
    }

    fun sendWhatsAppInquiry(
        context: Context,
        product: Product,
        customWood: String? = null,
        customDim: String? = null,
        customFin: String? = null,
        clientName: String = "",
        clientCity: String = "",
        clientCountry: String = "",
        notes: String = ""
    ) {
        val finalWood = customWood ?: _customizationState.value.selectedWood.ifBlank { product.primaryWood }
        val finalDim = customDim ?: _customizationState.value.selectedDimension.ifBlank { product.standardDimensions.firstOrNull()?.name ?: "Standard" }
        val finalFinish = customFin ?: _customizationState.value.selectedFinish.ifBlank { product.availableFinishes.firstOrNull()?.name ?: "Imperial Dark Walnut" }
        val finalName = clientName.ifBlank { _customizationState.value.clientName.ifBlank { "Valued Patron" } }
        val finalCity = clientCity.ifBlank { _customizationState.value.clientCity }
        val finalCountry = clientCountry.ifBlank { _customizationState.value.clientCountry.ifBlank { "India / International" } }
        val finalNotes = notes.ifBlank { _customizationState.value.customNotes }

        val destinationStr = if (finalCity.isNotBlank()) "$finalCity, $finalCountry" else finalCountry

        val message = buildString {
            append("👑 *FONIC LIVING — Bespoke Furniture Inquiry*\n")
            append("━━━━━━━━━━━━━━━━━━━━\n")
            append("✨ *Product:* ${product.name}\n")
            append("🏛️ *Category:* ${product.category.displayName}\n")
            append("🪵 *Selected Timber:* $finalWood\n")
            append("📐 *Dimensions:* $finalDim\n")
            append("🎨 *Bespoke Finish:* $finalFinish\n")
            append("🏷️ *Price Tag:* ${product.priceTag}\n")
            append("🌍 *Delivery Destination:* $destinationStr\n")
            if (finalName.isNotBlank()) {
                append("👤 *Client Name:* $finalName\n")
            }
            if (finalNotes.isNotBlank()) {
                append("💬 *Custom Notes:* $finalNotes\n")
            }
            append("━━━━━━━━━━━━━━━━━━━━\n")
            append("Please share the custom quote, export wooden crate specifications, and handcrafting schedule. Thank you!")
        }

        // Save inquiry to local Room DB for record keeping
        viewModelScope.launch {
            dao.insertInquiry(
                InquiryEntity(
                    productId = product.id,
                    productName = product.name,
                    category = product.category.displayName,
                    selectedWood = finalWood,
                    dimensions = finalDim,
                    selectedFinish = finalFinish,
                    clientName = finalName,
                    clientCity = finalCity,
                    clientCountry = finalCountry,
                    specialNotes = finalNotes
                )
            )
        }

        launchWhatsApp(context, "+916399899031", message)
    }

    fun launchDirectConciergeWhatsApp(context: Context) {
        val message = "👑 *FONIC LIVING BESPOKE CONCIERGE*\nNamaste! I would like to consult with your Master Craftsman regarding a custom bespoke furniture piece for my residence."
        launchWhatsApp(context, "+916399899031", message)
    }

    private fun launchWhatsApp(context: Context, rawPhone: String, message: String) {
        val cleanPhone = rawPhone.replace("+", "").replace(" ", "").replace("-", "")
        try {
            val encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString())
            val url = "https://api.whatsapp.com/send?phone=$cleanPhone&text=$encodedMessage"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            try {
                // Fallback to direct intent
                val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$cleanPhone?text=${URLEncoder.encode(message, "UTF-8")}")).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(fallbackIntent)
            } catch (ex: Exception) {
                Toast.makeText(context, "Could not open WhatsApp. Please contact +91 63998 99031", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun deleteInquiry(id: Long) {
        viewModelScope.launch {
            dao.deleteInquiry(id)
        }
    }
}
