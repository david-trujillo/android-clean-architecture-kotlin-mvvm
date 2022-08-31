package app.example.mvvm.transporte.navigation.core

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections

class NavigationDirection(@get:IdRes override val actionId: Int, override val arguments: Bundle = Bundle()) : NavDirections