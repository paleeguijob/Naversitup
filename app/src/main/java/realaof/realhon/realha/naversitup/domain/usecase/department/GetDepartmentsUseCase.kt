package realaof.realhon.realha.naversitup.domain.usecase.department

import kotlinx.coroutines.flow.last
import realaof.realhon.realha.naversitup.domain.repository.NeversitupRepository
import realaof.realhon.realha.naversitup.model.department.DepartmentItem
import realaof.realhon.realha.naversitup.model.network.toDataOrThrow
import realaof.realhon.realha.naversitup.model.usecase.BaseSuspendUseCase
import javax.inject.Inject

class GetDepartmentsUseCase @Inject constructor(
    private val repository: NeversitupRepository
) : BaseSuspendUseCase<Unit, List<DepartmentItem>>() {

    override suspend fun create(input: Unit): List<DepartmentItem> =
        repository.getDepartments().last().toDataOrThrow()
}