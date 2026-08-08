package dev.whosnickdoglio.circuit.di;

import com.slack.circuit.foundation.Circuit;
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.CircuitSaver;
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.serialization.CircuitSerializerRegistration;
import com.slack.circuit.serialization.SerializableCircuitSaver

import dev.zacsweers.metro.AppScope;
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.DependencyGraph;
import dev.zacsweers.metro.Multibinds
import dev.zacsweers.metro.Provides;
import dev.zacsweers.metrox.android.MetroAppComponentProviders

@DependencyGraph(AppScope::class)
interface AppDependencyGraph: MetroAppComponentProviders

@ContributesTo(scope = AppScope::class)
interface CircuitProviders {

    @Multibinds
    fun presenterFactories(): Set<Presenter.Factory>

    @Multibinds
    fun viewFactories(): Set<Ui.Factory>


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
