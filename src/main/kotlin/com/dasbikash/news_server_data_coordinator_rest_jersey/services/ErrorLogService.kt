package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions.DataNotFoundException
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.ErrorLog
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.ErrorLogRepository
import org.springframework.stereotype.Service

@Service
open class ErrorLogService
constructor(open var errorLogRepository: ErrorLogRepository)
    :DeletableLogService<ErrorLog>{
    fun getLatestErrorLogs(pageSize: Int): List<ErrorLog> {
        return errorLogRepository.getLatestErrorLogs(pageSize)
    }

    fun getErrorLogsBeforeGivenId(lastErrorLogId: Int, pageSize: Int): List<ErrorLog> {
        val lastErrorLog = errorLogRepository.findById(lastErrorLogId)
        if (!lastErrorLog.isPresent){
            throw DataNotFoundException()
        }
        return errorLogRepository.getErrorLogsBeforeGivenId(lastErrorLog.get().id!!,pageSize)
    }

    override fun getOldestLogs(pageSize: Int): List<ErrorLog> {
        return errorLogRepository.getOldestLogs(pageSize)
    }

    override fun getLogsAfterGivenId(lastLogId: Int, pageSize: Int): List<ErrorLog> {
        val lastErrorLog = errorLogRepository.findById(lastLogId)
        if (!lastErrorLog.isPresent){
            throw DataNotFoundException()
        }
        return errorLogRepository.getLogsAfterGivenId(lastErrorLog.get().id!!,pageSize)
    }
    override fun delete(logEntry: ErrorLog){
        errorLogRepository.delete(logEntry)
    }
}