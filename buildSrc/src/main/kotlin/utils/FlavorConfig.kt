package utils

import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.NamedDomainObjectContainer

object FlavorConfig {
    const val dimension = "environment"

    const val stage = "stage"
    const val prod = "prod"
}

fun <TFlavor : ProductFlavor> NamedDomainObjectContainer<TFlavor>.stage(
    action: TFlavor.() -> Unit,
) {
    createFlavor(FlavorConfig.stage, action)
}

fun <TFlavor : ProductFlavor> NamedDomainObjectContainer<TFlavor>.prod(
    action: TFlavor.() -> Unit,
) {
    createFlavor(FlavorConfig.prod, action)
}

private fun <TFlavor : ProductFlavor> NamedDomainObjectContainer<TFlavor>.createFlavor(
    name: String,
    action: TFlavor.() -> Unit,
) {
    maybeCreate(name).apply {
        dimension = FlavorConfig.dimension
        action(this)
    }
}
