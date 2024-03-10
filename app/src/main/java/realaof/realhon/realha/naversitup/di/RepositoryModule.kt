package realaof.realhon.realha.naversitup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import realaof.realhon.realha.naversitup.domain.mapper.nerversit.NeversitupMapper
import realaof.realhon.realha.naversitup.domain.mapper.nerversit.NeversitupMapperImpl
import realaof.realhon.realha.naversitup.domain.repository.NeversitupRepository
import realaof.realhon.realha.naversitup.domain.repository.NeversitupRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideNeversitupRepository(repositoryImpl: NeversitupRepositoryImpl): NeversitupRepository

    @Binds
    abstract fun provideNeversitupMapper(mapperImpl: NeversitupMapperImpl): NeversitupMapper
}