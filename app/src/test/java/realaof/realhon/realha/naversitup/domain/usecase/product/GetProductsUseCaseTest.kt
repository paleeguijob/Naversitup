package realaof.realhon.realha.naversitup.domain.usecase.product

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import realaof.realhon.realha.naversitup.base.extensions.BaseUnitTest
import realaof.realhon.realha.naversitup.domain.repository.NeversitupRepository
import realaof.realhon.realha.naversitup.model.network.NetworkResponse
import realaof.realhon.realha.naversitup.model.product.ProductItem

@OptIn(ExperimentalCoroutinesApi::class)
class GetProductsUseCaseTest : BaseUnitTest() {

    private val repository: NeversitupRepository = mockk()

    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    override fun setup() {
        super.setup()

        getProductsUseCase = GetProductsUseCase(
            repository = repository
        )
    }

    @Test
    fun `get products use case success`() = runTest {
        //Given
        val departmentId = "1"
        val expected = products
        val mockResponse = flowOf(NetworkResponse.Success(expected))

        coEvery { repository.getProducts(departmentId) } returns mockResponse

        //When
        val response = getProductsUseCase.execute(GetProductsUseCase.Input(departmentId))

        //Then
        assertEquals(expected, response.getOrNull())
    }

    private val products = listOf(
        ProductItem(
            departmentId = "1",
            id = "1",
            imageUrl = "imageUrl1",
            name = "Product1",
            desc = "Description1",
            price = "100.00",
            type = "type1"
        ),
        ProductItem(
            departmentId = "2",
            id = "1",
            imageUrl = "imageUrl2",
            name = "Product2",
            desc = "Description2",
            price = "200.00",
            type = "type2"
        ),
        ProductItem(
            departmentId = "3",
            id = "3",
            imageUrl = "imageUrl3",
            name = "Product3",
            desc = "Description3",
            price = "300.00",
            type = "type3"
        ),
        ProductItem(
            departmentId = "4",
            id = "4",
            imageUrl = "imageUrl4",
            name = "Product4",
            desc = "Description4",
            price = "400.00",
            type = "type4"
        )
    )
}