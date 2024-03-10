package realaof.realhon.realha.naversitup.ui.sceen.landing

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import realaof.realhon.realha.naversitup.R
import realaof.realhon.realha.naversitup.common.component.dialogue.CommonDialog
import realaof.realhon.realha.naversitup.common.component.error.BaseErrorScreen
import realaof.realhon.realha.naversitup.common.component.loading.BaseLoading
import realaof.realhon.realha.naversitup.model.common.dialogue.CommonDialogUi
import realaof.realhon.realha.naversitup.ui.sceen.landing.component.list.NeversitupLazyList
import realaof.realhon.realha.naversitup.ui.sceen.landing.uimodel.LandingUiState

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    viewModel: LandingViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchDepartments()
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val landingUiState by viewModel.landingUiState.collectAsStateWithLifecycle()
    val uiState = landingUiState

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var dialogDetail by rememberSaveable {
        mutableStateOf(
            CommonDialogUi(
                title = context.getString(R.string.never_sit_up_common_dialog_title),
                description = context.getString(R.string.never_sit_up_common_dialog_description)
            )
        )
    }

    when {
        uiState.loading != null -> BaseLoading(modifier = Modifier.fillMaxSize())

        uiState.error != null -> BaseErrorScreen(
            title = stringResource(id = R.string.never_sit_up_landing_department_not_found_title),
            message = stringResource(id = R.string.never_sit_up_landing_department_not_found_message),
            modifier = Modifier.fillMaxSize(),
        )

        uiState.success != null -> LandingContent(
            modifier = modifier,
            landingUi = uiState.success,
            onDepartmentItemSelected = { department ->
                with(viewModel) {
                    updateDepartmentSelected(department)
                    showProductLoading(department)
                    fetchProducts(department)
                }
            },
            onProductClicked = { productUi ->
                dialogDetail = CommonDialogUi(
                    title = context.getString(R.string.never_sit_up_common_dialog_title),
                    description = productUi.description
                )
                showDialog = true
            }
        )
    }

    if (showDialog) {
        CommonDialog(
            dialogUi = dialogDetail,
            onCloseClicked = {
                scope.launch {
                    showDialog = false
                }
            }
        )
    }
}

@Composable
private fun LandingContent(
    modifier: Modifier = Modifier,
    landingUi: LandingUiState.LandingUi,
    onDepartmentItemSelected: (departmentUi: LandingUiState.LandingUi.DepartmentUi) -> Unit,
    onProductClicked: (product: LandingUiState.LandingUi.ProductUiState.ProductUi) -> Unit
) {
    NeversitupLazyList(
        modifier = modifier,
        landingUi = landingUi,
        onDepartmentItemSelected = onDepartmentItemSelected,
        onProductClicked = onProductClicked
    )
}

@Preview(showBackground = true)
@Composable
private fun LandingScreenPreview() {
    LandingContent(
        modifier = Modifier,
        landingUi = LandingUiState.LandingUi(),
        onDepartmentItemSelected = {},
        onProductClicked = {}
    )
}