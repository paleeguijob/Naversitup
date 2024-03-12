package realaof.realhon.realha.naversitup.ui.sceen.landing.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun ProductSpacialItem(
    modifier: Modifier = Modifier,
    productsUi: LandingUiState.LandingUi.ProductUiState.ProductUi,
    onProductClicked: (LandingUiState.LandingUi.ProductUiState.ProductUi) -> Unit = {}
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray,
            contentColor = Color.DarkGray
        ),
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

        Box(modifier = Modifier) {
            Column(
                modifier = Modifier
            ) {

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
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
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
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
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
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
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


            //Spacial Text
            Text(
                text = productsUi.type.name,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(top = dimen.dimen_8, end = dimen.dimen_16)
                    .align(alignment = Alignment.TopEnd)
                    .clearAndSetSemantics {
                        contentDescription = "Never sit up Product name: ${productsUi.name}"
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductSpacialItemPreview() {
    ProductSpacialItem(
        productsUi = LandingUiState.LandingUi.ProductUiState.ProductUi(
            name = "RealAOF love RealHON",
            description = "Mark".repeat(100),
            price = "10,000"
        ),
        onProductClicked = {}
    )
}