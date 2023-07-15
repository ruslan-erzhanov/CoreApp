object Modules {

    const val app = ":app"

    const val profile = "$app:profile"
    const val settings = "$app:settings"
    const val navigator = "$app:navigator"

    private const val core = ":core"
    const val network = "$core:network"
    const val coreUtil = "$core:util"

    const val components = ":components"

    private const val data = ":data"
    const val dataModels = "$data:models"
    const val dataCommon = "$data:common"
    const val dataUser = "$data:user"
    const val dataSettings = "$data:settings"
    const val dataAuth = "$data:auth"
}