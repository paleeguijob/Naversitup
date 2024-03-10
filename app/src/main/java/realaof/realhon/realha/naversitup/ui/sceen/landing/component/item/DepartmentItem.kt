package realaof.realhon.realha.naversitup.ui.sceen.landing.component.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import realaof.realhon.realha.naversitup.ui.sceen.landing.uimodel.LandingUiState
import realaof.realhon.realha.naversitup.ui.theme.dimen

@Composable
fun DepartmentItem(
    modifier: Modifier = Modifier,
    departmentUi: LandingUiState.LandingUi.DepartmentUi,
    onDepartmentItemSelected: (departmentUi: LandingUiState.LandingUi.DepartmentUi) -> Unit,
) {

    Card(
        border = if (departmentUi.selected) BorderStroke(
            width = dimen.dimen_2,
            color = Color.Gray
        ) else null,
        modifier = modifier
            .wrapContentSize()
            .clickable {
                onDepartmentItemSelected(departmentUi)
            }
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(departmentUi.imageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build()
        )

        Box(
            modifier = Modifier
                .size(dimen.dimen_120)
                .wrapContentSize()
        ) {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = "Department Top Image",
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = departmentUi.name,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(vertical = dimen.dimen_8, horizontal = dimen.dimen_16)
            )
        }
    }
}

@Preview
@Composable
private fun DepartmentItemPreview() {
    DepartmentItem(
        departmentUi = LandingUiState.LandingUi.DepartmentUi(
            name = "name"
        ),
        onDepartmentItemSelected = {}
    )
}