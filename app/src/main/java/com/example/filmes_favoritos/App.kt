package com.example.filmes_favoritos

import android.app.Application
import com.example.filmes_favoritos.di.AppContainer
import com.example.filmes_favoritos.di.IAppContainer

class App : Application() {
    lateinit var container: IAppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}