package com.example.data

import com.example.model.DimensionOption
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.model.WoodFinish
import com.example.model.WoodSpec

object FurnitureRepository {

    private val TEAK_SPEC = WoodSpec(
        woodType = "Grade-A Nilambur Teak (Sagwan)",
        botName = "Tectona grandis",
        grainDescription = "Straight to wavy golden honey grain with natural rich silica & natural teak oils preventing rot",
        moistureContent = "8% - 10% Vacuum Kiln-Dried & Climate Seasoned",
        durabilityClass = "Class I (50+ Years Heirloom Life)",
        termiteResistance = "Natural Tectoquione Oil Protected (100% Termite & Borer Proof)",
        seasoningMethod = "3-Stage Computerized Solar Kiln Dehumidification",
        carvingSuitability = "Flawless for intricate 3D architectural floral relief carving"
    )

    private val SHEESHAM_SPEC = WoodSpec(
        woodType = "Old-Growth North Indian Rosewood (Sheesham)",
        botName = "Dalbergia sissoo",
        grainDescription = "Exquisite high-contrast interlocking grain featuring deep espresso and amber heartwood striations",
        moistureContent = "9% - 11% Chamber Seasoned",
        durabilityClass = "Class I Heavy Hardwood (Extremely Dense & Impact Resistant)",
        termiteResistance = "Pressure Vacuum Impregnated Organic Boron Treated",
        seasoningMethod = "60-Day Natural Seasoning followed by Steam Kiln Stabilization",
        carvingSuitability = "Superior chisel hold for sharp geometric & traditional jali fretwork"
    )

    private val WALNUT_SPEC = WoodSpec(
        woodType = "Kashmir / American Dark Walnut Timber",
        botName = "Juglans regia",
        grainDescription = "Lustrous wavy chocolate to dark espresso grain with rare burl swirls and velvety satin texture",
        moistureContent = "7% - 9% Precision Moisture Controlled",
        durabilityClass = "Class II Fine Furniture Timber",
        termiteResistance = "Seasoned Core with Natural Tannin Defense",
        seasoningMethod = "Controlled Temperature Kiln Conditioning",
        carvingSuitability = "Silky smooth hand-sanding response and ultra-crisp fluted details"
    )

    private val STANDARD_FINISHES = listOf(
        WoodFinish("wf_dark_walnut", "Imperial Dark Walnut Gloss", "Multi-layer hand-rubbed French polish with deep walnut tones", 0xFF281810),
        WoodFinish("wf_natural_teak", "Natural Teak Honey Silk", "Non-toxic organic hardwax oil highlighting natural grain & warmth", 0xFFB3672B),
        WoodFinish("wf_gold_leaf", "Antique Gold Leaf Gilding", "Real 24k champagne gold foil accents hand-applied on floral carvings", 0xFFD4AF37),
        WoodFinish("wf_smoked_oak", "Smoked Charcoal Walnut", "Contemporary deep matte smoked wood finish with open grain pore texture", 0xFF1C1A18)
    )

    private val EPOXY_FINISHES = listOf(
        WoodFinish("wf_ocean_epoxy", "Deep Ocean Blue Ultra-Clear Resin", "Crystal diamond grade optical epoxy with pearlescent sea swirl pigments", 0xFF1565C0),
        WoodFinish("wf_emerald_epoxy", "Emerald Valley Metallic Resin", "Rich jade green translucent epoxy with brass dust mica reflections", 0xFF00695C),
        WoodFinish("wf_gold_epoxy", "Liquid Champagne Gold Resin", "Subtle warm gold translucent resin contrasting with raw dark walnut burl", 0xFFC5A059)
    )

    val products: List<Product> = listOf(
        // Category 1: LUXURY BEDS
        Product(
            id = "fl_bed_01",
            name = "The Imperial Maharaja Royal Bed",
            tagline = "Hand-carved solid Teak master bed with high-back arched crown & gold leaf embellishments",
            category = ProductCategory.LUXURY_BEDS,
            priceTag = "Price on Request",
            primaryWood = "Grade-A Nilambur Teak (Sagwan)",
            secondaryWood = "Indian Sheesham (Dalbergia Sissoo)",
            woodSpecs = listOf(TEAK_SPEC, SHEESHAM_SPEC),
            description = "A breathtaking centerpiece fit for royalty. Handcrafted over 180 artisan hours by master wood sculptors, featuring deep floral relief cresting, sturdy interlocking joinery, and an imposing 72-inch carved headboard.",
            craftsmanshipDetails = listOf(
                "100% Solid Seasoned Hardwood without veneer or particle board",
                "Traditional Mortise & Tenon joinery engineered for lifetime zero-creak stability",
                "Optional Hydraulic or Box Storage with Teak ply interiors",
                "Custom upholstery options in Italian Velvet, Bouclé, or Genuine Full-Grain Leather"
            ),
            standardDimensions = listOf(
                DimensionOption("Grand King Size", "215 cm L x 205 cm W x 185 cm H", "85\" L x 81\" W x 73\" H", "Fits Master Suite 16x18 ft+"),
                DimensionOption("Imperial Queen Size", "215 cm L x 175 cm W x 180 cm H", "85\" L x 69\" W x 71\" H", "Fits Bedroom 14x15 ft+"),
                DimensionOption("Custom Bespoke Fit", "Tailored to your exact mattress size", "Custom Width x Length x Headboard Height", "Architectural custom specs")
            ),
            availableFinishes = STANDARD_FINISHES,
            isBestseller = true,
            warrantyYears = 25
        ),
        Product(
            id = "fl_bed_02",
            name = "The Florentine Canopy Four-Poster Bed",
            tagline = "Classical European silhouette in dark walnut timber with turned fluted pillars & brass ferrules",
            category = ProductCategory.LUXURY_BEDS,
            priceTag = "Price on Request",
            primaryWood = "Grade-A Nilambur Teak (Sagwan)",
            secondaryWood = "Kashmir Walnut Timber",
            woodSpecs = listOf(TEAK_SPEC, WALNUT_SPEC),
            description = "Elegance elevated to monumental height. Four towering hand-turned fluted solid wood columns support a delicate carved canopy cornice, framing your private sanctuary in pure classical luxury.",
            craftsmanshipDetails = listOf(
                "Solid turned posts cut from single-log hardwood trunks",
                "Concealed heavy-duty steel bed rail brackets supporting 800+ kg",
                "Customizable canopy rail for sheer linen or silk drapery",
                "Hand-buffed satin sheen finish resistant to humidity changes"
            ),
            standardDimensions = listOf(
                DimensionOption("Palatial King", "225 cm L x 210 cm W x 230 cm H", "88\" L x 83\" W x 90\" H", "Requires ceiling height 9.5 ft+"),
                DimensionOption("Luxury Queen", "225 cm L x 180 cm W x 225 cm H", "88\" L x 71\" W x 88\" H", "Requires ceiling height 9 ft+")
            ),
            availableFinishes = STANDARD_FINISHES,
            isNewArrival = true,
            warrantyYears = 20
        ),

        // Category 2: ROYAL DINING SETS
        Product(
            id = "fl_dining_01",
            name = "The Grand Versailles 8-Seater Banquet Set",
            tagline = "Opulent solid teak dining table with double lion-paw pedestal bases & 8 high-back carved chairs",
            category = ProductCategory.ROYAL_DINING,
            priceTag = "Price on Request",
            primaryWood = "Grade-A Nilambur Teak (Sagwan)",
            secondaryWood = "Sheesham Heartwood",
            woodSpecs = listOf(TEAK_SPEC, SHEESHAM_SPEC),
            description = "An heirloom banquet set designed to host distinguished gatherings. Features a 2.5-inch solid teak top with bookmatched grain, supported by two monumental hand-carved pedestal pedestals, paired with ergonomically cushioned royal chairs.",
            craftsmanshipDetails = listOf(
                "Monolithic 65mm thick tabletop edge with carved acanthus leaf borders",
                "Dual heavyweight hand-carved pedestal columns with antique gold leaf highlights",
                "Includes 2 Master Carver Host Armchairs + 6 Guest Side Chairs",
                "Protected with heat, alcohol & water-resistant polyurethane matte coat"
            ),
            standardDimensions = listOf(
                DimensionOption("8-Seater Grand Banquet", "245 cm L x 115 cm W x 78 cm H", "96\" L x 45\" W x 31\" H", "Ideal for 14x18 ft Dining Room"),
                DimensionOption("10-Seater Royal Estate", "305 cm L x 120 cm W x 78 cm H", "120\" L x 47\" W x 31\" H", "Ideal for 16x22 ft Dining Hall"),
                DimensionOption("6-Seater Compact Royal", "190 cm L x 105 cm W x 78 cm H", "75\" L x 41\" W x 31\" H", "Ideal for 12x14 ft Dining Space")
            ),
            availableFinishes = STANDARD_FINISHES,
            isBestseller = true,
            warrantyYears = 25
        ),
        Product(
            id = "fl_dining_02",
            name = "The Sovereign Oval Sheesham Banquet",
            tagline = "Sleek curved oval dining table celebrating rich natural Sheesham grain with fluted pillar base",
            category = ProductCategory.ROYAL_DINING,
            priceTag = "Price on Request",
            primaryWood = "North Indian Sheesham (Dalbergia Sissoo)",
            secondaryWood = "Nilambur Teak",
            woodSpecs = listOf(SHEESHAM_SPEC, TEAK_SPEC),
            description = "Where organic warmth meets royal dining. The seamless oval profile is engineered from curated Sheesham slabs displaying dramatic dark honey and espresso marbling, anchored by fluted architectural pillars.",
            craftsmanshipDetails = listOf(
                "Bookmatched wood grain layout hand-selected for symmetry",
                "Radius curved edge profiling smooth to the touch",
                "High-density stain-resistant upholstery on all chairs",
                "Certified kiln-dried to eliminate seasonal warping in dry or humid climates"
            ),
            standardDimensions = listOf(
                DimensionOption("8-Seater Oval", "240 cm L x 110 cm W x 76 cm H", "94\" L x 43\" W x 30\" H", "Spacious 8-chair seating"),
                DimensionOption("6-Seater Oval", "195 cm L x 100 cm W x 76 cm H", "77\" L x 39\" W x 30\" H", "Intimate 6-chair dining")
            ),
            availableFinishes = STANDARD_FINISHES,
            warrantyYears = 15
        ),

        // Category 3: CARVED SOFAS
        Product(
            id = "fl_sofa_01",
            name = "The Maharaja Sovereign 7-Seater Suite",
            tagline = "Royal living room ensemble (3+2+1+1) in deep walnut with antique gold leaf cresting & silk velvet",
            category = ProductCategory.CARVED_SOFAS,
            priceTag = "Price on Request",
            primaryWood = "Grade-A Nilambur Teak (Sagwan)",
            secondaryWood = "Sheesham Hardwood",
            woodSpecs = listOf(TEAK_SPEC, SHEESHAM_SPEC),
            description = "A grand statement in classical luxury. Each frame is painstakingly sculpted from seasoned teak logs by generation-old master carvers in Saharanpur, dressed in champagne upholstery with high-resilience memory foam seating.",
            craftsmanshipDetails = listOf(
                "Heavy 3-dimensional hand-carved floral crown on all backrests",
                "Deep tufted diamond buttoning on backrest for ergonomic lumbar comfort",
                "40-Density HR foam with pocket spring suspension for decades of shape retention",
                "Complete set includes 3-Seater Sofa, 2-Seater Loveseat, and 2 Royal Accent Armchairs"
            ),
            standardDimensions = listOf(
                DimensionOption("3-Seater Sovereign", "215 cm W x 95 cm D x 120 cm H", "85\" W x 37\" D x 47\" H", "Centerpiece 3-seater"),
                DimensionOption("2-Seater Loveseat", "165 cm W x 95 cm D x 120 cm H", "65\" W x 37\" D x 47\" H", "Secondary seating"),
                DimensionOption("1-Seater Maharaja Chair", "105 cm W x 95 cm D x 120 cm H", "41\" W x 37\" D x 47\" H", "Grand host armchairs")
            ),
            availableFinishes = STANDARD_FINISHES,
            isBestseller = true,
            warrantyYears = 20
        ),
        Product(
            id = "fl_sofa_02",
            name = "The Royal Victorian Chaise & Diwan",
            tagline = "Asymmetrical antique carved daybed with scroll arms and bolster cushions",
            category = ProductCategory.CARVED_SOFAS,
            priceTag = "Price on Request",
            primaryWood = "Grade-A Nilambur Teak (Sagwan)",
            secondaryWood = "Dark Walnut Timber",
            woodSpecs = listOf(TEAK_SPEC, WALNUT_SPEC),
            description = "Crafted for luxurious relaxation and parlor conversations. The graceful S-curved scroll arm and undulating carved crest rail create an irresistible aesthetic focal point.",
            craftsmanshipDetails = listOf(
                "Hand-carved cabriole legs ending in carved lion scroll feet",
                "Includes 2 matching cylindrical bolster pillows with gold tassel cord trim",
                "Breathable high-durability woven jacquard or velvet fabric",
                "Reinforced internal hardwood frame corner blocks"
            ),
            standardDimensions = listOf(
                DimensionOption("Grand Diwan", "210 cm L x 80 cm W x 98 cm H", "83\" L x 31\" W x 38\" H", "Comfortable adult daybed"),
                DimensionOption("Compact Chaise Lounge", "180 cm L x 75 cm W x 92 cm H", "71\" L x 29\" W x 36\" H", "Accent lounge fit")
            ),
            availableFinishes = STANDARD_FINISHES,
            warrantyYears = 15
        ),

        // Category 4: WOODEN JHULA / SWINGS
        Product(
            id = "fl_jhula_01",
            name = "The Rajwada Royal Teak Jhula",
            tagline = "Heirloom handcrafted indoor/veranda swing with ornate solid brass hanging chains & peacock motif",
            category = ProductCategory.WOODEN_JHULA,
            priceTag = "Price on Request",
            primaryWood = "Grade-A Nilambur Teak (Sagwan)",
            secondaryWood = "Sheesham Hardwood",
            woodSpecs = listOf(TEAK_SPEC, SHEESHAM_SPEC),
            description = "A cultural masterpiece for luxury Indian and global residences. Handcrafted with heavy teak wooden planks, deep relief carved peacock pillars, and 100% solid cast brass chains adorned with elephant and dancing peacock links.",
            craftsmanshipDetails = listOf(
                "Solid Cast Brass Hanging Chains tested to hold 1,200+ kg static load",
                "Includes heavy-duty brass ceiling ceiling anchor hooks and ball-bearing links",
                "Waterproof outdoor teak polyurethane option available for covered verandas & patios",
                "Plush reversible seat cushion with 4 matching decorative throw cushions"
            ),
            standardDimensions = listOf(
                DimensionOption("3-Seater Grand Swing", "190 cm W x 75 cm D x 65 cm H (Seat)", "75\" W x 29\" D x 26\" H", "Comfortably fits 3 adults"),
                DimensionOption("2-Seater Intimate Swing", "150 cm W x 70 cm D x 65 cm H (Seat)", "59\" W x 27\" D x 26\" H", "Fits 2 adults comfortably"),
                DimensionOption("Free-Standing Arch Frame", "225 cm W x 120 cm D x 215 cm H (Total)", "88\" W x 47\" D x 85\" H", "Complete stand-alone wooden arch")
            ),
            availableFinishes = STANDARD_FINISHES,
            isBestseller = true,
            warrantyYears = 25
        ),
        Product(
            id = "fl_jhula_02",
            name = "The Contemporary Zuri Suspended Daybed",
            tagline = "Minimalist luxury live-edge wooden swing suspended with braided brass hardware",
            category = ProductCategory.WOODEN_JHULA,
            priceTag = "Price on Request",
            primaryWood = "Grade-A Nilambur Teak (Sagwan)",
            secondaryWood = "American Dark Walnut",
            woodSpecs = listOf(TEAK_SPEC, WALNUT_SPEC),
            description = "A refined modern interpretation of the classic swing. Features a thick slab of live-edge seasoned teak, recessed warm LED underglow channels, and clean architectural brass rods.",
            craftsmanshipDetails = listOf(
                "Architectural minimalist profile suited for modern villas and penthouse balconies",
                "Integrated hidden structural steel subframe inside the wood core",
                "Marine-grade all-weather Sunbrella fabric options",
                "Ultra-quiet silent swivel bearings"
            ),
            standardDimensions = listOf(
                DimensionOption("Daybed Swing", "200 cm W x 90 cm D x 45 cm H", "79\" W x 35\" D x 18\" H", "Full lounge daybed swing"),
                DimensionOption("Standard 2-Seater", "160 cm W x 70 cm D x 45 cm H", "63\" W x 28\" D x 18\" H", "Balcony & living room fit")
            ),
            availableFinishes = STANDARD_FINISHES,
            isNewArrival = true,
            warrantyYears = 20
        ),

        // Category 5: EPOXY TABLES
        Product(
            id = "fl_epoxy_01",
            name = "The Azure River Live-Edge Epoxy Dining Table",
            tagline = "Century-old seasoned Burl Walnut & crystal translucent ocean resin with brass spider base",
            category = ProductCategory.EPOXY_TABLES,
            priceTag = "Price on Request",
            primaryWood = "Centenary Walnut Burl & Seasoned Teak Root",
            secondaryWood = "Raw Live-Edge Hardwood",
            woodSpecs = listOf(WALNUT_SPEC, TEAK_SPEC),
            description = "Nature's raw chaos preserved in liquid glass. Two mirrored raw live-edge slabs of rare burl walnut are permanently encapsulated with crystal-clear German epoxy resin with subtle ocean depth swirls.",
            craftsmanshipDetails = listOf(
                "Zero VOC, UV-resistant non-yellowing German resin formulation",
                "50mm solid thickness with hand-beveled rounded edges",
                "Heavy gauge gold-plated or matte black electrostatic steel/brass base",
                "Nano-ceramic scratch-resistant top sealant coating"
            ),
            standardDimensions = listOf(
                DimensionOption("8-Seater River Table", "240 cm L x 105 cm W x 76 cm H", "95\" L x 41\" W x 30\" H", "Statement dining center"),
                DimensionOption("10-Seater Grand Boardroom", "300 cm L x 115 cm W x 76 cm H", "118\" L x 45\" W x 30\" H", "Executive luxury hall"),
                DimensionOption("6-Seater Statement", "180 cm L x 95 cm W x 76 cm H", "71\" L x 37\" W x 30\" H", "Modern apartment centerpiece")
            ),
            availableFinishes = EPOXY_FINISHES,
            isBestseller = true,
            warrantyYears = 15
        ),
        Product(
            id = "fl_epoxy_02",
            name = "The Emerald Burl Live-Edge Center Coffee Table",
            tagline = "Organic cross-cut teak root slab with deep emerald resin pool and brushed brass hairpin legs",
            category = ProductCategory.EPOXY_TABLES,
            priceTag = "Price on Request",
            primaryWood = "Grade-A Nilambur Teak Root Slab",
            secondaryWood = "Kashmir Walnut",
            woodSpecs = listOf(TEAK_SPEC, WALNUT_SPEC),
            description = "A conversation starter for the connoisseur. The natural cavities, bark inclusions, and raw live curves of old-growth teak root are highlighted with translucent jade green resin.",
            craftsmanshipDetails = listOf(
                "Unique one-of-a-kind natural grain and root formation — no two pieces are identical",
                "Diamond buffed to 5000-grit ultra mirror finish",
                "Handcrafted custom steel base with leveling brass glides",
                "Resistant to hot coffee mugs and condensation rings"
            ),
            standardDimensions = listOf(
                DimensionOption("Grand Coffee Table", "135 cm L x 85 cm W x 45 cm H", "53\" L x 33\" W x 18\" H", "Spacious living room table"),
                DimensionOption("Round Organic Centerpiece", "100 cm Diameter x 45 cm H", "39\" Dia x 18\" H", "Circular live-edge cut")
            ),
            availableFinishes = EPOXY_FINISHES,
            isNewArrival = true,
            warrantyYears = 15
        )
    )

    fun getProductById(id: String): Product? = products.find { it.id == id }

    fun getProductsByCategory(category: ProductCategory): List<Product> {
        return if (category == ProductCategory.ALL) {
            products
        } else {
            products.filter { it.category == category }
        }
    }
}
