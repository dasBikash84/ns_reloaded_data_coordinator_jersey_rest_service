package com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.rest_resources

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.ArticleDeleteRequests
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.RequestDetailsBean
import com.dasbikash.news_server_data_coordinator_rest_jersey.services.ArticleDeleteRequestService
import com.dasbikash.news_server_data_coordinator_rest_jersey.utills.RestControllerUtills
import org.springframework.stereotype.Component
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("article-delete-requests")
@Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Component
open class ArticleDeleteRequestResource
constructor(open var articleDeleteRequestService: ArticleDeleteRequestService?=null,
            open var restControllerUtills: RestControllerUtills?=null) {
    @GET
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getAllEndPoint(@BeanParam requestDetails: RequestDetailsBean): Response {
        return restControllerUtills!!.entityToResponseEntity(ArticleDeleteRequests(articleDeleteRequestService!!.getAll()))
    }

}
