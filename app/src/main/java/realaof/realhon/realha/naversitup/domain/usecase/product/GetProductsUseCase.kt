package realaof.realhon.realha.naversitup.domain.usecase.product

import kotlinx.coroutines.flow.last
import realaof.realhon.realha.naversitup.domain.repository.NeversitupRepository
import realaof.realhon.realha.naversitup.model.network.toDataOrThrow
import realaof.realhon.realha.naversitup.model.product.ProductItem
import realaof.realhon.realha.naversitup.model.usecase.BaseSuspendUseCase
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: NeversitupRepository
) : BaseSuspendUseCase<GetProductsUseCase.Input, List<ProductItem>>() {

    override suspend fun create(input: Input): List<ProductItem> =
        repository.getProducts(departmentId = input.departmentId).last().toDataOrThrow()

    data class Input(val departmentId: String)
}