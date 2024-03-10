package realaof.realhon.realha.naversitup.domain.repository

import app.cash.turbine.turbineScope
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import realaof.realhon.realha.naversitup.base.extensions.BaseUnitTest
import realaof.realhon.realha.naversitup.model.department.DepartmentItem
import realaof.realhon.realha.naversitup.model.network.NetworkResponse
import realaof.realhon.realha.naversitup.model.product.ProductItem
import realaof.realhon.realha.naversitup.service.neversit.NeversitService
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class NeversitupRepositoryTest : BaseUnitTest() {

    private val service: NeversitService = mockk()

    private lateinit var repository: NeversitupRepository

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    @Before
    override fun setup() {
        super.setup()

        repository = NeversitupRepositoryImpl(
            service = service,
            dispatcher = dispatcher
        )
    }

    @Test
    fun `get departments`() = runTest {
        //Given
        val departmentList = departments
        val mockResponse = NetworkResponse.Success(departmentList)

        //When
        coEvery { service.getDepartments() } returns mockResponse

        //Then
        turbineScope {
            val response = repository.getDepartments().testIn(backgroundScope)
            assertEquals(mockResponse, response.awaitItem())
            response.awaitComplete()
        }
    }

    @Test
    fun `get departments exception emit flow`() = runTest {
        //Given
        val mockResponse = IOException()

        //When
        //Don't mock any response from service,It's for test emit Flow catch exception

        //Then
        turbineScope {
            repository.getDepartments().testIn(backgroundScope)
        }
    }

    @Test
    fun `get products`() = runTest {
        //Given
        val departmentId = "1"
        val productList = products
        val mockResponse = NetworkResponse.Success(productList)

        //When
        coEvery { service.getProducts(departmentId) } returns mockResponse

        //Then
        turbineScope {
            val response = repository.getProducts(departmentId).testIn(backgroundScope)
            assertEquals(mockResponse, response.awaitItem())
            response.awaitComplete()
        }
    }

    @Test
    fun `get products exception emit flow`() = runTest {
        //Given
        val departmentId = "1"
        val mockResponse = IOException()

        //When
        //Don't mock any response from service,It's for test emit Flow catch exception

        //Then
        turbineScope {
            repository.getProducts(departmentId).testIn(backgroundScope)
        }
    }

    private val departments = listOf(
        DepartmentItem(
            id = "1",
            imageUrl = "imageUrl",
            name = "Department1"
        ),
        DepartmentItem(
            id = "2",
            imageUrl = "imageUrl",
            name = "Department2"
        ),
        DepartmentItem(
            id = "3",
            imageUrl = "imageUrl",
            name = "Department3"
        ),
        DepartmentItem(
            id = "4",
            imageUrl = "imageUrl",
            name = "Department4"
        )
    )

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