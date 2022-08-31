package app.example.mvvm.transporte.navigation.core

import android.content.Intent
import android.os.Parcelable

abstract class NavigationResult<Result : Parcelable> {

    val id = this::class.simpleName.toString()

    companion object {
        const val INTENT_ARGS = "contract.intent.args"
        const val INTENT_ID = "contract.intent.id"
    }

    fun parseResult(intent: Intent?): Result? {
        return intent?.let { it.getParcelableExtra(INTENT_ARGS) as Result? }
    }

    fun setResult(result: Result): Intent {
        val intent = Intent()
        intent.putExtra(INTENT_ARGS, result)
        intent.putExtra(INTENT_ID, id)
        return intent
    }

}

val Intent.id: String?
    get() = this.getStringExtra(NavigationResult.INTENT_ID)