package dev.whosnickdoglio.circuit

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.navstack.rememberSaveableNavStack
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.screen.CircuitSaver
import com.slack.circuit.runtime.screen.ProvideCircuitSaver
import dev.whosnickdoglio.circuit.ui.theme.CircuitSerializableScreenBugTheme
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.binding
import dev.zacsweers.metrox.android.ActivityKey

@ContributesIntoMap(AppScope::class, binding<Activity>())
@ActivityKey
class MainActivity(
    private val circuit: Circuit,
    private val circuitSaver: CircuitSaver,
) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircuitSerializableScreenBugTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CircuitCompositionLocals(circuit) {
                        ProvideCircuitSaver(circuitSaver) {
                            val navStack = rememberSaveableNavStack(initialScreens = listOf(
                                HomeScreen))
                            val navigator = rememberCircuitNavigator(navStack) { /* do something on root */ }
                            NavigableCircuitContent(
                                navigator = navigator,
                                navStack = navStack,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}
