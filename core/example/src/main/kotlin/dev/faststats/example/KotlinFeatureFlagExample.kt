package dev.faststats.example

import dev.faststats.*
import java.time.Duration
import java.util.function.Consumer

object KotlinFeatureFlagExample {
    val CONTEXT: FastStatsContext = contextFactory
        // .featureFlagService(FeatureFlagService.Factory::create) // Define a feature flag service with default settings
        .featureFlagService { factory: FeatureFlagService.Factory? ->
            factory!!
                .attributes(
                    Attributes.empty() // Define global attributes
                        .put("version", "1.2.3")
                        .put("java_version", System.getProperty("java.version"))
                        .put("java_vendor", System.getProperty("java.vendor"))
                )
                .ttl(Duration.ofMinutes(10)) // Custom cache TTL for resolved flag values
                .create()
        }.create()

    val SERVICE: FeatureFlagService = CONTEXT.featureFlagService().orElseThrow()

    // Define flags with default values
    val NEW_COMMANDS: FeatureFlag<Boolean> = SERVICE.define("new_commands", false)
    val COMPRESSION: FeatureFlag<String> = SERVICE.define("compression", "zstd")

    fun usage() {
        // Async: waits for the server value to be fetched
        NEW_COMMANDS.whenReady().thenAccept(Consumer { enabled: Boolean? ->
            if (enabled == true) {
                // register new commands
            }
        })

        // Non-blocking: returns the cached value if present without triggering a fetch
        COMPRESSION.getCached().ifPresent(Consumer { compression: String? ->
            when (compression) {
                "zstd" -> {}
                "lz4" -> {}
                else -> {}
            }
        })

        // Refresh stale values explicitly when your code decides it is needed
        if (COMPRESSION.isExpired()) {
            COMPRESSION.fetch().thenAccept(Consumer { })
        }

        // Opt-in/out (requires allow_specific_opt_in on server)
        NEW_COMMANDS.optIn().thenAccept(Consumer { updatedValue: Boolean? ->
            if (updatedValue == true) {
                // react to the updated server value
            }
        })
        NEW_COMMANDS.optOut().thenAccept(Consumer { updatedValue: Boolean? ->
            if (!updatedValue!!) {
                // react to the updated server value
            }
        })
    }

    private val contextFactory: SimpleContext.Factory<*, *>
        get() = throw UnsupportedOperationException()
}
