package realaof.realhon.realha.naversitup.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import realaof.realhon.realha.naversitup.service.neversit.NeversitService
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideNeversitupService(retrofit: Retrofit): NeversitService =
        retrofit.create(NeversitService::class.java)
}