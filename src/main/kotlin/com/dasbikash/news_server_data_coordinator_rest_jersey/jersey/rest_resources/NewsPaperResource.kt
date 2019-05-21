package com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.rest_resources

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.Newspapers
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.RequestDetailsBean
import com.dasbikash.news_server_data_coordinator_rest_jersey.services.NewsPaperService
import com.dasbikash.news_server_data_coordinator_rest_jersey.utills.RestControllerUtills
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("newspapers")
@Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Component
open class NewsPaperResource
constructor(open var newsPaperService: NewsPaperService?=null,
            open var restControllerUtills: RestControllerUtills?=null) {

    @GET
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getAllActiveNewsPapersEndPoint(@BeanParam requestDetails: RequestDetailsBean): Response {
        return restControllerUtills!!.entityToResponseEntity(
                            Newspapers(newsPaperService!!.getAllActiveNewsPapers()))
    }

    @GET
    @Path("/country-name/{countryName}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getNewPaperByCountryNameEndPoint(@PathParam("countryName") countryName:String,
                                              @BeanParam requestDetails: RequestDetailsBean)
            :Response{
        return restControllerUtills!!.entityToResponseEntity(Newspapers(
                newsPaperService!!.getAllNewPaperByCountryName(countryName)))
    }

    @GET
    @Path("/language-id/{languageId}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getNewPaperByLanguageIdEndPoint(@PathParam("languageId") languageId:String,
                                             @BeanParam requestDetails: RequestDetailsBean)
            :Response{
        return restControllerUtills!!.entityToResponseEntity(
                    Newspapers(newsPaperService!!.getAllNewPaperByLanguageId(languageId)))
    }
}
