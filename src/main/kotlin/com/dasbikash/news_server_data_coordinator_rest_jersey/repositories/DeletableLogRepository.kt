package com.dasbikash.news_server_data_coordinator_rest_jersey.repositories

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DataCoordinatorRestEntity

interface DeletableLogRepository<T: DataCoordinatorRestEntity> {
    fun getOldestLogs(pageSize: Int): List<T>
    fun getLogsAfterGivenId(lastLogId: Int, pageSize: Int): List<T>
}