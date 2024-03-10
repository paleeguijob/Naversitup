package realaof.realhon.realha.naversitup.model.common.dialogue

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommonDialogUi(
    val title: String,
    val description: String,
) : Parcelable