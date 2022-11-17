package by.coolightman.weather.di

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
//    viewModelOf(::MembersViewModel)
}

val repositoryModule = module {

//    singleOf(::MemberRepositoryImpl) { bind<MemberRepository>() }
}

val databaseModule = module {
//    single { MembersDatabase(get()) }
}

val retrofitModule = module {

}

val appModule = module {
    includes(
        viewModelModule,
        repositoryModule,
        databaseModule,
        retrofitModule
    )
}