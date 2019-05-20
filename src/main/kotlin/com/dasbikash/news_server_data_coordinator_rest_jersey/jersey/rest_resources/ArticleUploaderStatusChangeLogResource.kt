package com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.rest_resources

import com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions.IllegalRequestBodyException
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.ArticleUploaderStatusChangeLogs
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.ArticleUploaderStatusChangeRequest
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.ArticleUploaderStatusChangeRequestFormat
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.RequestDetailsBean
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.ArticleUploaderStatusChangeLog
import com.dasbikash.news_server_data_coordinator_rest_jersey.services.ArticleUploaderStatusChangeLogService
import com.dasbikash.news_server_data_coordinator_rest_jersey.services.AuthTokenService
import com.dasbikash.news_server_data_coordinator_rest_jersey.utills.EmailUtils
import com.dasbikash.news_server_data_coordinator_rest_jersey.utills.RestControllerUtills
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("article-uploader-status-change-logs")
@Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Consumes(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Component
open class ArticleUploaderStatusChangeLogResource
constructor(open var articleUploaderStatusChangeLogService: ArticleUploaderStatusChangeLogService?=null,
            open var authTokenService: AuthTokenService?=null,
            open var restControllerUtills: RestControllerUtills?=null) {

    @Value("\${log.default_page_size}")
    open var defaultPageSize: Int = 10

    @Value("\${log.max_page_size}")
    open var maxPageSize: Int = 50

    @GET
    @Path("")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getLatestArticleUploaderStatusChangeLogsEndPoint(@QueryParam("page-size") pageSizeRequest:Int?,
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
                ArticleUploaderStatusChangeLogs(articleUploaderStatusChangeLogService!!.getLatestArticleUploaderStatusChangeLogs(pageSize)))
    }

    @GET
    @Path("/before/{log-id}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getArticleUploaderStatusChangeLogsBeforeGivenIdEndPoint(@QueryParam("page-size") pageSizeRequest:Int?,
                                                                     @PathParam("log-id") lastStatusChangeLogId:Int,
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
                        ArticleUploaderStatusChangeLogs(articleUploaderStatusChangeLogService!!
                                                            .getArticleUploaderStatusChangeLogsBeforeGivenId(lastStatusChangeLogId,pageSize)))
    }

    @GET
    @Path("/after/{log-id}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getArticleUploaderStatusChangeLogsAfterGivenIdEndPoint(@QueryParam("page-size") pageSizeRequest:Int?,
                                                                    @PathParam("log-id") lastStatusChangeLogId:Int,
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
                        ArticleUploaderStatusChangeLogs(articleUploaderStatusChangeLogService!!
                                                            .getArticleUploaderStatusChangeLogsAfterGivenId(lastStatusChangeLogId,pageSize)))
    }

    @GET
    @Path("request_status_change_token_generation")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun generateStatusChangeTokenEndPoint(@BeanParam requestDetails: RequestDetailsBean)
            :Response{
        val newToken = authTokenService!!.getNewAuthToken()
        EmailUtils.emailAuthTokenToAdmin(newToken,this::class.java)
        return restControllerUtills!!.entityToResponseEntity(ArticleUploaderStatusChangeRequestFormat())
    }

    @POST
    @Path("status_change_request")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    @Consumes(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun changeUploaderStatusEndPoint(articleUploaderStatusChangeRequest: ArticleUploaderStatusChangeRequest?,
                                          @BeanParam requestDetails: RequestDetailsBean)
            :Response{
        if (articleUploaderStatusChangeRequest == null ||
                articleUploaderStatusChangeRequest.authToken == null ||
                articleUploaderStatusChangeRequest.articleUploadTarget == null ||
                articleUploaderStatusChangeRequest.status == null ){
            throw IllegalRequestBodyException()
        }
        authTokenService!!.invalidateAuthToken(articleUploaderStatusChangeRequest.authToken)
        try {
            val statusData = ArticleUploaderStatusChangeLog(
                    status = articleUploaderStatusChangeRequest.status,
                    articleDataUploaderTarget = articleUploaderStatusChangeRequest.articleUploadTarget)
            articleUploaderStatusChangeLogService!!.save(statusData)
            return restControllerUtills!!.entityToResponseEntity(articleUploaderStatusChangeRequest)
        }catch (ex:Exception){
            throw InternalError()
        }
    }
}
