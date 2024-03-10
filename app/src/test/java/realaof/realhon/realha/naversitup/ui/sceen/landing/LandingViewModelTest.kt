package realaof.realhon.realha.naversitup.ui.sceen.landing

import app.cash.turbine.turbineScope
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import realaof.realhon.realha.naversitup.base.extensions.BaseUnitTest
import realaof.realhon.realha.naversitup.domain.mapper.nerversit.NeversitupMapperImpl
import realaof.realhon.realha.naversitup.domain.usecase.department.GetDepartmentsUseCase
import realaof.realhon.realha.naversitup.domain.usecase.product.GetProductsUseCase
import realaof.realhon.realha.naversitup.model.department.DepartmentItem
import realaof.realhon.realha.naversitup.model.network.toBaseCommonError
import realaof.realhon.realha.naversitup.model.product.ProductItem
import realaof.realhon.realha.naversitup.ui.sceen.landing.uimodel.LandingUiState

@OptIn(ExperimentalCoroutinesApi::class)
class LandingViewModelTest : BaseUnitTest() {

    private val getDepartmentsUseCase: GetDepartmentsUseCase = mockk()
    private val getProductsUseCase: GetProductsUseCase = mockk()
    private val mapper: NeversitupMapperImpl = NeversitupMapperImpl()

    private lateinit var viewModel: LandingViewModel

    @Before
    override fun setup() {
        super.setup()

        viewModel = LandingViewModel(
            getDepartmentUseCase = getDepartmentsUseCase,
            getProductsUseCase = getProductsUseCase,
            neversitupMapper = mapper
        )
    }

    @Test
    fun `get departments success`() = runTest {
        //Given
        val departmentList = departments
        val mockResponse = Result.success(departmentList)
        val expected = LandingUiState(
            success = LandingUiState.LandingUi(
                departments = mapper.mapToDepartments(departmentList)
            )
        )

        coEvery { getDepartmentsUseCase.execute(Unit) } returns mockResponse

        //When
        viewModel.fetchDepartments()

        //Then
        turbineScope {
            val response = viewModel.landingUiState.testIn(backgroundScope).awaitItem()

            assertEquals(expected.loading, response.loading)
            assertEquals(expected.error, response.error)
            assertEquals(expected.success, response.success)
        }
    }

    @Test
    fun `get departments failure`() = runTest {
        //Given
        val error = IOException()
        val mockResponse = Result.failure<List<DepartmentItem>>(error)
        val expected = LandingUiState(
            error = error.toBaseCommonError()
        )

        coEvery { getDepartmentsUseCase.execute(Unit) } returns mockResponse

        //When
        viewModel.fetchDepartments()

        //Then
        turbineScope {
            val response = viewModel.landingUiState.testIn(backgroundScope).awaitItem()

            assertEquals(expected.loading, response.loading)
            assertEquals(expected.error, response.error)
            assertEquals(expected.success, response.success)
        }
    }

    @Test
    fun `get products success`() = runTest {
        //Given
        val department = LandingUiState.LandingUi.DepartmentUi(
            departmentId = "1",
            imageUrl = "imageUrl",
            name = "Department1"
        )
        val departmentList = departments
        val mockResponse = Result.success(departmentList)

        val productList = products
        val mockProductResponse = Result.success(productList)

        val expected = LandingUiState(
            success = LandingUiState.LandingUi(
                departments = mapper.mapToDepartments(departmentList),
                departmentNameSelected = department.name,
                productUiState = LandingUiState.LandingUi.ProductUiState(
                    success = mapper.mapToProducts(productList)
                )
            )
        )

        coEvery { getDepartmentsUseCase.execute(Unit) } returns mockResponse
        coEvery {
            getProductsUseCase.execute(GetProductsUseCase.Input(department.departmentId))
        } returns mockProductResponse

        //When
        viewModel.fetchDepartments()
        viewModel.fetchProducts(department)

        //Then
        turbineScope {
            val response = viewModel.landingUiState.testIn(backgroundScope).awaitItem()

            assertEquals(expected.loading, response.loading)
            assertEquals(expected.error, response.error)
            assertEquals(expected.success, response.success)
        }
    }

    @Test
    fun `get products failure`() = runTest {
        //Given
        val department = LandingUiState.LandingUi.DepartmentUi(
            departmentId = "1",
            imageUrl = "imageUrl",
            name = "Department1"
        )

        val departmentList = departments
        val mockResponse = Result.success(departmentList)

        val error = IOException()
        val mockProductResponse = Result.failure<List<ProductItem>>(error)
        val expected = LandingUiState(
            success = LandingUiState.LandingUi(
                departments = mapper.mapToDepartments(departmentList),
                departmentNameSelected = department.name,
                productUiState = LandingUiState.LandingUi.ProductUiState(
                    error = error.toBaseCommonError()
                )
            )
        )

        coEvery { getDepartmentsUseCase.execute(Unit) } returns mockResponse
        coEvery { getProductsUseCase.execute(GetProductsUseCase.Input(department.departmentId)) } returns mockProductResponse

        //When
        viewModel.fetchDepartments()
        viewModel.fetchProducts(department)

        //Then
        turbineScope {
            val response = viewModel.landingUiState.testIn(backgroundScope).awaitItem()

            assertEquals(expected.loading, response.loading)
            assertEquals(expected.error, response.error)
            assertEquals(expected.success, response.success)
        }
    }

    @Test
    fun `update department selected`() = runTest {
        //Given
        val department = LandingUiState.LandingUi.DepartmentUi(
            departmentId = "1",
            imageUrl = "imageUrl",
            name = "Department1"
        )
        val departmentList = departments
        val mockResponse = Result.success(departmentList)
        val expected = LandingUiState(
            success = LandingUiState.LandingUi(
                departments = departmentsExpected
            )
        )

        coEvery { getDepartmentsUseCase.execute(Unit) } returns mockResponse

        //When

        viewModel.fetchDepartments()
        viewModel.updateDepartmentSelected(department)

        //Then
        turbineScope {
            val response = viewModel.landingUiState.testIn(backgroundScope).awaitItem()

            assertEquals(expected.loading, response.loading)
            assertEquals(expected.error, response.error)
            assertEquals(expected.success, response.success)
        }
    }

    @Test
    fun `show product loading`() = runTest {
        //Given
        val department = LandingUiState.LandingUi.DepartmentUi(
            departmentId = "1",
            imageUrl = "imageUrl",
            name = "Department1"
        )
        val departmentList = departments
        val mockResponse = Result.success(departmentList)
        val expected = LandingUiState(
            success = LandingUiState.LandingUi(
                departments = mapper.mapToDepartments(departmentList),
                departmentNameSelected = department.name,
                productUiState = LandingUiState.LandingUi.ProductUiState(loading = true)
            )
        )

        coEvery { getDepartmentsUseCase.execute(Unit) } returns mockResponse

        //When
        viewModel.fetchDepartments()
        viewModel.showProductLoading(department)

        //Then
        turbineScope {
            val response = viewModel.landingUiState.testIn(backgroundScope).awaitItem()

            assertEquals(expected.loading, response.loading)
            assertEquals(expected.error, response.error)
            assertEquals(expected.success, response.success)
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

    private val departmentsExpected = listOf(
        LandingUiState.LandingUi.DepartmentUi(
            departmentId = "1",
            imageUrl = "imageUrl",
            name = "Department1",
            selected = true
        ),
        LandingUiState.LandingUi.DepartmentUi(
            departmentId = "2",
            imageUrl = "imageUrl",
            name = "Department2"
        ),
        LandingUiState.LandingUi.DepartmentUi(
            departmentId = "3",
            imageUrl = "imageUrl",
            name = "Department3"
        ),
        LandingUiState.LandingUi.DepartmentUi(
            departmentId = "4",
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