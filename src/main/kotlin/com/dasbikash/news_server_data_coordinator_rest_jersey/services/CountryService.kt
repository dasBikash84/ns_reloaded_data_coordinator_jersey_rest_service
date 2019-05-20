package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Country
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.CountryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class CountryService @Autowired
constructor(open var countryRepository: CountryRepository){

    fun getAllCountries():List<Country>{
        return countryRepository.findAll()
    }
}