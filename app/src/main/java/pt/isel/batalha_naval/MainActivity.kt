package pt.isel.batalha_naval

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.*
import pt.isel.batalha_naval.domain.PlayerInfo
import pt.isel.batalha_naval.domain.UserInfo
import pt.isel.batalha_naval.ui.screens.IntroScreen
import pt.isel.batalha_naval.ui.theme.BatalhaNavalTheme

class MainActivity : ComponentActivity() {

    val app by lazy {
        (application as DependencyContainer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            BatalhaNavalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //IntroScreen()

                    runBlocking {
                            //
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BatalhaNavalTheme {
        Greeting("Android")
    }
}