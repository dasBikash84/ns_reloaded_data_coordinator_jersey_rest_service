package com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.exception_mappers

import com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions.*
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider


@Provider
class GenericExceptionMapper : ExceptionMapper<Throwable> {

    override fun toResponse(ex: Throwable): Response {
        ex.printStackTrace()
        return when(ex){
            is NewsPaperNotFoundByIdException   -> Response.status(Status.NOT_FOUND).entity(ex::class.java.simpleName).build()
            is NewsPaperNotFoundByNameException -> Response.status(Status.NOT_FOUND).entity(ex::class.java.simpleName).build()
            is CustomDataAccessException        -> Response.status(Status.NOT_FOUND).entity(ex::class.java.simpleName).build()
            is DataNotFoundException            -> Response.status(Status.NOT_FOUND).entity(ex::class.java.simpleName).build()
            is IllegalRequestBodyException      -> Response.status(Status.BAD_REQUEST).entity(ex::class.java.simpleName).build()
            is InternalError                    -> Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex::class.java.simpleName).build()
            else ->{
                throw ex
            }
        }
    }

}