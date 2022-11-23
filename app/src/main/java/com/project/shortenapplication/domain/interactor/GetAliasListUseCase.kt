package com.project.shortenapplication.domain.interactor

import androidx.lifecycle.LiveData
import com.project.shortenapplication.domain.entity.AliasDomain
import com.project.shortenapplication.domain.repository.AliasRepositoryContract
import javax.inject.Inject

class GetAliasListUseCase @Inject constructor(private val aliasRepository: AliasRepositoryContract) {

    fun getAllLinks(): LiveData<List<AliasDomain>> {
        return aliasRepository.getAllAlias()
    }
}