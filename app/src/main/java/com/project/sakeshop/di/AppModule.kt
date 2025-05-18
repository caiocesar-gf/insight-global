package com.project.sakeshop.di // Make sure this matches your application's package

import android.content.Context
import com.project.sakeshop.data.local.datasource.SakeShopDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideSakeShopDataSource(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): SakeShopDataSource {
        return SakeShopDataSource(context, moshi)
    }
}