package dev.faststats.example

import dev.faststats.data.Metric

object KotlinMetricTypesExample {
    // Single value metrics
    val PLAYER_COUNT: Metric<Number> = Metric.number("player_count") { 42 }
    val SERVER_VERSION: Metric<String> = Metric.string("server_version") { "1.0.0" }
    val MAINTENANCE_MODE: Metric<Boolean> = Metric.bool("maintenance_mode") { false }

    // Array metrics
    val INSTALLED_PLUGINS: Metric<Array<String>> = Metric.stringArray("installed_plugins") {
        arrayOf("WorldEdit", "Essentials")
    }
    val WORLDS: Metric<Array<String>> = Metric.stringArray("worlds") {
        arrayOf("city", "farmworld", "farmworld_nether", "famrworld_end")
    }
}
