package app.example.mvvm.transporte.navigation.contracts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import app.example.mvvm.transporte.navigation.core.NavigationResult

object TramListContract : NavigationResult<TramListContract.Result>() {

    val route = app.example.mvvm.transporte.navigation.AppGraphDirections.actionGlobalToTramListFragment()

    @Parcelize
    data class Result(
        val id: String,
    ) : Parcelable

}