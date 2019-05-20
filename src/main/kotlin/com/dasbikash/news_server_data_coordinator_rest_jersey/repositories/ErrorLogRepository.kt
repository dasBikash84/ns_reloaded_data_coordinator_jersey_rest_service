package com.dasbikash.news_server_data_coordinator_rest_jersey.repositories

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DatabaseTableNames
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.ErrorLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ErrorLogRepository : JpaRepository<ErrorLog, Int>,DeletableLogRepository<ErrorLog>{

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ERROR_LOG_TABLE_NAME} order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getLatestErrorLogs(pageSize: Int): List<ErrorLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ERROR_LOG_TABLE_NAME} WHERE id < :lastErrorLogId order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getErrorLogsBeforeGivenId(lastErrorLogId: Int, pageSize: Int): List<ErrorLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ERROR_LOG_TABLE_NAME} order by id ASC limit :pageSize",
            nativeQuery = true)
    override fun getOldestLogs(pageSize: Int): List<ErrorLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ERROR_LOG_TABLE_NAME} WHERE id >= :lastLogId order by id ASC limit :pageSize",
            nativeQuery = true)
    override fun getLogsAfterGivenId(lastLogId: Int, pageSize: Int): List<ErrorLog>
}