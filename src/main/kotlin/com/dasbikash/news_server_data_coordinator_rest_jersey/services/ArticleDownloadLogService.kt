package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions.DataNotFoundException
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.ArticleDownloadLog
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.ArticleDownloadLogRepository
import org.springframework.stereotype.Service

@Service
open class ArticleDownloadLogService constructor(open var articleDownloadLogRepository: ArticleDownloadLogRepository)
    :DeletableLogService<ArticleDownloadLog>{
    fun getLatestArticleDownloadLogs(pageSize: Int): List<ArticleDownloadLog> {
        return articleDownloadLogRepository.getLatestArticleDownloadLogs(pageSize)
    }

    fun getArticleDownloadLogsBeforeGivenId(lastArticleDownloadLogId: Int, pageSize: Int): List<ArticleDownloadLog> {
        val lastArticleDownloadLog = articleDownloadLogRepository.findById(lastArticleDownloadLogId)
        if (!lastArticleDownloadLog.isPresent){
            throw DataNotFoundException()
        }
        return articleDownloadLogRepository.getArticleDownloadLogsBeforeGivenId(lastArticleDownloadLog.get().id!!,pageSize)
    }

    override fun getOldestLogs(pageSize: Int): List<ArticleDownloadLog> {
        return articleDownloadLogRepository.getOldestLogs(pageSize)
    }

    override fun getLogsAfterGivenId(lastLogId: Int, pageSize: Int): List<ArticleDownloadLog> {
        return articleDownloadLogRepository.getLogsAfterGivenId(lastLogId,pageSize)
    }

    override fun delete(logEntry: ArticleDownloadLog) {
        articleDownloadLogRepository.delete(logEntry)
    }
}