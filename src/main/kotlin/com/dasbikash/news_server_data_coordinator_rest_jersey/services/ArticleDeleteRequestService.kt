package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.ArticleDeleteRequest
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.ArticleDeleteRequestRepository
import org.springframework.stereotype.Service

@Service
open class ArticleDeleteRequestService(open var articleDeleteRequestRepository: ArticleDeleteRequestRepository) {

    fun getAll(): List<ArticleDeleteRequest> {
        return articleDeleteRequestRepository.findAll()
    }
}
