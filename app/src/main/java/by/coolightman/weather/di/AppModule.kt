package by.coolightman.weather.di

import by.coolightman.weather.data.remote.service.ApiService
import by.coolightman.weather.util.API_URL_ROOT
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
//    viewModelOf(::MembersViewModel)
}

val repositoryModule = module {

//    singleOf(::MemberRepositoryImpl) { bind<MemberRepository>() }
}

val databaseModule = module {
//    single { MembersDatabase(get()) }
}

val apiModule = module {
    single{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL_ROOT)
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val appModule = module {
    includes(
        viewModelModule,
        repositoryModule,
        databaseModule,
        apiModule
    )
}