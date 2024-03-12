package realaof.realhon.realha.naversitup.ui.sceen.landing.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import realaof.realhon.realha.naversitup.ui.sceen.landing.uimodel.LandingUiState
import realaof.realhon.realha.naversitup.ui.theme.dimen

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    productsUi: LandingUiState.LandingUi.ProductUiState.ProductUi,
    onProductClicked: (product: LandingUiState.LandingUi.ProductUiState.ProductUi) -> Unit
) {

    Card(
        modifier = modifier
            .clickable {
                onProductClicked(productsUi)
            }
    ) {

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(productsUi.imageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build()
        )

        Column(modifier = Modifier) {

            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = "Never sit up Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = dimen.dimen_149)
            )

            Text(
                text = productsUi.name,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(dimen.dimen_8)
                    .clearAndSetSemantics {
                        contentDescription = "Never sit up Product name: ${productsUi.name}"
                    }
            )

            Text(
                text = productsUi.description,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                overflow = TextOverflow.Ellipsis,
                minLines = 3,
                maxLines = 3,
                modifier = Modifier
                    .padding(dimen.dimen_8)
                    .clearAndSetSemantics {
                        contentDescription =
                            "Never sit up Product description: ${productsUi.description}"
                    }
            )

            Text(
                text = productsUi.price,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimen.dimen_8)
                    .padding(top = dimen.dimen_8, bottom = dimen.dimen_16)
                    .clearAndSetSemantics {
                        contentDescription = "Never sit up Product price: ${productsUi.price}"
                    }
            )
        }
    }
}

@Preview
@Composable
private fun ProductItemPreview() {
    ProductItem(
        productsUi = LandingUiState.LandingUi.ProductUiState.ProductUi(
            name = "RealAOF love RealHON",
            description = "Mark".repeat(100),
            price = "10,000"
        ),
        onProductClicked = {}
    )
}