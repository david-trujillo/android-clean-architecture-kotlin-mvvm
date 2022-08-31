package app.example.mvvm.transporte.common.views.presentation.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import app.example.mvvm.transporte.common.views.presentation.viewmodel.BaseViewModel
import app.example.mvvm.transporte.common.views.presentation.viewmodel.BaseViewState

/* https://developer.android.com/jetpack/guide/ui-layer/events */
/* https://github.com/android/architecture-samples/blob/9575ea50e6683f8574b18eea60ff856a6f7e2c1c/app/src/main/java/com/example/android/architecture/blueprints/todoapp/util/CollectAsStateWithLifecycle.kt */

/**
 * Collects values from this [Flow] and represents its latest value via [State] in a
 * lifecycle-aware manner.
 *
 * Every time there would be new value posted into the [Flow] the returned [State] will be updated
 * causing recomposition of every [State.value] usage whenever the [lifecycle] is at
 * least [minActiveState].
 *
 * This [Flow] is collected every time [lifecycle] reaches the [minActiveState] Lifecycle
 * state. The collection stops when [lifecycle] falls below [minActiveState].
 *
 * @sample androidx.lifecycle.runtime.compose.samples.FlowCollectAsStateWithLifecycle
 *
 * Warning: [Lifecycle.State.INITIALIZED] is not allowed in this API. Passing it as a
 * parameter will throw an [IllegalArgumentException].
 */
fun <A : BaseViewModel<B>, B : BaseViewState> LifecycleOwner.observeOnStarted(viewModel: A, onChange: FlowCollector<B>) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collect(onChange)
        }
    }
}