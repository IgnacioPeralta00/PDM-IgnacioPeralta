package com.pdm.fipr.labo03

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.labo03.model.Student
import com.pdm.fipr.labo03.routes.Routes
import com.pdm.fipr.labo03.screens.GyroscopeSensor
import com.pdm.fipr.labo03.screens.HomeScreen
import com.pdm.fipr.labo03.screens.StudentsScreen

@Composable
fun AppNavigator(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(Routes.Home)
    // Estado elevado para facilitar la edición de la lista
    val studentsList = rememberSaveable { mutableStateListOf<Student>() }


    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {
                HomeScreen(
                    modifier = modifier,
                    onStudentClick = { backStack.add(Routes.Students(studentsList)) },
                    onSensorClick = { backStack.add(Routes.Sensor) }
                )
            }
            entry<Routes.Students> { students ->
                StudentsScreen(
                    modifier = modifier,
                    students = students.studentsList,
                    onStudentAdd = { user -> studentsList.add(Student(user)) },
                    onClear = { studentsList.clear() },
                    onBackHome = { backStack.removeLastOrNull() }
                )
            }
            entry<Routes.Sensor> {
                GyroscopeSensor(
                    modifier = modifier,
                    onBackHome = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}