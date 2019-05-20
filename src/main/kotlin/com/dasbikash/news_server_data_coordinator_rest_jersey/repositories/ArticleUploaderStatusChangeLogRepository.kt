package com.dasbikash.news_server_data_coordinator_rest_jersey.repositories

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DatabaseTableNames
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.ArticleUploaderStatusChangeLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ArticleUploaderStatusChangeLogRepository : JpaRepository<ArticleUploaderStatusChangeLog, Int>{

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_UPLOADER_STATUS_CHANGE_LOG_TABLE_NAME} order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getLatestArticleUploaderStatusChangeLogs(pageSize: Int): List<ArticleUploaderStatusChangeLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_UPLOADER_STATUS_CHANGE_LOG_TABLE_NAME} WHERE id < :lastStatusChangeLogId order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getArticleDownloadLogsBeforeGivenId(lastStatusChangeLogId: Int, pageSize: Int): List<ArticleUploaderStatusChangeLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_UPLOADER_STATUS_CHANGE_LOG_TABLE_NAME} WHERE id >= :lastStatusChangeLogId order by id ASC limit :pageSize",
            nativeQuery = true)
    fun getArticleDownloadLogsAfterGivenId(lastStatusChangeLogId: Int, pageSize: Int): List<ArticleUploaderStatusChangeLog>
}