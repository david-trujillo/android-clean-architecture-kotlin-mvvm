package app.example.mvvm.transporte.features.tramlist.presentation

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import app.example.mvvm.transporte.common.views.binding.viewBinding
import app.example.mvvm.transporte.common.views.presentation.BaseFragment
import app.example.mvvm.transporte.common.views.presentation.extension.observeOnStarted
import app.example.mvvm.transporte.features.tramlist.R
import app.example.mvvm.transporte.features.tramlist.databinding.TramListFragmentBinding
import app.example.mvvm.transporte.features.tramlist.presentation.adapter.TramListAdapter
import app.example.mvvm.transporte.navigation.contracts.TramListContract
import javax.inject.Inject

@AndroidEntryPoint
internal class TramListFragment : BaseFragment(R.layout.tram_list_fragment) {

    override val viewModel: TramListViewModel by viewModels()

    override val binding: TramListFragmentBinding by viewBinding()

    @Inject
    lateinit var adapter: TramListAdapter

    override fun setupViews() {
        showToolbar()
        setDisplayHomeAsUpEnabled(true)
        setTitle(getString(R.string.tram_list_title))
        binding.rvTramList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTramList.adapter = adapter
        adapter.onItemClicked = this::onTramSelected

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        viewModel.loadData()
    }

    private fun onTramSelected(id: String) {
        val intent = TramListContract.setResult(TramListContract.Result(id))
        finishWithResult(intent)
    }

    override fun setupObservers() = observeOnStarted(viewModel) { state ->

        binding.iLoading.root.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        binding.iError.root.visibility = if (state.isError) View.VISIBLE else View.GONE

        state.tramList?.let {
            if (adapter.itemCount == 0) {
                adapter.add(it)
            }
        }
    }
}