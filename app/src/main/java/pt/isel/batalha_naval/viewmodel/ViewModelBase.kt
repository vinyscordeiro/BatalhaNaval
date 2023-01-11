package pt.isel.batalha_naval.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pt.isel.batalha_naval.models.ErrorModel

open class ViewModelBase : ViewModel() {
    var latestError by mutableStateOf<ErrorModel?>(null)

    private var _errorId = 0

    protected fun safe(func: () -> Unit) {
        try {
            func()
        } catch (e: Exception){
            latestError = ErrorModel(e.toString(), _errorId++)
        }
    }

    protected suspend fun safeSuspend(func: suspend () -> Unit) {
        try {
            func()
        } catch (e: Exception){
            latestError = ErrorModel(e.toString(), _errorId++)
        }
    }

    protected suspend fun safeViewModelScopeLaunch(func: suspend () -> Unit) : Job {
        return viewModelScope.launch { 
            safeSuspend {
                try {
                    func()
                } catch (e: CancellationException){
                    Log.d(this.javaClass.name, "Cancelled exception $e")
                }
            }
        }
    }
}