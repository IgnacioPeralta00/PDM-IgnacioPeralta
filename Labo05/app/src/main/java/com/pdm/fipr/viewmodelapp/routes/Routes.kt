package com.pdm.fipr.viewmodelapp.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {
    @Serializable
    data object Tasks: Routes()
}