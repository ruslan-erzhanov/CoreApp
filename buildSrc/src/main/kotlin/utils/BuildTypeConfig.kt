package utils

import com.android.build.api.dsl.BuildType
import org.gradle.api.NamedDomainObjectContainer

object BuildTypeConfig {
    const val release = "release"
    const val debug = "debug"
    const val releaseMinified = "releaseMinified"
    const val releasePlayMarket = "releasePlayMarket"
}

fun <TBuildType : BuildType> NamedDomainObjectContainer<TBuildType>.releaseMinified(action: TBuildType.() -> Unit) {
    createBuildType(BuildTypeConfig.releaseMinified, action)
}

fun <TBuildType : BuildType> NamedDomainObjectContainer<TBuildType>.releasePlayMarket(action: TBuildType.() -> Unit) {
    createBuildType(BuildTypeConfig.releasePlayMarket, action)
}

private fun <TBuildType : BuildType> NamedDomainObjectContainer<TBuildType>.createBuildType(
    name: String,
    action: TBuildType.() -> Unit,
) {
    maybeCreate(name).apply {
        action(this)
        isMinifyEnabled = true
        matchingFallbacks += listOf(BuildTypeConfig.release)
    }
}