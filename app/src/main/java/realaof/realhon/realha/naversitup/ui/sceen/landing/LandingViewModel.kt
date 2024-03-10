package realaof.realhon.realha.naversitup.ui.sceen.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import realaof.realhon.realha.naversitup.domain.mapper.nerversit.NeversitupMapper
import realaof.realhon.realha.naversitup.domain.usecase.department.GetDepartmentsUseCase
import realaof.realhon.realha.naversitup.domain.usecase.product.GetProductsUseCase
import realaof.realhon.realha.naversitup.model.network.toBaseCommonError
import realaof.realhon.realha.naversitup.ui.sceen.landing.uimodel.LandingUiState
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val getDepartmentUseCase: GetDepartmentsUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val neversitupMapper: NeversitupMapper
) : ViewModel() {

    private val _landingUiState = MutableStateFlow(LandingUiState(loading = true))
    val landingUiState: StateFlow<LandingUiState> get() = _landingUiState.asStateFlow()

    fun fetchDepartments() = viewModelScope.launch {
        getDepartmentUseCase.execute(Unit)
            .onSuccess { response ->
                _landingUiState.value = LandingUiState(
                    success = LandingUiState.LandingUi(
                        departments = neversitupMapper.mapToDepartments(response)
                    )
                )
            }
            .onFailure { error ->
                _landingUiState.value = LandingUiState(
                    error = error.toBaseCommonError()
                )
            }
    }

    fun fetchProducts(department: LandingUiState.LandingUi.DepartmentUi) = viewModelScope.launch {
        getProductsUseCase.execute(GetProductsUseCase.Input(departmentId = department.departmentId))
            .onSuccess { response ->
                updateLandingUiStateProduct(
                    departmentName = department.name,
                    productUiState = LandingUiState.LandingUi.ProductUiState(
                        success = neversitupMapper.mapToProducts(response)
                    )
                )
            }
            .onFailure { error ->
                updateLandingUiStateProduct(
                    departmentName = department.name,
                    productUiState = LandingUiState.LandingUi.ProductUiState(
                        error = error.toBaseCommonError()
                    )
                )
            }
    }

    fun updateDepartmentSelected(departmentSelected: LandingUiState.LandingUi.DepartmentUi) =
        viewModelScope.launch {
            val updateDepartments = _landingUiState.value.success?.departments?.map {
                if (departmentSelected.departmentId == it.departmentId) {
                    it.copy(selected = true)
                } else {
                    it.copy(selected = false)
                }
            }

            _landingUiState.update {
                val newUpdate = it.copy(
                    success = it.success?.copy(
                        departments = updateDepartments
                    )
                )

                newUpdate
            }
        }

    fun showProductLoading(department: LandingUiState.LandingUi.DepartmentUi) =
        viewModelScope.launch {
            // Need to update landing every set landing state because i need to update state in ProductState
            updateLandingUiStateProduct(
                departmentName = department.name,
                productUiState = LandingUiState.LandingUi.ProductUiState(loading = true)
            )

            //Delay Show loading
            delay(500)
        }

    // Need to update landing every set landing state because i need to update state in ProductState
    private fun updateLandingUiStateProduct(
        departmentName: String?,
        productUiState: LandingUiState.LandingUi.ProductUiState
    ) {
        _landingUiState.update {
            val newProducts = it.copy(
                success = it.success?.copy(
                    departmentNameSelected = departmentName.orEmpty(),
                    productUiState = productUiState
                )
            )

            newProducts
        }
    }
}