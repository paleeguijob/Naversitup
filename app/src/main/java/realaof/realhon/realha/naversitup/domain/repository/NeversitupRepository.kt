package realaof.realhon.realha.naversitup.domain.repository

import kotlinx.coroutines.flow.Flow
import realaof.realhon.realha.naversitup.model.department.DepartmentItem
import realaof.realhon.realha.naversitup.model.network.BaseCommonError
import realaof.realhon.realha.naversitup.model.network.NetworkResponse
import realaof.realhon.realha.naversitup.model.product.ProductItem

interface NeversitupRepository {

    suspend fun getDepartments(): Flow<NetworkResponse<List<DepartmentItem>, BaseCommonError>>

    suspend fun getProducts(departmentId: String): Flow<NetworkResponse<List<ProductItem>, BaseCommonError>>
}