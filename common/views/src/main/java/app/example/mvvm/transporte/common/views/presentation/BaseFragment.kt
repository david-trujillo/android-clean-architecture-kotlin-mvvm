package app.example.mvvm.transporte.common.views.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.viewbinding.ViewBinding
import app.example.mvvm.transporte.common.views.presentation.viewmodel.BaseViewModel
import app.example.mvvm.transporte.libs.analytics.Analytics
import app.example.mvvm.transporte.libs.analytics.event.ScreenViewEvent
import app.example.mvvm.transporte.navigation.core.NavigationManager
import javax.inject.Inject


abstract class BaseFragment(@LayoutRes layoutID: Int) : Fragment(layoutID) {

    val TAG: String = this::class.simpleName.toString()

    protected abstract val viewModel: BaseViewModel<*>
    protected abstract val binding: ViewBinding

    @Inject
    lateinit var navigationManager: NavigationManager

    @Inject
    lateinit var analytic: Analytics

    var requestPermission: ActivityResultLauncher<Array<String>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytic.logView(ScreenViewEvent(this::class.simpleName.toString()))
        setupObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission = registerForRequestPermissionResult()
        hideLoadingScreen()
        registerForResult()
        hideToolbar()
        setupViews()
        setupListeners()
    }

    abstract fun setupViews()

    /* View observers */

    open fun setupObservers() {

    }

    /* View listener  */

    open fun setupListeners() {

    }

    /* Logger function  */

    open fun logger(msg: String) {
        Log.d(TAG, msg)
    }

    /* On activity result */

    open fun onResult(result: Intent) {

    }

    /* On request permission result */

    open fun onRequestPermissionResult(isGranted: Boolean) {

    }

    /* Toolbar setup */

    fun hideToolbar() {
        (requireActivity() as BaseActivity).hideToolbar()
    }

    fun showToolbar() {
        (requireActivity() as BaseActivity).showToolbar()
    }

    fun setDisplayHomeAsUpEnabled(enable: Boolean) {
        (requireActivity() as BaseActivity).setDisplayHomeAsUpEnabled(enable)
    }

    fun setTitle(title: String) {
        (requireActivity() as BaseActivity).setTitle(title)
    }

    fun showLoadingScreen() {
        (requireActivity() as BaseActivity).showLoadingScreen()
    }

    fun hideLoadingScreen() {
        (requireActivity() as BaseActivity).hideLoadingScreen()
    }

    /* Permission request launcher */

    private fun registerForRequestPermissionResult() = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        val granted = it.entries.all {
            it.value
        }
        onRequestPermissionResult(granted)
    }

    /* Navigate functions */

    private fun registerForResult(navController: NavController? = navigationManager.appNavController) {
        navigationManager.observeNavigateReturnValue(viewLifecycleOwner, {
            navigationManager.removeNavigateReturnedValue()
            onResult(it)
        }, navController)
    }

    fun finishWithResult(intent: Intent, navController: NavController? = navigationManager.appNavController) {
        navigationManager.setNavigateResult(intent, navController)
        navigationManager.navigateUp(navController)
    }

    fun finish(navController: NavController? = navigationManager.appNavController) {
        navigationManager.navigateUp(navController)
    }

    fun navigate(directions: NavDirections, navController: NavController? = navigationManager.appNavController, navOptions: NavOptions? = null) {
        navigationManager.navigate(directions, navController, navOptions)
    }

    fun navigate(@IdRes id: Int, navController: NavController? = navigationManager.appNavController) {
        navigationManager.navigate(id, navController)
    }

}