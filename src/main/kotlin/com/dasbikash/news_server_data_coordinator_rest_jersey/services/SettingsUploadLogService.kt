package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions.DataNotFoundException
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.SettingsUploadLog
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.SettingsUploadLogRepository
import org.springframework.stereotype.Service

@Service
open class SettingsUploadLogService
constructor(open var settingsUploadLogRepository: SettingsUploadLogRepository)
    :DeletableLogService<SettingsUploadLog>{
    fun getLatestSettingsUploadLogs(pageSize: Int): List<SettingsUploadLog> {
        return settingsUploadLogRepository.getLatestSettingsUploadLogs(pageSize)
    }

    fun getSettingsUploadLogsBeforeGivenId(lastErrorLogId: Int, pageSize: Int): List<SettingsUploadLog> {
        val lastSettingsUploadLog = settingsUploadLogRepository.findById(lastErrorLogId)
        if (!lastSettingsUploadLog.isPresent){
            throw DataNotFoundException()
        }
        return settingsUploadLogRepository.getSettingsUpdateLogsBeforeGivenId(lastSettingsUploadLog.get().id!!,pageSize)
    }

    override fun getOldestLogs(pageSize: Int): List<SettingsUploadLog> {
        return settingsUploadLogRepository.getOldestLogs(pageSize)
    }

    override fun getLogsAfterGivenId(lastLogId: Int, pageSize: Int): List<SettingsUploadLog> {
        return settingsUploadLogRepository.getLogsAfterGivenId(lastLogId,pageSize)
    }

    override fun delete(logEntry: SettingsUploadLog) {
        settingsUploadLogRepository.delete(logEntry)
    }
}