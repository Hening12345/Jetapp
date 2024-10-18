package com.example.jetapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jetapp.data.repository.MainRepository
import com.example.jetapp.data.source.local.LocalDataSource
import com.example.jetapp.data.source.local.room.ProductsDao
import com.example.jetapp.data.source.local.room.ProductsDatabase
import com.example.jetapp.data.source.remote.RemoteDataSource
import com.example.jetapp.data.source.remote.network.ApiService
import com.example.jetapp.domain.repository.IMainRepository
import com.example.jetapp.domain.usecase.MainInteractor
import com.example.jetapp.domain.usecase.MainUseCase
import com.example.jetapp.ui.screen.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import java.util.concurrent.TimeUnit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    factory<MainUseCase> { MainInteractor(get()) }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

fun provideRoomDatabase(context: Context) : ProductsDatabase {
    return Room.databaseBuilder(
        context,
        ProductsDatabase::class.java,
        "Products.db"
    ).build()
}

fun provideProductsDao(productsDatabase: ProductsDatabase) : ProductsDao {
    return productsDatabase.productsDao()
}

val databaseModule = module {
    single { provideRoomDatabase(androidContext()) }
    single { provideProductsDao(get()) }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single<IMainRepository> { MainRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}


