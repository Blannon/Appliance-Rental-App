package com.example.onceshare.domain.usecases

import com.example.onceshare.data.model.Appliance
import com.example.onceshare.data.repository.ApplianceRepository
import javax.inject.Inject

class GetAppliancesUseCase @Inject constructor(
    private val applianceRepository: ApplianceRepository
) {
    suspend operator fun invoke(): Result<List<Appliance>> {
        return applianceRepository.getAppliances()
    }
}
