package realaof.realhon.realha.naversitup.domain.usecase.department

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import realaof.realhon.realha.naversitup.base.extensions.BaseUnitTest
import realaof.realhon.realha.naversitup.domain.repository.NeversitupRepository
import realaof.realhon.realha.naversitup.model.department.DepartmentItem
import realaof.realhon.realha.naversitup.model.network.NetworkResponse

@OptIn(ExperimentalCoroutinesApi::class)
class GetDepartmentsUseCaseTest : BaseUnitTest() {

    private val repository: NeversitupRepository = mockk()

    private lateinit var getDepartmentsUseCase: GetDepartmentsUseCase

    @Before
    override fun setup() {
        super.setup()

        getDepartmentsUseCase = GetDepartmentsUseCase(
            repository = repository
        )
    }

    @Test
    fun `get departments use case success`() = runTest {
        //Given
        val departmentList = departments
        val mockResponse = flowOf(NetworkResponse.Success(departmentList))

        coEvery { repository.getDepartments() } returns mockResponse

        //When
        val response = getDepartmentsUseCase.execute(Unit)

        //Then
        assertEquals(departmentList, response.getOrNull())
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
}