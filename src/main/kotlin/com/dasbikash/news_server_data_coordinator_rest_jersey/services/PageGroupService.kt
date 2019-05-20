package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions.DataNotFoundException
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.PageGroups
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.PageGroup
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.PageGroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class PageGroupService @Autowired
constructor(open var pageGroupRepository: PageGroupRepository) {

    fun getPageGroups(): PageGroups{

        val pageGroupList = pageGroupRepository.findAll()

        if (pageGroupList.size==0){
            throw DataNotFoundException()
        }

        val pageGroupMap = mutableMapOf<String, PageGroup>()
        pageGroupList.asSequence().forEach {
            pageGroupMap.put(it.name!!,it)
        }
        return PageGroups(pageGroupMap)
    }
}