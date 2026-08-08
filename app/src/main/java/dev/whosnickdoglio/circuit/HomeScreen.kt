package dev.whosnickdoglio.circuit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
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
import kotlinx.parcelize.Parcelize

@Parcelize
@CircuitSerializable(AppScope::class)
data object HomeScreen : Screen {
    data class State(
        val onEvent: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event: CircuitUiEvent {
        data object Login: Event
    }
}

@CircuitInject(HomeScreen::class, AppScope::class)
@Composable
internal fun HomeScreen(state: HomeScreen.State, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home screen")
        Button(onClick = {state.onEvent(HomeScreen.Event.Login)}) {
            Text("Login")
        }
    }
}

@AssistedInject
internal class HomePresenter(
    @Assisted private val navigator: Navigator
) : Presenter<HomeScreen.State> {

    @CircuitInject(HomeScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): HomePresenter
    }

    @Composable
    override fun present(): HomeScreen.State {
        return HomeScreen.State {
            if (it == HomeScreen.Event.Login) {
                navigator.goTo(LoginScreen(bounceBackScreen = HomeScreen))
            }
        }
    }
}
