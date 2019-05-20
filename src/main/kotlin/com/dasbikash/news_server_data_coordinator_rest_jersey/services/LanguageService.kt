package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Language
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.LanguageRepository
import org.springframework.stereotype.Service

@Service
open class LanguageService(open var languageRepository: LanguageRepository) {

    fun getAllLanguages(): List<Language> {
        return languageRepository.findAll()
    }
}
