package com.pdm.fipr.labo03.routes

import androidx.navigation3.runtime.NavKey
import com.pdm.fipr.labo03.model.Student
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {

    @Serializable
    data object Home: Routes()
    @Serializable
    data class Students(val studentsList: List<Student>): Routes()

    @Serializable
    data object Sensor: Routes()

}