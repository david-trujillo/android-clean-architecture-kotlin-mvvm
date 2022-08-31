package app.example.mvvm.transporte

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import app.example.mvvm.transporte.common.views.binding.viewBinding
import app.example.mvvm.transporte.common.views.presentation.BaseActivity
import app.example.mvvm.transporte.databinding.ActivityContainerBinding
import app.example.mvvm.transporte.navigation.core.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ContainerActivity : AppCompatActivity(), BaseActivity {

    val binding: ActivityContainerBinding by viewBinding()

    @Inject
    lateinit var navigationManager: NavigationManager

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)
        initNavManager()
        setupToolbar()
    }

    private fun getNavController(): NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        return navHostFragment.navController
    }

    private fun initNavManager() {
        val navController: NavController = getNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph)
        navigationManager.init(navController)
    }

    private fun setupToolbar() {
        binding.toolbar.setupWithNavController(getNavController(), appBarConfiguration)
        setSupportActionBar(binding.toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        return getNavController().navigateUp() || super.onSupportNavigateUp()
    }

    override fun hideToolbar() {
        supportActionBar?.hide()
    }

    override fun showToolbar() {
        supportActionBar?.show()
    }

    override fun setDisplayHomeAsUpEnabled(enable: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enable)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun showLoadingScreen() {
        binding.iLoading.root.visibility = View.VISIBLE
    }

    override fun hideLoadingScreen() {
        binding.iLoading.root.visibility = View.GONE
    }
}
