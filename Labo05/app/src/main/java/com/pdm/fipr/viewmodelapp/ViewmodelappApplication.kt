package com.pdm.fipr.viewmodelapp

import com.pdm.fipr.viewmodelapp.data.AppProvider

import android.app.Application

class ViewmodelappApplication : Application() {
    val appProvider by lazy { AppProvider(this) }
}