package app.example.mvvm.transporte.navigation.core

import android.content.Intent
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {

    companion object {
        const val INTENT_RESULT = "contract.intent.result"
    }

    @get: Nullable
    var appNavController: NavController? = null

    fun init(navController: NavController?) {
        appNavController = navController
    }

    fun navigateUp(navController: NavController? = appNavController) {
        navController?.navigateUp()
    }

    fun navigate(@IdRes id: Int, navController: NavController? = appNavController) {
        navController?.navigate(id)
    }

    fun navigate(direction: NavDirections, navController: NavController? = appNavController, navOptions: NavOptions? = null) {
        navController?.navigate(direction, navOptions)
    }

    fun observeNavigateReturnValue(lifecycleOwner: LifecycleOwner, onResult: (result: Intent) -> Unit, navController: NavController? = appNavController) {
        val currentBackStackEntry = navController?.currentBackStackEntry
        currentBackStackEntry?.savedStateHandle?.getLiveData<Intent>(Companion.INTENT_RESULT)?.observe(lifecycleOwner) {
            onResult(it)
        }
    }

    fun getNavigateReturnedValue(navController: NavController? = appNavController): Intent? {
        val currentBackStackEntry = navController?.currentBackStackEntry
        return currentBackStackEntry?.savedStateHandle?.get<Intent>(Companion.INTENT_RESULT)
    }

    fun removeNavigateReturnedValue(navController: NavController? = appNavController): Intent? {
        val currentBackStackEntry = navController?.currentBackStackEntry
        return currentBackStackEntry?.savedStateHandle?.remove<Intent>(Companion.INTENT_RESULT)
    }

    fun prepareNavigateReturnedValue(result: Intent?, navController: NavController? = appNavController) {
        val currentBackStackEntry = navController?.currentBackStackEntry
        currentBackStackEntry?.savedStateHandle?.set(Companion.INTENT_RESULT, result)
    }

    fun doesNavigateReturnedValueExist(varName: String, navController: NavController? = appNavController): Boolean =
        navController?.currentBackStackEntry?.savedStateHandle?.contains(varName) ?: false

    fun setNavigateResult(result: Intent?, navController: NavController? = appNavController) {
        val previousBackStackEntry = navController?.previousBackStackEntry
        previousBackStackEntry?.savedStateHandle?.set(Companion.INTENT_RESULT, result)
    }


}