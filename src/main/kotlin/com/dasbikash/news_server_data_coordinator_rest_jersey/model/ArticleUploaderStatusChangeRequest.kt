package com.dasbikash.news_server_data_coordinator_rest_jersey.model

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DataCoordinatorRestEntity
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.ArticleUploadTarget
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.TwoStateStatus
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class ArticleUploaderStatusChangeRequest:DataCoordinatorRestEntity {
    var authToken:String? = null
    var articleUploadTarget:ArticleUploadTarget?=null
    var status:TwoStateStatus?=null
}
@XmlRootElement
class ArticleUploaderStatusChangeRequestFormat:DataCoordinatorRestEntity {
    var authToken:String = "Emailed token"
    var articleUploadTarget:String= ArticleUploadTarget.values().joinToString(separator = " | ")
    var status:String = "Target Status"
}