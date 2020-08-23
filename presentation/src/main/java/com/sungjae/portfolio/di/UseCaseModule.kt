package com.sungjae.portfolio.di

import com.sungjae.portfolio.domain.usecase.*
import com.utinfra.sungjae.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    /*   Use Case Local    */
    factory { SharedPreferenceSaveUseCase(get()) }

    /*   Use Case Remote   */
    factory { CheckDeviceUseCase(get()) }
    factory { ConfirmOfHospitalizationUseCase(get()) }
    factory { ConfirmOfPatientDataUseCase(get()) }
    factory { GetPatientDataUseCase(get()) }
    factory { GetMainPageDataUseCase(get()) }
    factory { GetTodayPageDataUseCase(get()) }
    factory { GetTodayDetailPageDataUseCase(get()) }
    factory { GetTomorrowPageDataUseCase(get()) }
    factory { SendTomorrowDetailSelectedDataUseCase(get()) }
    factory { GetIntakePageDataUseCase(get()) }
    factory { GetIntakeDetailPageDataUseCase(get()) }
    factory { SendIntakeDetailSelectedData(get()) }

    /*   Use Case Dialog   */
    factory { TodayDetailDialog1UseCase(get()) }
    factory { TodayDetailDialog2UseCase(get()) }
    factory { TodayDetailDialog3UseCase(get()) }
    factory { TodayDetailDialog4UseCase(get()) }

}