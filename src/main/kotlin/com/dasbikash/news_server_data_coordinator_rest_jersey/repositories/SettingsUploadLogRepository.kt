package com.dasbikash.news_server_data_coordinator_rest_jersey.repositories

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DatabaseTableNames
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.SettingsUploadLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SettingsUploadLogRepository : JpaRepository<SettingsUploadLog, Int>,DeletableLogRepository<SettingsUploadLog>{

    @Query(value = "SELECT * FROM ${DatabaseTableNames.SETTINGS_UPLOAD_LOG_TABLE_NAME} order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getLatestSettingsUploadLogs(pageSize: Int): List<SettingsUploadLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.SETTINGS_UPLOAD_LOG_TABLE_NAME} WHERE id < :lastSettingsUploadLogId order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getSettingsUpdateLogsBeforeGivenId(lastSettingsUploadLogId: Int, pageSize: Int): List<SettingsUploadLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.SETTINGS_UPLOAD_LOG_TABLE_NAME} order by id ASC limit :pageSize",
            nativeQuery = true)
    override fun getOldestLogs(pageSize: Int): List<SettingsUploadLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.SETTINGS_UPLOAD_LOG_TABLE_NAME} WHERE id >= :lastLogId order by id ASC limit :pageSize",
            nativeQuery = true)
    override fun getLogsAfterGivenId(lastLogId: Int, pageSize: Int): List<SettingsUploadLog>
}