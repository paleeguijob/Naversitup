package realaof.realhon.realha.naversitup.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import realaof.realhon.realha.naversitup.ui.sceen.landing.LandingScreen
import realaof.realhon.realha.naversitup.ui.theme.NaversitupTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeversitupApp()
        }
    }
}

@Composable
fun NeversitupApp(modifier: Modifier = Modifier) {
    NaversitupTheme {
        LandingScreen(modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun NeversitAppPreview() {
    NaversitupTheme {
        NeversitupApp()
    }
}