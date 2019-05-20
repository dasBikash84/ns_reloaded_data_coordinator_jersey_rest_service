package com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.rest_resources

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.Languages
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.RequestDetailsBean
import com.dasbikash.news_server_data_coordinator_rest_jersey.services.LanguageService
import com.dasbikash.news_server_data_coordinator_rest_jersey.utills.RestControllerUtills
import org.springframework.stereotype.Component
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("languages")
@Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Component
open class LanguageResource
constructor(open var languageService: LanguageService?=null,
            open var restControllerUtills: RestControllerUtills?=null) {
    @GET
    @Path("")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getAllLanguagesEndPoint(@BeanParam requestDetails: RequestDetailsBean): Response {
        return restControllerUtills!!.entityToResponseEntity(Languages(languageService!!.getAllLanguages()))
    }

}
