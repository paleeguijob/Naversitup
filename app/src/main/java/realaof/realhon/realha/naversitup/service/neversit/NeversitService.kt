package realaof.realhon.realha.naversitup.service.neversit

import realaof.realhon.realha.naversitup.model.department.DepartmentItem
import realaof.realhon.realha.naversitup.model.network.BaseCommonError
import realaof.realhon.realha.naversitup.model.network.NetworkResponse
import realaof.realhon.realha.naversitup.model.product.ProductItem
import retrofit2.http.GET
import retrofit2.http.Path

interface NeversitService {

    @GET("api/v1/departments")
    suspend fun getDepartments(): NetworkResponse<List<DepartmentItem>, BaseCommonError>

    @GET("api/v1/departments/{departmentId}/products")
    suspend fun getProducts(
        @Path("departmentId") departmentId: String
    ): NetworkResponse<List<ProductItem>, BaseCommonError>

}