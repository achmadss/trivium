package dev.achmad.trivium.ui.utils

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
inline fun <reified VM: ViewModel> activityViewModel(): VM {
    val viewModelStoreOwner = LocalActivity.current as ComponentActivity
    return viewModel(VM::class, viewModelStoreOwner)
}