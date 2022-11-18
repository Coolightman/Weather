package by.coolightman.weather.di

import androidx.room.Room
import by.coolightman.weather.data.local.AppDatabase
import by.coolightman.weather.data.remote.service.ApiService
import by.coolightman.weather.data.repository.WeatherRepositoryImpl
import by.coolightman.weather.domain.repository.WeatherRepository
import by.coolightman.weather.domain.usecase.FetchWeatherDataByCityUseCase
import by.coolightman.weather.domain.usecase.GetLasWeatherStampUseCase
import by.coolightman.weather.ui.screen.BaseViewModel
import by.coolightman.weather.util.API_URL_ROOT
import by.coolightman.weather.util.DB_NAME
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModelOf(::BaseViewModel)
}

val useCaseModule = module {
    single { FetchWeatherDataByCityUseCase(get()) }
    single { GetLasWeatherStampUseCase(get()) }
}

val repositoryModule = module {
    singleOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    single { get<AppDatabase>().weatherDao() }
}

val apiModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL_ROOT)
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }
}

val appModule = module {
    includes(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        databaseModule,
        apiModule
    )
}