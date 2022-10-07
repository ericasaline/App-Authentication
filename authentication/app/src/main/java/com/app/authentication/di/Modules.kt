package com.app.authentication.di

import androidx.room.Room
import com.app.authentication.database.UserDatabase
import com.app.authentication.database.repository.Repository
import com.app.authentication.database.repository.RepositoryImpl
import com.app.authentication.ui.login.LoginViewModel
import com.app.authentication.ui.signup.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ SignUpViewModel(get()) }
    viewModel{ LoginViewModel(get()) }
}

val dataModule = module {
    single<Repository> {
        RepositoryImpl(get())
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java,
            "database"
        ).build()
    }

    single {
        get<UserDatabase>().dao()
    }
}