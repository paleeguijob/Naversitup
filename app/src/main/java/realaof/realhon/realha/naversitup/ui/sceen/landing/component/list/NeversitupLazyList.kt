package realaof.realhon.realha.naversitup.ui.sceen.landing.component.list

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.woong.compose.grid.HorizontalGrid
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid
import realaof.realhon.realha.naversitup.R
import realaof.realhon.realha.naversitup.common.component.error.BaseErrorScreen
import realaof.realhon.realha.naversitup.common.component.loading.BaseLoading
import realaof.realhon.realha.naversitup.model.enum.ProductType
import realaof.realhon.realha.naversitup.ui.sceen.landing.component.item.DepartmentItem
import realaof.realhon.realha.naversitup.ui.sceen.landing.component.item.ProductItem
import realaof.realhon.realha.naversitup.ui.sceen.landing.component.item.ProductSpacialItem
import realaof.realhon.realha.naversitup.ui.sceen.landing.uimodel.LandingUiState
import realaof.realhon.realha.naversitup.ui.theme.dimen

@Composable
fun NeversitupLazyList(
    modifier: Modifier = Modifier,
    landingUi: LandingUiState.LandingUi,
    onDepartmentItemSelected: (departmentUi: LandingUiState.LandingUi.DepartmentUi) -> Unit,
    onProductClicked: (product: LandingUiState.LandingUi.ProductUiState.ProductUi) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimen.dimen_16),
        contentPadding = PaddingValues(dimen.dimen_16)
    ) {
        item {
            DepartmentList(
                departments = landingUi.departments ?: listOf(),
                onDepartmentItemSelected = onDepartmentItemSelected
            )
        }

        item {
            WrapProductListContent(
                departmentName = landingUi.departmentNameSelected,
                productUiState = landingUi.productUiState,
                onProductClicked = onProductClicked
            )
        }
    }
}

@Composable
fun DepartmentList(
    modifier: Modifier = Modifier,
    departments: List<LandingUiState.LandingUi.DepartmentUi>,
    onDepartmentItemSelected: (departmentUi: LandingUiState.LandingUi.DepartmentUi) -> Unit,
) {
    Column(modifier = modifier.wrapContentHeight()) {
        Text(
            text = stringResource(id = R.string.never_sit_up_landing_department_department_list_title),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = dimen.dimen_8)
        )

        HorizontalGrid(
            rows = SimpleGridCells.Fixed(1),
            modifier = Modifier
                .height(dimen.dimen_120)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(dimen.dimen_16),
            verticalArrangement = Arrangement.spacedBy(dimen.dimen_16)
        ) {

            departments.forEach { department ->
                DepartmentItem(
                    departmentUi = department,
                    onDepartmentItemSelected = onDepartmentItemSelected
                )
            }
        }
    }
}

@Composable
private fun WrapProductListContent(
    modifier: Modifier = Modifier,
    departmentName: String,
    productUiState: LandingUiState.LandingUi.ProductUiState,
    onProductClicked: (product: LandingUiState.LandingUi.ProductUiState.ProductUi) -> Unit
) {
    when {
        productUiState.loading != null -> BaseLoading(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = dimen.dimen_200)
        )

        productUiState.error != null -> BaseErrorScreen(
            title = stringResource(id = R.string.never_sit_up_landing_product_not_found_title),
            message = stringResource(id = R.string.never_sit_up_landing_product_not_found_message),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = dimen.dimen_200)
        )

        productUiState.success != null -> {
            if (productUiState.success.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.never_sit_up_landing_department_selected),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimen.dimen_200)
                )
            } else {
                ProductList(
                    departmentName = "${stringResource(id = R.string.never_sit_up_landing_product_product_list_title)} $departmentName",
                    products = productUiState.success,
                    onProductClicked = onProductClicked,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    departmentName: String,
    products: List<LandingUiState.LandingUi.ProductUiState.ProductUi>,
    onProductClicked: (product: LandingUiState.LandingUi.ProductUiState.ProductUi) -> Unit
) {
    Column {
        Text(
            text = departmentName,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = modifier.padding(vertical = dimen.dimen_8)
        )

        VerticalGrid(
            columns = SimpleGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(dimen.dimen_16),
            verticalArrangement = Arrangement.spacedBy(dimen.dimen_16),
        ) {
            products.forEach { product ->

                when (product.type) {
                    ProductType.NORMAL -> ProductItem(
                        productsUi = product,
                        onProductClicked = onProductClicked
                    )

                    ProductType.SPACIAL -> ProductSpacialItem(
                        productsUi = product,
                        onProductClicked = onProductClicked
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DepartmentListPreview() {
    DepartmentList(
        departments = listOf(
            LandingUiState.LandingUi.DepartmentUi(
                name = "name"
            ),
            LandingUiState.LandingUi.DepartmentUi(
                name = "name"
            ),
            LandingUiState.LandingUi.DepartmentUi(
                name = "name"
            ),
            LandingUiState.LandingUi.DepartmentUi(
                name = "name"
            ),
            LandingUiState.LandingUi.DepartmentUi(
                name = "name"
            ),
            LandingUiState.LandingUi.DepartmentUi(
                name = "name"
            ),
        ),
        onDepartmentItemSelected = {},
        modifier = Modifier.wrapContentHeight()
    )
}

@Preview
@Composable
private fun ProductListPreview() {
    ProductList(
        departmentName = "Department 1",
        products = listOf(
            LandingUiState.LandingUi.ProductUiState.ProductUi(
                name = "RealAOF love RealHON",
                description = "Mark".repeat(10),
                price = "10,000"
            ),
            LandingUiState.LandingUi.ProductUiState.ProductUi(
                name = "RealAOF love RealHON",
                description = "Mark".repeat(10),
                price = "10,000"
            ),
            LandingUiState.LandingUi.ProductUiState.ProductUi(
                name = "RealAOF love RealHON",
                description = "Mark".repeat(10),
                price = "10,000"
            ),
            LandingUiState.LandingUi.ProductUiState.ProductUi(
                name = "RealAOF love RealHON",
                description = "Mark".repeat(10),
                price = "10,000"
            ),
            LandingUiState.LandingUi.ProductUiState.ProductUi(
                name = "RealAOF love RealHON",
                description = "Mark".repeat(10),
                price = "10,000"
            ),
            LandingUiState.LandingUi.ProductUiState.ProductUi(
                name = "RealAOF love RealHON",
                description = "Mark".repeat(10),
                price = "10,000"
            ),
            LandingUiState.LandingUi.ProductUiState.ProductUi(
                name = "RealAOF love RealHON",
                description = "Mark".repeat(10),
                price = "10,000"
            ),
            LandingUiState.LandingUi.ProductUiState.ProductUi(
                name = "RealAOF love RealHON",
                description = "Mark".repeat(10),
                price = "10,000"
            )
        ),
        onProductClicked = {},
        modifier = Modifier
    )
}

@Preview
@Composable
private fun NeversitupLazyListPreview() {
    NeversitupLazyList(
        landingUi = LandingUiState.LandingUi(
            productUiState = LandingUiState.LandingUi.ProductUiState(
                success =
                listOf(
                    LandingUiState.LandingUi.ProductUiState.ProductUi(
                        name = "RealAOF love RealHON",
                        description = "Mark".repeat(10),
                        price = "10,000"
                    ),
                    LandingUiState.LandingUi.ProductUiState.ProductUi(
                        name = "RealAOF love RealHON",
                        description = "Mark".repeat(10),
                        price = "10,000"
                    ),
                    LandingUiState.LandingUi.ProductUiState.ProductUi(
                        name = "RealAOF love RealHON",
                        description = "Mark".repeat(10),
                        price = "10,000"
                    ),
                    LandingUiState.LandingUi.ProductUiState.ProductUi(
                        name = "RealAOF love RealHON",
                        description = "Mark".repeat(10),
                        price = "10,000"
                    ),
                    LandingUiState.LandingUi.ProductUiState.ProductUi(
                        name = "RealAOF love RealHON",
                        description = "Mark".repeat(10),
                        price = "10,000"
                    ),
                    LandingUiState.LandingUi.ProductUiState.ProductUi(
                        name = "RealAOF love RealHON",
                        description = "Mark".repeat(10),
                        price = "10,000"
                    ),
                    LandingUiState.LandingUi.ProductUiState.ProductUi(
                        name = "RealAOF love RealHON",
                        description = "Mark".repeat(10),
                        price = "10,000"
                    ),
                    LandingUiState.LandingUi.ProductUiState.ProductUi(
                        name = "RealAOF love RealHON",
                        description = "Mark".repeat(10),
                        price = "10,000"
                    )
                )
            ),
            departments = listOf(
                LandingUiState.LandingUi.DepartmentUi(
                    name = "name"
                ),
                LandingUiState.LandingUi.DepartmentUi(
                    name = "name"
                ),
                LandingUiState.LandingUi.DepartmentUi(
                    name = "name"
                ),
                LandingUiState.LandingUi.DepartmentUi(
                    name = "name"
                ),
                LandingUiState.LandingUi.DepartmentUi(
                    name = "name"
                ),
                LandingUiState.LandingUi.DepartmentUi(
                    name = "name"
                ),
            )
        ),
        onDepartmentItemSelected = {},
        onProductClicked = {},
        modifier = Modifier
    )
}