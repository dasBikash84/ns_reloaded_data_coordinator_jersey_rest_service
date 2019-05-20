package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions.DataNotFoundException
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.SettingsUpdateLog
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.SettingsUpdateLogRepository
import org.springframework.stereotype.Service

@Service
open class SettingsUpdateLogService
constructor(open var settingsUpdateLogRepository: SettingsUpdateLogRepository)
    :DeletableLogService<SettingsUpdateLog>{
    fun getLatestSettingsUpdateLogs(pageSize: Int): List<SettingsUpdateLog> {
        val settingsUpdateLogs = settingsUpdateLogRepository.getLatestSettingsUpdateLogs(pageSize)
        if (settingsUpdateLogs.isEmpty()){
            throw DataNotFoundException()
        }
        return settingsUpdateLogs
    }

    fun getSettingsUpdateLogsBeforeGivenId(lastErrorLogId: Int, pageSize: Int): List<SettingsUpdateLog> {
        val lastSettingsUpdateLog = settingsUpdateLogRepository.findById(lastErrorLogId)
        if (!lastSettingsUpdateLog.isPresent){
            throw DataNotFoundException()
        }
        return settingsUpdateLogRepository.getSettingsUpdateLogsBeforeGivenId(lastSettingsUpdateLog.get().id!!,pageSize)
    }

    override fun getOldestLogs(pageSize: Int): List<SettingsUpdateLog> {
        return settingsUpdateLogRepository.getOldestLogs(pageSize)
    }

    override fun getLogsAfterGivenId(lastLogId: Int, pageSize: Int): List<SettingsUpdateLog> {
        return settingsUpdateLogRepository.getLogsAfterGivenId(lastLogId,pageSize)
    }

    override fun delete(logEntry: SettingsUpdateLog) {
        settingsUpdateLogRepository.delete(logEntry)
    }
}