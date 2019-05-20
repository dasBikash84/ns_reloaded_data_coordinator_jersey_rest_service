package com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.rest_resources

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.ErrorLogs
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.LogEntryDeleteRequest
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.RequestDetailsBean
import com.dasbikash.news_server_data_coordinator_rest_jersey.services.ErrorLogService
import com.dasbikash.news_server_data_coordinator_rest_jersey.utills.RestControllerUtills
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("error-logs")
@Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Consumes(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Component
open class ErrorLogResource
constructor(open var errorLogService: ErrorLogService?=null,
            open var restControllerUtills: RestControllerUtills?=null) {

    @Value("\${log.default_page_size}")
    open var defaultPageSize: Int = 10

    @Value("\${log.max_page_size}")
    open var maxPageSize: Int = 50

    @GET
    @Path("")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getLatestErrorLogsEndPoint(@QueryParam("page-size") pageSizeRequest:Int?,
                                        @BeanParam requestDetails: RequestDetailsBean): Response {
        var pageSize = defaultPageSize
        pageSizeRequest?.let {
            when{
                it>=maxPageSize -> pageSize = maxPageSize
                it>0            -> pageSize = it
            }
        }
        return restControllerUtills!!.entityToResponseEntity(ErrorLogs(errorLogService!!.getLatestErrorLogs(pageSize)))
    }

    @GET
    @Path("/before/{log-id}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getErrorLogsBeforeGivenIdEndPoint(@QueryParam("page-size") pageSizeRequest:Int?,
                                               @PathParam("log-id") lastErrorLogId:Int,
                                               @BeanParam requestDetails: RequestDetailsBean)
            : Response {
        var pageSize = defaultPageSize
        pageSizeRequest?.let {
            when{
                it>=maxPageSize -> pageSize = maxPageSize
                it>0            -> pageSize = it
            }
        }
        return restControllerUtills!!.entityToResponseEntity(
                                ErrorLogs(errorLogService!!.getErrorLogsBeforeGivenId(lastErrorLogId,pageSize)))
    }

    @GET
    @Path("/after/{log-id}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getErrorLogsAfterGivenIdEndPoint(@QueryParam("page-size") pageSizeRequest:Int?,
                                              @PathParam("log-id") lastErrorLogId:Int,
                                              @BeanParam requestDetails: RequestDetailsBean)
            : Response{
        var pageSize = defaultPageSize
        pageSizeRequest?.let {
            when{
                it>=maxPageSize -> pageSize = maxPageSize
                it>0            -> pageSize = it
            }
        }
        return restControllerUtills!!.entityToResponseEntity(
                                        ErrorLogs(errorLogService!!.getLogsAfterGivenId(lastErrorLogId,pageSize)))
    }

    @DELETE
    @Path("request_log_delete_token_generation")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun generateLogDeletionTokenEndPoint(@BeanParam requestDetails: RequestDetailsBean): Response {
        return restControllerUtills!!.generateLogDeleteToken(this::class.java)
    }

    @DELETE
    @Path("")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    @Consumes(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun deleteErrorLogsEndPoint(logEntryDeleteRequest: LogEntryDeleteRequest?,
                                     @BeanParam requestDetails: RequestDetailsBean)
            : Response {
        return restControllerUtills!!.entityToResponseEntity(ErrorLogs(
                restControllerUtills!!.deleteLogEntries(errorLogService!!,logEntryDeleteRequest)))
    }
}
