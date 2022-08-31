package app.example.mvvm.transporte.features.home.presentation

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import app.example.mvvm.transporte.common.views.binding.viewBinding
import app.example.mvvm.transporte.common.views.presentation.BaseFragment
import app.example.mvvm.transporte.features.home.R
import app.example.mvvm.transporte.features.home.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()

    override val binding: FragmentHomeBinding by viewBinding()

    override fun setupViews() {
        val navHomeFragment = childFragmentManager.findFragmentById(R.id.navHostHome) as NavHostFragment
        val homeNavController: NavController = navHomeFragment.navController
        setupBottomNavMenu(homeNavController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        binding.bottomNavView.setupWithNavController(navController)
    }

    override fun onResult(result: Intent) {

    }

}