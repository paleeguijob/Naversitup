package realaof.realhon.realha.naversitup.domain.mapper.nerversit

import realaof.realhon.realha.naversitup.model.department.DepartmentItem
import realaof.realhon.realha.naversitup.model.product.ProductItem
import realaof.realhon.realha.naversitup.ui.sceen.landing.uimodel.LandingUiState

interface NeversitupMapper {

    fun mapToDepartments(departmentItems: List<DepartmentItem>): List<LandingUiState.LandingUi.DepartmentUi>

    fun mapToProducts(productItems: List<ProductItem>): List<LandingUiState.LandingUi.ProductUiState.ProductUi>
}