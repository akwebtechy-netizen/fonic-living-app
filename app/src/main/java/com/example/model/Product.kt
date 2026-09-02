package com.example.model

enum class ProductCategory(val displayName: String, val tagLine: String) {
    ALL("All Creations", "Complete bespoke portfolio"),
    LUXURY_BEDS("Luxury Beds", "Hand-carved royal master bed suites"),
    ROYAL_DINING("Royal Dining Sets", "Grand 6 to 12 seater handcrafted banquets"),
    CARVED_SOFAS("Carved Sofas", "Maharaja & Victorian heirloom seating"),
    WOODEN_JHULA("Wooden Jhula/Swings", "Solid brass chained architectural swings"),
    EPOXY_TABLES("Epoxy Tables", "Live-edge river tables with resin & burl wood")
}

data class WoodSpec(
    val woodType: String, // e.g. "Grade-A Nilambur Teak (Sagwan)"
    val botName: String, // e.g. "Tectona grandis"
    val grainDescription: String,
    val moistureContent: String, // e.g. "8% - 10% Kiln Dried"
    val durabilityClass: String, // e.g. "Class I (50+ Years)"
    val termiteResistance: String, // e.g. "Natural Tectoquione Oil Protected"
    val seasoningMethod: String, // e.g. "3-Stage Solar Kiln Seasoning"
    val carvingSuitability: String // e.g. "High precision 3D relief carving"
)

data class DimensionOption(
    val name: String, // e.g. "King Size (Imperial)"
    val dimensionsMetric: String, // e.g. "200 cm x 195 cm x 150 cm"
    val dimensionsImperial: String, // e.g. "78\" W x 84\" L x 62\" H"
    val idealRoomSize: String
)

data class WoodFinish(
    val id: String,
    val name: String,
    val description: String,
    val hexTone: Long
)

data class Product(
    val id: String,
    val name: String,
    val tagline: String,
    val category: ProductCategory,
    val priceTag: String = "Price on Request",
    val primaryWood: String, // e.g. "Teak / Sagwan"
    val secondaryWood: String? = "Sheesham / Rosewood Option",
    val woodSpecs: List<WoodSpec>,
    val description: String,
    val craftsmanshipDetails: List<String>,
    val standardDimensions: List<DimensionOption>,
    val availableFinishes: List<WoodFinish>,
    val exportPackaging: String = "Fumigated 7-Layer Solid Pine Crate with EPE Foam & Anti-Moisture Desiccants",
    val warrantyYears: Int = 15,
    val deliveryLeadTimeWeeks: String = "4 - 6 Weeks Bespoke Handcrafting",
    val isBestseller: Boolean = false,
    val isNewArrival: Boolean = false,
    val visualTheme: String = "gold_dark_walnut"
)
