package com.example.demomovie.android.di

import com.example.demomovie.android.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


fun androidModule() = module {
    loadKoinModules(homeModule())
}

private fun homeModule() = module {
    viewModel { HomeViewModel(get()) }
}