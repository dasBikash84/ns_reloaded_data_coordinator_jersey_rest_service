package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Newspaper
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.NewspaperRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class NewsPaperService @Autowired
constructor(open var newspaperRepository:NewspaperRepository){

    fun getAllActiveNewsPapers():List<Newspaper>{
        return newspaperRepository.findAllByActive()
    }

    fun getAllNewsPapers():List<Newspaper>{
        return newspaperRepository.findAll()
    }

    /*fun getNewPaperById(id:String):Newspaper?{
        val optionalNewspaper  = newspaperRepository.findById(id)
        if (optionalNewspaper.isPresent){
            return optionalNewspaper.get()
        }
        return null
    }*/

    fun getAllNewPaperByCountryName(countryName:String):List<Newspaper>{

        val returnNewspaperList = mutableListOf<Newspaper>()

        newspaperRepository
                .findAllByActive()
                .filter { it.getCountry()?.name == countryName }
                .toCollection(returnNewspaperList)
        return returnNewspaperList
    }

    fun getAllNewPaperByLanguageId(languageId: String): List<Newspaper> {

        val returnNewspaperList = mutableListOf<Newspaper>()

        newspaperRepository
                .findAllByActive()
                .filter { it.getLanguage()?.id == languageId }
                .toCollection(returnNewspaperList)
        return returnNewspaperList

    }
}