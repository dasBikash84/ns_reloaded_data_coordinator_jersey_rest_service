package com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.rest_resources

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.ArticleUploadLogs
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.LogEntryDeleteRequest
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.RequestDetailsBean
import com.dasbikash.news_server_data_coordinator_rest_jersey.services.ArticleUploadLogService
import com.dasbikash.news_server_data_coordinator_rest_jersey.utills.RestControllerUtills
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("article-upload-logs")
@Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Consumes(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Component
open class ArticleUploadLogController
constructor(open var  articleUploadLogService: ArticleUploadLogService?=null,
            open var  restControllerUtills: RestControllerUtills?=null) {

    @Value("\${log.default_page_size}")
    open var  defaultPageSize: Int = 10

    @Value("\${log.max_page_size}")
    open var  maxPageSize: Int = 50

    @GET
    @Path("")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getLatestArticleUploadLogsEndPoint(@QueryParam("page-size") pageSizeRequest:Int?,
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
                    ArticleUploadLogs(articleUploadLogService!!.getLatestArticleUploadLogs(pageSize)))
    }

    @GET
    @Path("/before/{log-id}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getArticleUploadLogsBeforeGivenIdEndPoint(@QueryParam("page-size") pageSizeRequest:Int?,
                                                       @PathParam("log-id") lastArticleUploadLogId:Int,
                                                       @BeanParam requestDetails: RequestDetailsBean)
            : Response {
        var pageSize = defaultPageSize
        pageSizeRequest?.let {
            when{
                it>=maxPageSize -> pageSize = maxPageSize
                it>0            -> pageSize = it
            }
        }
        return restControllerUtills!!.entityToResponseEntity(ArticleUploadLogs(
                articleUploadLogService!!.getArticleUploadLogsBeforeGivenId(lastArticleUploadLogId,pageSize)))
    }

    @GET
    @Path("/after/{log-id}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getArticleUploadLogsAfterGivenIdEndPoint(@QueryParam("page-size") pageSizeRequest:Int?,
                                                      @PathParam("log-id") lastArticleUploadLogId:Int,
                                                      @BeanParam requestDetails: RequestDetailsBean)
            : Response{
        var pageSize = defaultPageSize
        pageSizeRequest?.let {
            when{
                it>=maxPageSize -> pageSize = maxPageSize
                it>0            -> pageSize = it
            }
        }
        return restControllerUtills!!.entityToResponseEntity(ArticleUploadLogs(
                articleUploadLogService!!.getLogsAfterGivenId(lastArticleUploadLogId,pageSize)))
    }

    @DELETE
    @Path("request_log_delete_token_generation")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun generateLogDeletionTokenEndPoint(@BeanParam requestDetails: RequestDetailsBean)
            : Response{
        return restControllerUtills!!.generateLogDeleteToken(this::class.java)
    }

    @DELETE
    @Path("")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    @Consumes(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun deleteErrorLogsEndPoint(logEntryDeleteRequest: LogEntryDeleteRequest?,
                                     @BeanParam requestDetails: RequestDetailsBean)
            : Response {
        return restControllerUtills!!.entityToResponseEntity(ArticleUploadLogs(
                restControllerUtills!!.deleteLogEntries(articleUploadLogService!!,logEntryDeleteRequest)))
    }
}
