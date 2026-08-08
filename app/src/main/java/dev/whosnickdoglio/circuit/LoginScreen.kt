package dev.whosnickdoglio.circuit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.serialization.CircuitSerializable
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import dev.zacsweers.metro.Inject
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginScreen(
    val bounceBackScreen: Screen? = null,
) : Screen {
    data class State(val eventSink: (Event) -> Unit) : CircuitUiState
    sealed interface Event: CircuitUiEvent {
        data object NavigateBack: Event
    }
}

@CircuitInject(LoginScreen::class, AppScope::class)
@Composable
internal fun LoginScreen(state: LoginScreen.State, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login screen")
        Button(onClick = {
            state.eventSink(LoginScreen.Event.NavigateBack)
        }) {
            Text("Back")
        }
    }
}

@AssistedInject
internal class LoginPresenter(
    @Assisted private val navigator: Navigator
) : Presenter<LoginScreen.State> {

    @CircuitInject(LoginScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): LoginPresenter
    }


    @Composable
    override fun present(): LoginScreen.State = LoginScreen.State {
        if (it == LoginScreen.Event.NavigateBack) navigator.pop()
    }
}
