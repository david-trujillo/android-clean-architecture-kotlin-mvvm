package app.example.mvvm.transporte.features.tram.presentation

import android.content.Intent
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import app.example.mvvm.transporte.common.views.binding.viewBinding
import app.example.mvvm.transporte.common.views.presentation.BaseFragment
import app.example.mvvm.transporte.common.views.presentation.extension.observeOnStarted
import app.example.mvvm.transporte.features.tram.R
import app.example.mvvm.transporte.features.tram.databinding.FragmentTramBinding
import app.example.mvvm.transporte.features.tram.presentation.adapter.TramAdapter
import app.example.mvvm.transporte.navigation.contracts.TramListContract
import app.example.mvvm.transporte.navigation.core.id
import javax.inject.Inject

@AndroidEntryPoint
class TramFragment : BaseFragment(R.layout.fragment_tram) {

    override val viewModel: TramViewModel by viewModels()

    override val binding: FragmentTramBinding by viewBinding()

    @Inject
    lateinit var timeAdapter: TramAdapter

    override fun setupViews() {
        binding.rvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = timeAdapter
        binding.etTramStop.inputType = InputType.TYPE_NULL

        binding.etTramStop.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                navigate(TramListContract.route)
            }
            view.performClick()
        }

        binding.iError.btTryAgain.setOnClickListener {
            binding.etTramStop.tag?.toString()?.let {
                viewModel.loadTramInfo(it)
            }
        }

        binding.srlTram.apply {
            setOnRefreshListener {
                this.isRefreshing = false
                binding.etTramStop.tag?.toString()?.let {
                    viewModel.loadTramInfo(it)
                }
            }
        }
    }

    override fun setupObservers() = observeOnStarted(viewModel) { state ->
        binding.iLoading.root.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        binding.iError.root.visibility = if (state.isError) View.VISIBLE else View.GONE

        state.data?.let {
            binding.iEmpty.root.visibility = View.GONE
            binding.etTramStop.setText(it.title)
            timeAdapter.addAll(it.destinations)
            it.getDestiny()?.let {
                binding.tvTramDirection.text = it
            }
        } ?: run {
            binding.iEmpty.root.visibility = if (state.isLoading || state.isError) View.GONE else View.VISIBLE
            timeAdapter.clearAll()
        }

        state.stopID?.let {
            binding.etTramStop.tag = it
        }
    }

    override fun onResult(result: Intent) {
        when (result.id) {
            TramListContract.id -> {
                TramListContract.parseResult(result)?.let {
                    viewModel.loadTramInfo(it.id)
                }
            }
        }
    }

}
