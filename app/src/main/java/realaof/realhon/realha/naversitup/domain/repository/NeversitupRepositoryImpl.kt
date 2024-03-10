package realaof.realhon.realha.naversitup.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import realaof.realhon.realha.naversitup.model.department.DepartmentItem
import realaof.realhon.realha.naversitup.model.network.BaseCommonError
import realaof.realhon.realha.naversitup.model.network.NetworkResponse
import realaof.realhon.realha.naversitup.model.product.ProductItem
import realaof.realhon.realha.naversitup.service.neversit.NeversitService
import javax.inject.Inject

class NeversitupRepositoryImpl @Inject constructor(
    private val service: NeversitService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : NeversitupRepository {

    override suspend fun getDepartments(): Flow<NetworkResponse<List<DepartmentItem>, BaseCommonError>> =
        withContext(dispatcher) {
            flow {
                emit(service.getDepartments())
            }.catch { error ->
                emit(NetworkResponse.OtherError(error))
            }
        }

    override suspend fun getProducts(departmentId: String): Flow<NetworkResponse<List<ProductItem>, BaseCommonError>> =
        withContext(dispatcher) {
            flow {
                emit(service.getProducts(departmentId = departmentId))
            }.catch { error ->
                emit(NetworkResponse.OtherError(error))
            }
        }

}