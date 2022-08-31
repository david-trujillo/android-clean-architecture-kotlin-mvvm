object DefaultConfig {
    const val appID = "app.example.mvvm.transporte"
    const val minSdk = 24
    const val targetSdk = 31
    const val compileSdk = 32
}

interface BuildType {

    companion object {
        const val RELEASE = "release"
        const val RELEASE_PREFIX = ""
        const val DEBUG = "debug"
        const val DEBUG_PREFIX = ".debug"
    }

    val isMinifyEnabled: Boolean
    val versionNameSuffix: String
    val applicationIdSuffix: String
    val isDebuggable: Boolean
}

object BuildTypeRelease : BuildType {
    const val type = "release"
    override val isMinifyEnabled: Boolean = false
    override val versionNameSuffix: String = ""
    override val applicationIdSuffix: String = BuildType.RELEASE_PREFIX
    override val isDebuggable: Boolean = false
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled: Boolean = false
    override val versionNameSuffix: String = " (debug)"
    override val applicationIdSuffix: String = BuildType.DEBUG_PREFIX
    override val isDebuggable: Boolean = true
}