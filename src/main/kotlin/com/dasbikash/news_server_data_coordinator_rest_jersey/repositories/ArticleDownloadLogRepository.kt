package com.dasbikash.news_server_data_coordinator_rest_jersey.repositories

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DatabaseTableNames
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.ArticleDownloadLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ArticleDownloadLogRepository : JpaRepository<ArticleDownloadLog, Int>,DeletableLogRepository<ArticleDownloadLog>{
    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_DOWNLOAD_LOG_TABLE_NAME} order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getLatestArticleDownloadLogs(pageSize: Int): List<ArticleDownloadLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_DOWNLOAD_LOG_TABLE_NAME} WHERE id < :lastArticleDownloadLogId order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getArticleDownloadLogsBeforeGivenId(lastArticleDownloadLogId: Int, pageSize: Int): List<ArticleDownloadLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_DOWNLOAD_LOG_TABLE_NAME} order by id ASC limit :pageSize",
            nativeQuery = true)
    override fun getOldestLogs(pageSize: Int): List<ArticleDownloadLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_DOWNLOAD_LOG_TABLE_NAME} WHERE id >= :lastLogId order by id ASC limit :pageSize",
            nativeQuery = true)
    override fun getLogsAfterGivenId(lastLogId: Int, pageSize: Int): List<ArticleDownloadLog>
}