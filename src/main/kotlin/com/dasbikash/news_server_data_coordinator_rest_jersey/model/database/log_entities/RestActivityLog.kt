package com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.RequestDetailsBean
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DatabaseTableNames
import org.aspectj.lang.JoinPoint
import javax.persistence.*

@Entity
@Table(name = DatabaseTableNames.REST_ACTIVITY_LOG_TABLE_NAME)
data class RestActivityLog(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id:Int?=null,
        val requestURL:String,
        val requestMethod:String,
        val remoteHost:String,
        val methodSignature:String,
        val timeTakenMs:Int,
        val returnedEntiryCount:Int?=null,
        val exceptionClassName:String?=null,
        val acceptHeader:String?=null,
        val userAgentHeader:String?=null
){
    companion object{
        fun getInstance(joinPoint: JoinPoint, timeTakenMs: Int,
                        exceptionClassFullName: String?=null, returnedEntiryCount:Int?=null,
                        requestDetails: RequestDetailsBean)
                :RestActivityLog{
            return RestActivityLog(requestURL = requestDetails.requestURL,methodSignature = joinPoint.signature.toString(),
                    requestMethod = requestDetails.requestMethod,remoteHost = requestDetails.remoteHost,timeTakenMs = timeTakenMs,
                    exceptionClassName = exceptionClassFullName,returnedEntiryCount = returnedEntiryCount,
                    acceptHeader = requestDetails.acceptHeader,userAgentHeader = requestDetails.userAgentHeader)
        }
    }
}