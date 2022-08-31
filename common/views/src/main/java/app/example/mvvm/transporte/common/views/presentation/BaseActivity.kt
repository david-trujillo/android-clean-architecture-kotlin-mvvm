package app.example.mvvm.transporte.common.views.presentation

interface BaseActivity {

    fun hideToolbar()

    fun showToolbar()

    fun setDisplayHomeAsUpEnabled(enable: Boolean)

    fun setTitle(title: String)

    fun showLoadingScreen()

    fun hideLoadingScreen()

}