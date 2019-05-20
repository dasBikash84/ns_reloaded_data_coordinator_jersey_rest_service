package com.dasbikash.news_server_data_coordinator_rest_jersey.repositories

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DatabaseTableNames
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.SettingsUpdateLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SettingsUpdateLogRepository : JpaRepository<SettingsUpdateLog, Int>,DeletableLogRepository<SettingsUpdateLog>{

    @Query(value = "SELECT * FROM ${DatabaseTableNames.SETTINGS_UPDATE_LOG_TABLE_NAME} order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getLatestSettingsUpdateLogs(pageSize: Int): List<SettingsUpdateLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.SETTINGS_UPDATE_LOG_TABLE_NAME} WHERE id < :lastSettingsUpdateLogId order by id DESC limit :pageSize",
            nativeQuery = true)
    fun getSettingsUpdateLogsBeforeGivenId(lastSettingsUpdateLogId: Int, pageSize: Int): List<SettingsUpdateLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.SETTINGS_UPDATE_LOG_TABLE_NAME} order by id ASC limit :pageSize",
            nativeQuery = true)
    override fun getOldestLogs(pageSize: Int): List<SettingsUpdateLog>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.SETTINGS_UPDATE_LOG_TABLE_NAME} WHERE id >= :lastLogId order by id ASC limit :pageSize",
            nativeQuery = true)
    override fun getLogsAfterGivenId(lastLogId: Int, pageSize: Int): List<SettingsUpdateLog>
}