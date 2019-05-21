package com.dasbikash.news_server_data_coordinator_rest_jersey.model

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DataCoordinatorRestEntity
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class LogEntryDeleteRequest(
        var authToken: String? = null,
        var targetLogId: Int? = null,
        var entryDeleteCount: Int? = null
) : DataCoordinatorRestEntity {
    companion object {
        const val MAX_ENTRY_DELETE_LIMIT = 500
        const val DEFAULT_ENTRY_DELETE_COUNT = 10
    }
}

@XmlRootElement
class LogEntryDeleteRequestFormat (
        var authToken:String = "Emailed token",
        var targetLogId: String = "Log id for entry deletion from specific location. " +
                "Oldest entry will be deleted for null",
        var entryDeleteCount: String = "Log enrty delete count. " +
                                                "Max:${LogEntryDeleteRequest.MAX_ENTRY_DELETE_LIMIT}. "+
                                                "Default:${LogEntryDeleteRequest.DEFAULT_ENTRY_DELETE_COUNT}"
): DataCoordinatorRestEntity