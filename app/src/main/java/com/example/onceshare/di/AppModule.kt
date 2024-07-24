package com.example.onceshare.di

import com.example.onceshare.data.repository.ApplianceRepository
import com.example.onceshare.data.repository.AuthRepository
import com.example.onceshare.domain.usecases.AddApplianceUseCase
import com.example.onceshare.domain.usecases.GetAppliancesUseCase
import com.example.onceshare.domain.usecases.LoginUseCase
import com.example.onceshare.domain.usecases.SignUpUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository = AuthRepository(firebaseAuth)

    @Provides
    fun provideApplianceRepository(
        firestore: FirebaseFirestore
    ): ApplianceRepository = ApplianceRepository(firestore)

    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase = LoginUseCase(authRepository)

    @Provides
    fun provideSignUpUseCase(
        authRepository: AuthRepository
    ): SignUpUseCase = SignUpUseCase(authRepository)

    @Provides
    fun provideAddApplianceUseCase(
        applianceRepository: ApplianceRepository
    ): AddApplianceUseCase = AddApplianceUseCase(applianceRepository)

    @Provides
    fun provideGetAppliancesUseCase(
        applianceRepository: ApplianceRepository
    ): GetAppliancesUseCase = GetAppliancesUseCase(applianceRepository)
}

