package com.kotlinblog.dontgetfat.di

import com.kotlinblog.dontgetfat.data.DgfRepository
import com.kotlinblog.dontgetfat.view.calories.CaloriesViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * TODO: Add a class header comment!
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class
))
interface AppComponent {
    fun inject(viewModel: CaloriesViewModel)
    fun inject(repository: DgfRepository)
}