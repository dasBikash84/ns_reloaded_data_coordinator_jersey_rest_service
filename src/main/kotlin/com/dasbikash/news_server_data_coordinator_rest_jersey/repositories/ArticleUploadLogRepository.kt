package com.dasbikash.news_server_data_coordinator_rest_jersey.repositories

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DatabaseTableNames
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.ArticleUploadLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ArticleUploadLogRepository : JpaRepository<ArticleUploadLog, Int>,DeletableLogRepository<ArticleUploadLog>{

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_UPLOAD_LOG_TABLE_NAME} order by id DESC limit :pageSize",
            nativeQuery = true)fun getLatestArticleUploadLogs(pageSize: Int): List<ArticleUploadLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_UPLOAD_LOG_TABLE_NAME} WHERE id < :lastArticleUploadLogId order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getSettingsUpdateLogsBeforeGivenId(lastArticleUploadLogId: Int, pageSize: Int): List<ArticleUploadLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_UPLOAD_LOG_TABLE_NAME} order by id ASC limit :pageSize",
            nativeQuery = true)
    override fun getOldestLogs(pageSize: Int): List<ArticleUploadLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_UPLOAD_LOG_TABLE_NAME} WHERE id >= :lastLogId order by id ASC limit :pageSize",
            nativeQuery = true)
    override fun getLogsAfterGivenId(lastLogId: Int, pageSize: Int): List<ArticleUploadLog>
}