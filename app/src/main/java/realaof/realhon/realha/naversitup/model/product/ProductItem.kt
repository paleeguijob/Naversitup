package realaof.realhon.realha.naversitup.model.product

data class Product(
    val products: List<ProductItem>
)

data class ProductItem(
    val departmentId: String,
    val desc: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: String,
    val type: String
)