package realaof.realhon.realha.naversitup.common.component.dialogue

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import realaof.realhon.realha.naversitup.model.common.dialogue.CommonDialogUi
import realaof.realhon.realha.naversitup.ui.theme.dimen

@Composable
fun CommonDialog(
    modifier: Modifier = Modifier,
    dialogUi: CommonDialogUi,
    onCloseClicked: () -> Unit = {}
) {
    Dialog(onDismissRequest = onCloseClicked) {
        Card(modifier = modifier
                .heightIn(min = dimen.dimen_300, max = dimen.dimen_300)
        ) {
            Column(modifier = Modifier
                .heightIn(min = dimen.dimen_300)
                .background(color = Color.White)

            ) {
                Text(
                    text = dialogUi.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimen.dimen_32, bottom = dimen.dimen_16)
                        .padding(horizontal = dimen.dimen_16)
                )

                Text(
                    text = dialogUi.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(horizontal = dimen.dimen_16)
                        .padding(bottom = dimen.dimen_16)
                )

                Spacer(modifier = Modifier.weight(1f))

                HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color.Black)

                Text(
                    text = "Close",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimen.dimen_24)
                        .clickable {
                            onCloseClicked()
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommonDialogPreview() {
    CommonDialog(
        dialogUi = CommonDialogUi(
            title = "Product Description",
            description = "Descriptions".repeat(20)
        ),
        onCloseClicked = {}
    )
}