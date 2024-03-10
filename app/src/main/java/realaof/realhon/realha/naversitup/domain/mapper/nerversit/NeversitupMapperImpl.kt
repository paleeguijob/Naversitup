package realaof.realhon.realha.naversitup.domain.mapper.nerversit

import realaof.realhon.realha.naversitup.model.department.DepartmentItem
import realaof.realhon.realha.naversitup.model.product.ProductItem
import realaof.realhon.realha.naversitup.ui.sceen.landing.uimodel.LandingUiState
import javax.inject.Inject

class NeversitupMapperImpl @Inject constructor() : NeversitupMapper {

    override fun mapToDepartments(departmentItems: List<DepartmentItem>): List<LandingUiState.LandingUi.DepartmentUi> =
        departmentItems.map {
            LandingUiState.LandingUi.DepartmentUi(
                name = it.name,
                imageUrl = it.imageUrl,
                departmentId = it.id
            )
        }

    override fun mapToProducts(productItems: List<ProductItem>): List<LandingUiState.LandingUi.ProductUiState.ProductUi> =
        productItems.map {
            LandingUiState.LandingUi.ProductUiState.ProductUi(
                imageUrl = it.imageUrl,
                name = it.name,
                description = it.desc,
                price = it.price
            )
        }
}