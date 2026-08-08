package dev.whosnickdoglio.circuit

import android.app.Application
import dev.whosnickdoglio.circuit.di.AppDependencyGraph
import dev.zacsweers.metro.createGraph
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import dev.zacsweers.metrox.android.MetroApplication

class App: Application(), MetroApplication {
    override val appComponentProviders: MetroAppComponentProviders by lazy {
        createGraph<AppDependencyGraph>()
    }
}
