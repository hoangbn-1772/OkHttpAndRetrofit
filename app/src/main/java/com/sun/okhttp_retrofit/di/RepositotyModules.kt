package com.sun.okhttp_retrofit.di

import com.sun.okhttp_retrofit.data.remote.UserRemoteDataSource
import com.sun.okhttp_retrofit.data.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRemoteDataSource(get()) }
    single { UserRepository(get()) }
}
