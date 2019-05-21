package com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.rest_resources

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.Pages
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.RequestDetailsBean
import com.dasbikash.news_server_data_coordinator_rest_jersey.services.PageService
import com.dasbikash.news_server_data_coordinator_rest_jersey.utills.RestControllerUtills
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("pages")
@Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Component
open class PageResource (open var pageService: PageService?=null,
                         open var restControllerUtills: RestControllerUtills?=null){
    @GET
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getAllActivePagesEndPoint(@BeanParam requestDetails: RequestDetailsBean): Response {
        return restControllerUtills!!.entityToResponseEntity(
                            Pages(pageService!!.getAllActivePages()))
    }

    @GET
    @Path("/newspaper-id/{newsPaperId}/top-level-pages")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getAllTopLevelPagesForNewsPaperEndPoint(@PathParam("newsPaperId") newsPaperId:String,
                                                     @BeanParam requestDetails: RequestDetailsBean)
            :Response{
        return restControllerUtills!!.entityToResponseEntity(
                        Pages(pageService!!.getAllTopLevelPagesForNewsPaper(newsPaperId)))
    }

    @GET
    @Path("/top-level-page-id/{pageId}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getAllChildPagesForTopLevelPageEndPoint(@PathParam("pageId") pageId:String,
                                                     @BeanParam requestDetails: RequestDetailsBean)
            :Response{
        return restControllerUtills!!.entityToResponseEntity(
                    Pages(pageService!!.getAllChildPagesForTopLevelPage(pageId)))
    }
}