package com.mc7.myhiltinjectionapp.di

import android.content.Context
import com.mc7.myhiltinjectionapp.BuildConfig
import com.mc7.myhiltinjectionapp.DetailRepository
import com.mc7.myhiltinjectionapp.repository.DetailUserRepositoryImp
import com.mc7.myhiltinjectionapp.utils.MyApp
import com.mc7.myhiltinjectionapp.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApplication(@ApplicationContext context: Context): MyApp {
        return context as MyApp
    }

    @Provides
    fun getApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.githubAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun getDetailUser(apiService: ApiService): DetailRepository {
        return DetailUserRepositoryImp(apiService)
    }
}