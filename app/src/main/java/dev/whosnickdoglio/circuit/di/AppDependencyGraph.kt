package dev.whosnickdoglio.circuit.di;

import androidx.savedstate.serialization.SavedStateConfiguration
import com.slack.circuit.foundation.Circuit;
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.CircuitSaveable
import com.slack.circuit.runtime.screen.CircuitSaver;
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.serialization.CircuitSerializerRegistration;
import com.slack.circuit.serialization.SerializableCircuitSaver
import dev.whosnickdoglio.circuit.HomeScreen
import dev.whosnickdoglio.circuit.LoginScreen

import dev.zacsweers.metro.AppScope;
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.DependencyGraph;
import dev.zacsweers.metro.Multibinds
import dev.zacsweers.metro.Provides;
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@DependencyGraph(AppScope::class)
interface AppDependencyGraph: MetroAppComponentProviders

@ContributesTo(scope = AppScope::class)
interface CircuitProviders {

    @Multibinds
    fun presenterFactories(): Set<Presenter.Factory>

    @Multibinds
    fun viewFactories(): Set<Ui.Factory>

    @Provides
    fun provideCircuitSaver(): CircuitSaver = SerializableCircuitSaver(
        SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(CircuitSaveable::class) {
                    subclass(HomeScreen::class)
                    subclass(LoginScreen::class)
                }

                polymorphic(Screen::class) {
                    subclass(HomeScreen::class)
                    subclass(LoginScreen::class)
                }
            }
        }
    )


    @Provides
    public fun provideCircuit(
            uiFactories: Set<Ui.Factory>,
            presenterFactories: Set<Presenter.Factory>,
            ): Circuit =
            Circuit.Builder()
            .addUiFactories(uiFactories)
            .addPresenterFactories(presenterFactories)
            .build()
}
