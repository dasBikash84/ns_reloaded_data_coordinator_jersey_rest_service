package com.dasbikash.news_server_data_coordinator_rest_jersey.model

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DataCoordinatorRestEntity

class LogEntryDeleteRequest(
        val authToken: String? = null,
        val targetLogId: Int? = null,
        var entryDeleteCount: Int? = null
) : DataCoordinatorRestEntity {
    companion object {
        const val MAX_ENTRY_DELETE_LIMIT = 500
        const val DEFAULT_ENTRY_DELETE_COUNT = 10
    }
}

class LogEntryDeleteRequestFormat (
        val authToken:String = "Emailed token",
        val targetLogId: String = "Log id for entry deletion from specific location. " +
                                    "Oldest entry will be deleted for null",
        val entryDeleteCount: String = "Log enrty delete count. " +
                                        "Max:${LogEntryDeleteRequest.MAX_ENTRY_DELETE_LIMIT}. "+
                                        "Default:${LogEntryDeleteRequest.DEFAULT_ENTRY_DELETE_COUNT}"
): DataCoordinatorRestEntity