package realaof.realhon.realha.naversitup.ui.sceen.landing.uimodel

import realaof.realhon.realha.naversitup.model.enum.ProductType
import realaof.realhon.realha.naversitup.model.network.BaseCommonError

data class LandingUiState(
    val loading: Boolean? = null,
    val error: BaseCommonError? = null,
    val success: LandingUi? = null
) {
    data class LandingUi(
        val departments: List<DepartmentUi>? = listOf(),
        val departmentNameSelected: String = "",
        val productUiState: ProductUiState = ProductUiState()
    ) {
        data class DepartmentUi(
            val name: String = "",
            val imageUrl: String = "",
            val departmentId: String = "-1",
            val selected: Boolean = false,
        )

        data class ProductUiState(
            val loading: Boolean? = null,
            val error: BaseCommonError? = null,
            val success: List<ProductUi>? = listOf()
        ) {
            data class ProductUi(
                val imageUrl: String = "",
                val name: String = "",
                val description: String = "",
                val price: String = "",
                val type: ProductType = ProductType.NORMAL
            )
        }
    }
}