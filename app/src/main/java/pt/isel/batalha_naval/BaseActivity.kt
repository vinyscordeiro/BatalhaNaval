package pt.isel.batalha_naval

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import pt.isel.batalha_naval.ui.components.ErrorComponent
import pt.isel.batalha_naval.viewmodel.ViewModelBase

abstract class BaseActivity<T> : ComponentActivity() where T : ViewModelBase {
    protected val dependencyContainer by lazy { application as DependencyContainer }
    protected val navigationService by lazy { dependencyContainer.navigationService }
    protected abstract val viewModel : T

    protected fun safeSetContent(content: @Composable () -> Unit) {
        setContent {
            content()
            ErrorComponent(state = viewModel.latestError)
        }
    }
}